package org.graded_classes.graded_attendance;

public enum R {
    add_people("fxml/add_people.fxml"), add_student("fxml/add_student.fxml"),
    custom_data_view("fxml/custom_data_view.fxml"), fee_report("fxml/fee_report.fxml"), home_layout("fxml/home_tab.fxml"),
    new_student_layout("fxml/new_student_layout.fxml"), new_teacher_layout("fxml/new_teacher_layout.fxml"), student_attendance_layout("fxml/student_attendance_layout.fxml"),
    student_fee_layout("fxml/student_fee_layout.fxml"),
    data_view("fxml/data_view.fxml"),
    teacher_attendance_layout("fxml/teacher_attendance_layout.fxml"),
    teacher_payment_layout("fxml/teachers_payment_layout.fxml"),
    teaching_progress_layout("fxml/teaching_progress_layout.fxml"),
    database_layout("fxml/database_edit.fxml"),
    chat_layout("fxml/chat_layout.fxml"),
    calendar_layout("fxml/calendar_layout.fxml");
    private final String path;

    R(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
