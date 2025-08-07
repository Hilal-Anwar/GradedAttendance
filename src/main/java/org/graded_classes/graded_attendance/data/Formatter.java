package org.graded_classes.graded_attendance.data;

public class Formatter {
    public static String format(String input) {
        char i = input.charAt(0);
        return Character.toUpperCase(i) + input.substring(1).toLowerCase();
    }
}
