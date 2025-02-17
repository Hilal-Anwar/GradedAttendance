package org.graded_classes.graded_attendance.testing;

import org.graded_classes.graded_attendance.GradedResourceLoader;
import org.graded_classes.graded_attendance.data.SqlFileReader;

import java.time.LocalDate;

public class SqlTesting {
    public static void main(String[] args) {
        var v = new SqlFileReader(GradedResourceLoader.loadURL("data/Attendance.sql").getPath()).getQuery();
        var date = LocalDate.now();
        var last_date = date.getMonth().maxLength();
        var start_date = date.getDayOfMonth();
        for (int i = start_date; i <= last_date; i++) {
            System.out.println(v.replace("_DATE_",date.toString()));
            date= date.plusDays(1);
        }

    }
}
