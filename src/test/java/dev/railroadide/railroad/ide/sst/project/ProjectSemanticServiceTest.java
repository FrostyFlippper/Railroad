package dev.railroadide.railroad.ide.sst.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectSemanticServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void cachesIndexesPerProjectRoot() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """
        );

        ProjectSemanticService service = new ProjectSemanticService();
        ProjectSemanticIndex first = service.index(root);
        ProjectSemanticIndex second = service.index(root);

        assertSame(first, second);
        assertTrue(service.hasIndex(root));
    }

    @Test
    void rebuildRefreshesProjectIndex() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """
        );

        ProjectSemanticService service = new ProjectSemanticService();
        ProjectSemanticIndex initial = service.index(root);

        Files.writeString(root.resolve("src/main/java/demo/B.java"), """
                package demo;

                class B {
                }
                """);

        ProjectSemanticIndex rebuilt = service.rebuild(root);

        assertFalse(initial == rebuilt);
        assertEquals(2, rebuilt.files().size());
        assertEquals(1, rebuilt.lookupQualifiedName("demo.B").size());
    }

    @Test
    void updateFileReplacesOneIndexedFile() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """
        );

        ProjectSemanticService service = new ProjectSemanticService();
        service.index(root);

        Path aFile = root.resolve("src/main/java/demo/A.java");
        Files.writeString(aFile, """
                package demo;

                class A {
                    static int VALUE;
                }
                """);

        service.updateFile(root, aFile);
        ProjectSemanticIndex updated = service.index(root);

        assertEquals(1, updated.lookupMember("demo.A", "VALUE").size());
    }

    @Test
    void removeFileDropsIndexedFacts() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """,
                "src/main/java/demo/B.java", """
                        package demo;

                        class B {
                        }
                        """
        );

        ProjectSemanticService service = new ProjectSemanticService();
        service.index(root);

        Path bFile = root.resolve("src/main/java/demo/B.java");
        service.removeFile(root, bFile);

        ProjectSemanticIndex updated = service.index(root);
        assertEquals(1, updated.files().size());
        assertTrue(updated.lookupQualifiedName("demo.B").isEmpty());
    }

    @Test
    void invalidateRemovesInMemoryCacheOnly() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """
        );

        ProjectSemanticService service = new ProjectSemanticService();
        ProjectSemanticIndex initial = service.index(root);
        assertTrue(service.hasIndex(root));

        service.invalidate(root);
        assertFalse(service.hasIndex(root));

        ProjectSemanticIndex reloaded = service.index(root);
        assertNotSame(initial, reloaded);
        assertEquals(1, reloaded.lookupQualifiedName("demo.A").size());
    }

    @Test
    void loadsPersistedIndexAfterCacheMiss() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """
        );

        ProjectSemanticService writer = new ProjectSemanticService();
        ProjectSemanticIndex initial = writer.index(root);

        ProjectSemanticService reader = new ProjectSemanticService();
        ProjectSemanticIndex reloaded = reader.index(root);

        assertNotSame(initial, reloaded);
        assertEquals(1, reloaded.lookupQualifiedName("demo.A").size());
    }

    @Test
    void reloadsFromSourceWhenPersistedIndexIsStale() throws Exception {
        Path root = createProject(
                "src/main/java/demo/A.java", """
                        package demo;

                        class A {
                        }
                        """
        );

        ProjectSemanticService writer = new ProjectSemanticService();
        writer.index(root);

        Path aFile = root.resolve("src/main/java/demo/A.java");
        Files.writeString(aFile, """
                package demo;

                class A {
                    static int VALUE;
                }
                """);

        ProjectSemanticService reader = new ProjectSemanticService();
        ProjectSemanticIndex reloaded = reader.index(root);

        assertEquals(1, reloaded.lookupMember("demo.A", "VALUE").size());
    }

    private Path createProject(String relativePath, String source, String... additionalPathAndSourcePairs) throws Exception {
        Path root = tempDir.resolve("project-" + System.nanoTime());
        writeProjectSource(root, relativePath, source);
        for (int index = 0; index < additionalPathAndSourcePairs.length; index += 2) {
            writeProjectSource(root, additionalPathAndSourcePairs[index], additionalPathAndSourcePairs[index + 1]);
        }
        return root;
    }

    private static void writeProjectSource(Path root, String relativePath, String source) throws Exception {
        Path file = root.resolve(relativePath);
        Files.createDirectories(file.getParent());
        Files.writeString(file, source);
    }
}
