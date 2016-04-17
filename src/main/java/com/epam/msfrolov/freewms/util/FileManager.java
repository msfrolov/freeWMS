package com.epam.msfrolov.freewms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

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
        InputStream in = FileManager.class.getClassLoader().getResourceAsStream(fileName);
        Scanner sc = new Scanner(in);
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine())
            lines.add(sc.nextLine());
        return lines;
    }
}
