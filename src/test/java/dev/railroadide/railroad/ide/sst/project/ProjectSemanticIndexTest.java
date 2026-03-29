package dev.railroadide.railroad.ide.sst.project;

import dev.railroadide.railroad.ide.sst.semantic.api.SymbolKind;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectSemanticIndexTest {

    @Test
    void emptyIndexReturnsNoFilesOrSymbols() {
        ProjectSemanticIndex index = ProjectSemanticIndex.empty();

        assertTrue(index.files().isEmpty());
        assertFalse(index.containsFile(Path.of("src/main/java/demo/Utility.java")));
        assertTrue(index.getFile(Path.of("src/main/java/demo/Utility.java")).isEmpty());
        assertTrue(index.getFilesByPackage("demo").isEmpty());
        assertTrue(index.lookupSimpleName("Utility").isEmpty());
        assertTrue(index.lookupQualifiedName("demo.Utility").isEmpty());
        assertTrue(index.lookupMembers("demo.Utility").isEmpty());
        assertTrue(index.lookupMember("demo.Utility", "VALUE").isEmpty());
    }

    @Test
    void indexesFilesPackagesSymbolsAndMembers() {
        Path utilityPath = Path.of("src/main/java/demo/Utility.java");
        Path usePath = Path.of("src/main/java/demo/Use.java");

        ProjectSemanticIndex.SourceFileIndex utility = new ProjectSemanticIndex.SourceFileIndex(
                utilityPath,
                " demo ",
                List.of(),
                List.of(
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.CLASS,
                                "Utility",
                                "demo.Utility",
                                null,
                                null,
                                utilityPath,
                                false,
                                true
                        ),
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.FIELD,
                                "VALUE",
                                "demo.Utility#VALUE",
                                "demo.Utility",
                                null,
                                utilityPath,
                                true,
                                false
                        ),
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.METHOD,
                                "run",
                                "demo.Utility#run(String)",
                                "demo.Utility",
                                "(String)",
                                utilityPath,
                                false,
                                false
                        )
                )
        );

        ProjectSemanticIndex.SourceFileIndex use = new ProjectSemanticIndex.SourceFileIndex(
                usePath,
                "demo",
                List.of(
                        new ProjectSemanticIndex.ImportDescriptor("demo.Utility", false, false),
                        new ProjectSemanticIndex.ImportDescriptor("demo.Utility.VALUE", true, false)
                ),
                List.of(
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.CLASS,
                                "Use",
                                "demo.Use",
                                null,
                                null,
                                usePath,
                                false,
                                true
                        )
                )
        );

        ProjectSemanticIndex index = ProjectSemanticIndex.builder()
                .putFile(utility)
                .putFile(use)
                .build();

        assertEquals(2, index.files().size());
        assertTrue(index.containsFile(utility.path()));
        assertEquals(utility, index.getFile(utilityPath).orElseThrow());
        assertEquals(2, index.getFilesByPackage("demo").size());
        assertEquals(1, index.lookupSimpleName("Utility").size());
        assertEquals(1, index.lookupQualifiedName("demo.Utility").size());
        assertEquals(2, index.lookupMembers("demo.Utility").size());
        assertEquals(1, index.lookupMember("demo.Utility", "VALUE").size());
        assertEquals(1, index.lookupMember("demo.Utility", "run").size());
    }

    @Test
    void builderRemoveFileRemovesIndexedDeclarations() {
        Path utilityPath = Path.of("src/main/java/demo/Utility.java");

        ProjectSemanticIndex.SourceFileIndex utility = new ProjectSemanticIndex.SourceFileIndex(
                utilityPath,
                "demo",
                List.of(),
                List.of(
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.CLASS,
                                "Utility",
                                "demo.Utility",
                                null,
                                null,
                                utilityPath,
                                false,
                                true
                        )
                )
        );

        ProjectSemanticIndex index = ProjectSemanticIndex.builder()
                .putFile(utility)
                .removeFile(Path.of("src/main/java/demo/./Utility.java"))
                .build();

        assertFalse(index.containsFile(utility.path()));
        assertTrue(index.getFile(utilityPath).isEmpty());
        assertTrue(index.lookupSimpleName("Utility").isEmpty());
        assertTrue(index.lookupQualifiedName("demo.Utility").isEmpty());
    }

    @Test
    void sourceFileIndexCollectsOnlyNonNullQualifiedNames() {
        Path path = Path.of("src/main/java/demo/Utility.java");

        ProjectSemanticIndex.SourceFileIndex file = new ProjectSemanticIndex.SourceFileIndex(
                path,
                "demo",
                List.of(),
                List.of(
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.CLASS,
                                "Utility",
                                "demo.Utility",
                                null,
                                null,
                                path,
                                false,
                                true
                        ),
                        new ProjectSemanticIndex.SymbolDescriptor(
                                SymbolKind.LOCAL_VARIABLE,
                                "temp",
                                null,
                                "demo.Utility#run(String)",
                                null,
                                path,
                                false,
                                false
                        )
                )
        );

        assertEquals(Set.of("demo.Utility"), file.declaredQualifiedNames());
    }

    @Test
    void rejectsBlankImportAndSymbolNames() {
        Path path = Path.of("src/main/java/demo/Utility.java");

        assertThrows(IllegalArgumentException.class, () ->
                new ProjectSemanticIndex.ImportDescriptor("   ", false, false));

        assertThrows(IllegalArgumentException.class, () ->
                new ProjectSemanticIndex.SymbolDescriptor(
                        SymbolKind.CLASS,
                        "   ",
                        "demo.Utility",
                        null,
                        null,
                        path,
                        false,
                        true
                ));
    }
}
