package org.graded_classes.graded_attendance.data;

import org.graded_classes.graded_attendance.GradedResourceLoader;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.Locale;

public class DataLoader {
    public LinkedHashMap<String, Student> getStudentData() {
        return studentData;
    }

    LinkedHashMap<String, Student> studentData = new LinkedHashMap<>();
    LocalDate today = LocalDate.now();
    Statement statement;

    public DataLoader() {
        Connection connection = null;
        String monthName = today.getMonth().getDisplayName(TextStyle.SHORT, Locale.UK) + "-" + today.getYear();

        System.out.println(monthName);
        String root_path = "G:/My Drive/Graded/data/";

        try {
            if (!new File(root_path).exists())
                System.out.println(new File(root_path).mkdirs());
            String path = root_path + "/" + monthName + "/";
            if (!new File(path).exists())
                System.out.println(new File(path).mkdir());
            connection = DriverManager.getConnection("jdbc:sqlite:" + root_path + "MyTestDataBase.db");
            var attendance = DriverManager.getConnection("jdbc:sqlite:" + root_path + "/" + monthName + "/" + "Graded_attendance.db");
            var att_statement = attendance.createStatement();
            att_statement.setQueryTimeout(30);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(new SqlFileReader(GradedResourceLoader.loadURL("data/GradedData.sql").getPath()).getQuery());
            statement.executeUpdate(new SqlFileReader(GradedResourceLoader.loadURL("data/TimeTable.sql").getPath()).getQuery());
            att_statement.executeUpdate(new SqlFileReader(GradedResourceLoader.loadURL("data/Attendance.sql").getPath()).getQuery().formatted(LocalDate.now().toString(), monthName));
            att_statement.executeUpdate(new SqlFileReader(GradedResourceLoader.loadURL("data/Fee.sql").getPath()).getQuery().formatted("FEE_" + monthName));
            statement.executeUpdate("ATTACH DATABASE 'G:/My Drive/Graded/data/Feb-2025/Graded_attendance.db' as 'TEST'");
            if (statement.executeQuery("select count(*) from TEST.'%s'".formatted(LocalDate.now().toString())).getInt("count(*)") == 0)
                System.out.println(statement.executeUpdate("""
                        INSERT INTO TEST.'%s'("UID","NAME","CLASS") select "UID","NAME","CLASS" from StudentData;
                        """.formatted(LocalDate.now().toString())));
            ResultSet rs = statement.executeQuery("select * from StudentData");
            //ResultSet rs1 = statement1.executeQuery("select * from TimeTable6");
            while (rs.next()) {
                studentData.put(rs.getString("UID"), new Student(rs.getString("UID"),
                        rs.getString("NAME"), rs.getString("CLASS"),
                        rs.getString("USER_ID")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
