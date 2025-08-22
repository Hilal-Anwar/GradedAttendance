package org.graded_classes.graded_attendance.data;

import org.graded_classes.graded_attendance.GradedResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Scanner;

public class SqlFileReader {
    private String query="";
    public SqlFileReader(String filePath) {
        Scanner scanner = new Scanner(GradedResourceLoader.loadStream(filePath));
        while (scanner.hasNextLine()) query = MessageFormat.format("{0}{1}\n", query, scanner.nextLine());
        }
    public String getQuery() {
        return query;
    }
}
