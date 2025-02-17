package org.graded_classes.graded_attendance.data;

public class TimeTableDataHolder {
    private String[] data;

    public TimeTableDataHolder(String... data) {
        this.data = data;
    }


    public String[] getTimeSlots() {
        return data;
    }

    public void setTimeSlots(String[] data) {
        this.data = data;
    }
}
