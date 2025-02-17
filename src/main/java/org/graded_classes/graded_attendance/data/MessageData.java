package org.graded_classes.graded_attendance.data;

import org.graded_classes.graded_attendance.GradedResourceLoader;

import java.sql.*;
import java.util.HashSet;

public class MessageData {
    Statement statement;
    HashSet<Long> studentIds = new HashSet<>();

    public Statement getStatement() {
        return statement;
    }

    public HashSet<Long> getStudentIds() {
        return studentIds;
    }

    public MessageData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:G:/My Drive/Graded/data/Contact.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(new SqlFileReader(GradedResourceLoader.loadURL("data/Contact.sql").getPath()).getQuery());
            init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws SQLException {
        ResultSet rs = statement.executeQuery("select * from STUDENT_CONTACT");
        while (rs.next()) {
            studentIds.add(rs.getLong("T_ID"));
        }
    }

    public void add(long studentId, String user_name, String name, String _class) {
        if (!studentIds.contains(studentId)) {
            try {
                statement.executeUpdate("insert into STUDENT_CONTACT values('" + studentId + "','" + user_name + "','" + name + "','" + _class + "')");
                studentIds.add(studentId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
