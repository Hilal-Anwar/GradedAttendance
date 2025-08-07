package org.graded_classes.graded_attendance.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseStatement {

    Statement statement;
    Connection connection;

    public DatabaseStatement(Connection connection) {
        this.connection = connection;
        try {
            statement=connection.createStatement();
            statement.setQueryTimeout(30);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
