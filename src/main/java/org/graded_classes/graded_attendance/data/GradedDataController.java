package org.graded_classes.graded_attendance.data;


public class GradedDataController {
    private final  String root_path="G:/My Drive/Graded/data/";
    StudentAttendanceDataController studentAttendanceDataController;
    TeacherAttendanceDataController teacherAttendanceDataController;
    TeachingProgressionDataController teachingProgressionDataController;
    DatabaseLoader rootDataBase = new DatabaseLoader(root_path,
            "GradedDatabase.db");

    public GradedDataController() {
        studentAttendanceDataController=new StudentAttendanceDataController(root_path);
        teacherAttendanceDataController=new TeacherAttendanceDataController(root_path);
        teachingProgressionDataController=new TeachingProgressionDataController(root_path);
        init();
    }
    private void init() {

    }
    private void createTableForTeacherStudentAndTiming() {

    }
    public boolean addStudent(Student student) {
        return false;
    }
    public boolean addTeacher(Teacher teacher) {
        return false;
    }
    public boolean removeStudent(Student student) {
        return false;
    }
    public boolean removeTeacher(Teacher teacher) {
        return false;
    }
}