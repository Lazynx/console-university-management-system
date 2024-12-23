package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.util.List;

public interface TeacherView {
    Semester selectSemester();

    void showNoSemesterSelectedMessage();

    Discipline selectDiscipline(List<Discipline> disciplines);

    Student selectStudent(List<Student> students);

    LessonRecord createLessonRecord();

    void showNoDisciplinesMessage(Semester semester);

    void showNoStudentsMessage(Discipline discipline);

    void showRecordAddedMessage(LessonRecord record);

    void showStudentRecords(Student student, List<LessonRecord> lessonRecords);

    void showTranscriptUpdatedMessage(Student student, Discipline discipline, Transcript transcript);

    void showAttestationClosedMessage(Discipline discipline, Semester semester);

    boolean confirmAttestationClosure();

    void showTeacherRating(Teacher teacher);

    String getComment();

    UrgencyLevel selectUrgencyLevel();

    void showComplaintCreatedMessage(Discipline discipline, Student student, UrgencyLevel urgencyLevel);
}
