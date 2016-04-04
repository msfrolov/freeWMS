package com.epam.msfrolov.freewms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileManager {
    public static Properties getProperties(String fileName) {
        try {
            Properties properties = new Properties();
            properties.load(FileManager.class.getClassLoader().getResourceAsStream(fileName));
            return properties;

        } catch (IOException e) {
            throw new AppException("IOException: failed to get property", e);
        }

    }

    public static List<String> readFileToList(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(FileManager.class.getClassLoader().getResourceAsStream(fileName)))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new AppException("IOException: failed to get file", e);
        }
    }
}
