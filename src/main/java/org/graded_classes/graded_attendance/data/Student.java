package org.graded_classes.graded_attendance.data;

public record Student(String ed_no,
                      String name,
                      String email,
                      String bloodGroup,
                      String guardian_phone,
                      String aadhar_no,
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
    @Override
    public String toString() {
        return String.format(
                "INSERT INTO \"StudentData\" (ed_no, name, email, bloodGroup, guardian_phone, aadhaar_no, father_name, mother_name, class, gender, dob, address, father_occ, mother_occ, previous_ins_name, reason_leaving, school_n, suggestions, subjects, telegram_id) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                ed_no, name, email, bloodGroup, guardian_phone, aadhar_no, father_name, mother_name, _class, gender, dob, address, father_occ, mother_occ, previous_ins_name, reason_leaving, school_n, suggestions, String.join(", ", subjects), telegram_id
        );
    }
  /*  public Student(String uid, String name, String aClass, String userId) {
        this(uid, name, aClass, userId,
                null, null, null,
                null, null, null, null,
                null, null, null,
                null, null, null, null, null, null);

    }*/

}
