package org.example.breadfest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FXMLContent {
    public static void writeToFxmlFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static void main(String[] args) {
        String content = "This is the content that I want to write to a file.";

        // Specify the relative file path within resources
        String relativeFilePath = "output.txt";

        // Get the absolute file path by resolving the relative path against resources directory
        String absoluteFilePath = Paths.get("src/main/resources/org/example/breadfest", relativeFilePath).toString();

        try {
            // Create FileWriter object with the absolute file path
            FileWriter fileWriter = new FileWriter(absoluteFilePath);

            // Create BufferedWriter object to write efficiently
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the content to the file
            bufferedWriter.write(content);

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("Content has been written to the file successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
