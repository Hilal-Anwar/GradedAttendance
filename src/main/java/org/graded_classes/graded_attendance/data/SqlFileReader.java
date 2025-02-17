package org.graded_classes.graded_attendance.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SqlFileReader {
    private String query="";
    public SqlFileReader(String filePath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
                query = query + scanner.nextLine()+"\n";
            }
        }
    public String getQuery() {
        return query;
    }
}
