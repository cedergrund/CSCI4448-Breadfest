package org.example.breadfest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FXMLContent {
    public static void writeToFxmlFile(String relative_file_path, String content) {
        String file_path = Paths.get("src/main/resources/org/example/breadfest", relative_file_path).toString();

        try {
            FileWriter fileWriter = new FileWriter(file_path);
            // Create BufferedWriter object to write efficiently
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Write the content to the file
            bufferedWriter.write(content);
            // Close the BufferedWriter
            bufferedWriter.close();
            System.out.println("Content has been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("Got an error from test!");
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void wipeFileContent(String relativeFilePath) {
        String filePath = Paths.get("src/main/resources/org/example/breadfest", relativeFilePath).toString();
        try {
            // Open the file for writing, which truncates it (clears its content)
            FileWriter fileWriter = new FileWriter(filePath);
            // Close the FileWriter
            fileWriter.close();
            System.out.println("Content of the file has been wiped successfully.");
        } catch (IOException e) {
            System.out.println("Got an error while wiping file content!");
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
