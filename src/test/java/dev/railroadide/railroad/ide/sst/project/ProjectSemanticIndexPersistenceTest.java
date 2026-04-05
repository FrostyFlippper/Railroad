package dev.railroadide.railroad.ide.sst.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectSemanticIndexPersistenceTest {

    @TempDir
    Path tempDir;

    @Test
    void savesAndLoadsProjectSemanticIndex() throws Exception {
        Path root = tempDir.resolve("project");
        Path aFile = root.resolve("src/main/java/demo/A.java");
        Files.createDirectories(aFile.getParent());
        Files.writeString(aFile, """
                package demo;

                class A {
                    static int VALUE;
                }
                """);

        ProjectSemanticIndex index = new JavaProjectSemanticIndexer().build(root);
        ProjectSemanticIndexPersistence persistence = new ProjectSemanticIndexPersistence();
        persistence.save(root, index);

        ProjectSemanticIndex loaded = persistence.loadIfCurrent(root);

        assertNotNull(loaded);
        assertEquals(1, loaded.files().size());
        assertEquals(1, loaded.lookupQualifiedName("demo.A").size());
        assertEquals(1, loaded.lookupMember("demo.A", "VALUE").size());
    }

    @Test
    void ignoresStaleManifestEntries() throws Exception {
        Path root = tempDir.resolve("project");
        Path aFile = root.resolve("src/main/java/demo/A.java");
        Files.createDirectories(aFile.getParent());
        Files.writeString(aFile, """
                package demo;

                class A {
                }
                """);

        JavaProjectSemanticIndexer indexer = new JavaProjectSemanticIndexer();
        ProjectSemanticIndexPersistence persistence = new ProjectSemanticIndexPersistence();
        persistence.save(root, indexer.build(root));

        Files.writeString(aFile, """
                package demo;

                class A {
                    static int VALUE;
                }
                """);

        ProjectSemanticIndex loaded = persistence.loadIfCurrent(root);

        assertTrue(loaded == null);
    }
}
