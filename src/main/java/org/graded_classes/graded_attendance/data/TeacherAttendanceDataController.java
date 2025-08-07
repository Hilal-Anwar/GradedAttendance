package org.graded_classes.graded_attendance.data;

public class TeacherAttendanceDataController {
    DatabaseLoader teacherAttendance;
    public TeacherAttendanceDataController(String rootPath) {
          teacherAttendance=new DatabaseLoader("TeacherAttendance", rootPath);
    }
}
