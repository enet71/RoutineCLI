package org.routine.templates;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TemplatesManager {
    Properties templateProperties = new Properties();

    public void copyTemplate(Path from, Path to) {
        loadTemplateProperties(from);

        try (Stream<Path> files = Files.walk(from)) {
            files.forEach((filePath) -> {
                if (filePath.toString().contains(".properties")) {
                    System.out.println("PROPERTIES");
                } else if (filePath.toFile().isDirectory()) {
                    createDirectory(createToPath(from, to, filePath));
                } else {
                    copyFile(filePath, createToPath(from, to, filePath));
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTemplateProperties(Path from) {
        try {
            FileInputStream inputStream = new FileInputStream(from + "/.properties");
            templateProperties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path createToPath(Path from, Path to, Path filePath) {
        int fromCount = from.getNameCount() - 1;
        Path fileSubPath = filePath.subpath(fromCount, filePath.getNameCount());
        return Paths.get(to.toString(), fileSubPath.toString());
    }

    private void createDirectory(Path to) {
        try {
            if (!Files.exists(to)) {
                Files.createDirectory(to);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void copyFile(Path from, Path to) {
        Path parsedToPath = Paths.get(replaceStringProperties(to.toString()));

        try {
            Files.deleteIfExists(parsedToPath);
            Files.createFile(parsedToPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Stream<String> lines = Files.lines(from)) {
            lines.forEach(line -> {
                String parsedLine = replaceStringProperties(line);

                try {
                    Files.writeString(parsedToPath, parsedLine + System.lineSeparator(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String replaceStringProperties(String line) {
        System.out.println("LINE:" + line);
        Pattern pattern = Pattern.compile("#\\{\\{(.*?)}}#");
        Matcher matcher = pattern.matcher(line);

        String parsedLine = line;

        while (matcher.find()) {
            String property = line.substring(matcher.start(), matcher.end());
            String propertyKey = matcher.group(1);

            if (propertyKey != null) {
                String templateProperty = templateProperties.getProperty(propertyKey);
                parsedLine = parsedLine.replace(property, templateProperty);
            }
        }

        return parsedLine;
    }
}
