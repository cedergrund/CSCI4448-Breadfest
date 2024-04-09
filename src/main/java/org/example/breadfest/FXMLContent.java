package org.example.breadfest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FXMLContent {
    public static void writeToFxmlFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
