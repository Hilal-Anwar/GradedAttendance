package org.graded_classes.graded_attendance.data;

public class Attendance {
    String check_in;
    String check_out;
    String homework_status;

    public Attendance(String check_in, String check_out, String homework_status) {
        this.check_in = check_in;
        this.check_out = check_out;
        this.homework_status = homework_status;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public String getHomework_status() {
        return homework_status;
    }

    public void setHomework_status(String homework_status) {
        this.homework_status = homework_status;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }
}
