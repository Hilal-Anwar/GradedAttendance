package org.graded_classes.graded_attendance.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public record Student(String ed_no,
                      String name,
                      String email,
                      String bloodGroup,
                      String guardian_phone,
                      String aadhaar_no,
                      String father_name,
                      String mother_name,
                      String _class,
                      String gender,
                      String dob,
                      String address,
                      String father_occ,
                      String mother_occ,
                      String previous_ins_name,
                      String reason_leaving,
                      String school_n,
                      String suggestions,
                      String[] subjects,
                      String telegram_id) {
   /* @Override
    public String toString() {
        return String.format(
                "INSERT INTO \"StudentData\" (ed_no, name, email, bloodGroup, guardian_phone, aadhaar_no, father_name, mother_name, class, gender, dob, address, father_occ, mother_occ, previous_ins_name, reason_leaving, school_n, suggestions, subjects, telegram_id) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                ed_no, name, email, bloodGroup, guardian_phone, aadhaar_no, father_name, mother_name, _class, gender, dob, address, father_occ, mother_occ, previous_ins_name, reason_leaving, school_n, suggestions, String.join(", ", subjects), telegram_id
        );
    }*/

    public void insertIntoDatabase(Connection connection) throws SQLException {
        String sql = "INSERT INTO \"StudentData\"(ed_no, name,email, bloodGroup, guardian_phone, aadhaar_no, father_name, mother_name, class, gender, dob, address, father_occ, mother_occ, previous_ins_name, reason_leaving, school_n, suggestions, subjects, telegram_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ed_no);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, bloodGroup);
            stmt.setString(5, guardian_phone);
            stmt.setString(6, aadhaar_no);
            stmt.setString(7, father_name);
            stmt.setString(8, mother_name);
            stmt.setString(9, _class);
            stmt.setString(10, gender);
            stmt.setString(11, dob);
            stmt.setString(12, address);
            stmt.setString(13, father_occ);
            stmt.setString(14, mother_occ);
            stmt.setString(15, previous_ins_name);
            stmt.setString(16, reason_leaving);
            stmt.setString(17, school_n);
            stmt.setString(18, suggestions);
            stmt.setString(19, String.join(", ", subjects)); // Convert list to comma-separated string
            stmt.setString(20, telegram_id);

            stmt.executeUpdate();
        }
    }

  /*  public Student(String uid, String name, String aClass, String userId) {
        this(uid, name, aClass, userId,
                null, null, null,
                null, null, null, null,
                null, null, null,
                null, null, null, null, null, null);

    }*/

}
