package org.graded_classes.graded_attendance.controller;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class TimeTable {
    private String grade;
    final Map<String, Map<String, Object>> table;
    Connection connection;

    public TimeTable(String grade, Connection connection) {
        this.grade = grade;
        this.connection = connection;
        table = readTimeTable();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void updateTimeSlot(String day, String timeSlot, String subject) {
        String sql = String.format("UPDATE \"TimeTable%s\" SET [%s] = ? WHERE Day = ?", grade, timeSlot);
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, subject);
            pst.setString(2, day);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Map<String, Object>> getTable() {
        return table;
    }

    public Map<String, Map<String, Object>> readTimeTable() {
        Map<String, Map<String, Object>> timeTable = new LinkedHashMap<>();
        String sql = String.format("SELECT * FROM \"TimeTable%s\"", grade);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String day = rs.getString("Day");
                Map<String, Object> slots = new LinkedHashMap<>();
                slots.put("Day", day);
                slots.put("3:00 PM", getIfPresent(rs.getString("3:00 PM")));
                slots.put("4:00 PM", getIfPresent(rs.getString("4:00 PM")));
                slots.put("5:00 PM", getIfPresent(rs.getString("5:00 PM")));
                slots.put("6:00 PM", getIfPresent(rs.getString("6:00 PM")));
                slots.put("7:00 PM", getIfPresent(rs.getString("7:00 PM")));
                slots.put("8:00 PM", getIfPresent(rs.getString("8:00 PM")));

                timeTable.put(day, slots);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return timeTable;
    }

    private Object getIfPresent(String string) {
        return string == null ? "" : string;
    }

}
