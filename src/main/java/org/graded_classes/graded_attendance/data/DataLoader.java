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
            statement.executeUpdate(new SqlFileReader("data/GradedData.sql").getQuery());
            statement.executeUpdate(new SqlFileReader("data/TimeTable.sql").getQuery());
            att_statement.executeUpdate(new SqlFileReader("data/Attendance.sql").getQuery().
                    formatted(LocalDate.now().toString(), monthName));
            att_statement.executeUpdate(new SqlFileReader("data/Fee.sql").getQuery().
                    formatted("FEE_" + monthName));
            loadData();
            //statement.executeUpdate("ATTACH DATABASE 'G:/My Drive/Graded/data/Feb-2025/Graded_attendance.db' as 'TEST'");
           /* if (statement.executeQuery("select count(*) from TEST.'%s'".formatted(LocalDate.now().toString())).getInt("count(*)") == 0)
                System.out.println(statement.executeUpdate("""
                        INSERT INTO TEST.'%s'("UID","NAME","CLASS") select "UID","NAME","CLASS" from StudentData;
                        """.formatted(LocalDate.now().toString())));
            ResultSet rs = statement.executeQuery("select * from StudentData");
            //ResultSet rs1 = statement1.executeQuery("select * from TimeTable6");
            while (rs.next()) {
               *//* studentData.put(rs.getString("UID"), new Student(rs.getString("UID"),
                        rs.getString("NAME"), rs.getString("CLASS"),
                        rs.getString("USER_ID")));*//*
            }*/
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void removeStudent(Student student){
        try {
            System.out.println("DELETE FROM \"StudentData\"  WHERE \"ed_no\"  = \"%s\";".formatted(student.ed_no()));
            statement.executeUpdate("DELETE FROM \"StudentData\"  WHERE \"ed_no\"  = \"%s\";".formatted(student.ed_no()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addStudent(Student student) {
        System.out.println(statement.toString());
        try {
            System.out.println(student);
            studentData.put(student.ed_no(), student);
            student.insertIntoDatabase(statement.getConnection());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student);
    }

    private void loadData() throws SQLException {
        ResultSet rs = statement.executeQuery("select * from StudentData");
        while (rs.next()) {
            studentData.put(rs.getString("ed_no"), new Student(
                    rs.getString("ed_no"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("bloodGroup"),
                    rs.getString("guardian_phone"),
                    rs.getString("aadhaar_no"),
                    rs.getString("father_name"),
                    rs.getString("mother_name"),
                    rs.getString("class"),
                    rs.getString("gender"),
                    rs.getString("dob"),
                    rs.getString("address"),
                    rs.getString("father_occ"),
                    rs.getString("mother_occ"),
                    rs.getString("previous_ins_name"),
                    rs.getString("reason_leaving"),
                    rs.getString("school_n"),
                    rs.getString("suggestions"),
                    rs.getString("subjects").split(", "), // Assuming subjects are stored as a comma-separated string
                    rs.getString("telegram_id")
            ));
        }
    }

}
