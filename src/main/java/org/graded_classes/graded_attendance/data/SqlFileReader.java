package org.graded_classes.graded_attendance.data;

import org.graded_classes.graded_attendance.GradedResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SqlFileReader {
    private String query="";
    public SqlFileReader(String filePath) {
        Scanner scanner = null;
        System.out.println(GradedResourceLoader.loadURL(filePath).getPath());
        scanner = new Scanner(GradedResourceLoader.loadStream(filePath));
        while (scanner.hasNextLine()) {
                query = query + scanner.nextLine() + "\n";
            }
        }
    public String getQuery() {
        return query;
    }
}
