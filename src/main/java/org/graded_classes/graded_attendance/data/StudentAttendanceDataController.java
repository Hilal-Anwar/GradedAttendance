package org.graded_classes.graded_attendance.data;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class StudentAttendanceDataController {
    DatabaseLoader studentDatabaseLoader;

    public StudentAttendanceDataController(String root_path) {
        String monthName = LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.UK) + "-" + LocalDate.now().getYear();
        studentDatabaseLoader = new DatabaseLoader(root_path + monthName, "Attendance.db");
    }

    StudentFeeDataController studentFeeDataController;
}
