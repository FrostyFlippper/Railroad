package dev.railroadide.railroad.ide.sst.project;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Builds a project-wide semantic index by extracting declaration facts from
 * Java source files.
 */
public final class JavaProjectSemanticIndexer {
    private final JavaProjectSemanticExtractor extractor;

    public JavaProjectSemanticIndexer() {
        this(new JavaProjectSemanticExtractor());
    }

    public JavaProjectSemanticIndexer(JavaProjectSemanticExtractor extractor) {
        this.extractor = Objects.requireNonNull(extractor, "extractor");
    }

    public ProjectSemanticIndex build(Path projectRoot) {
        Objects.requireNonNull(projectRoot, "projectRoot");

        try (Stream<Path> paths = Files.walk(projectRoot)) {
            List<Path> javaFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".java"))
                    .sorted(Comparator.naturalOrder())
                    .toList();
            return build(javaFiles);
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to build semantic index for " + projectRoot, exception);
        }
    }

    public ProjectSemanticIndex build(List<Path> sourceFiles) {
        Objects.requireNonNull(sourceFiles, "sourceFiles");

        ProjectSemanticIndex.Builder builder = ProjectSemanticIndex.builder();
        for (Path sourceFile : sourceFiles) {
            if (sourceFile == null)
                continue;

            String source = readSource(sourceFile);
            builder.putFile(extractor.extract(sourceFile, source));
        }

        return builder.build();
    }

    public ProjectSemanticIndex.SourceFileIndex indexFile(Path sourceFile) {
        Objects.requireNonNull(sourceFile, "sourceFile");
        return extractor.extract(sourceFile, readSource(sourceFile));
    }

    private static String readSource(Path sourceFile) {
        try {
            return Files.readString(sourceFile);
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to read Java source file " + sourceFile, exception);
        }
    }
}
