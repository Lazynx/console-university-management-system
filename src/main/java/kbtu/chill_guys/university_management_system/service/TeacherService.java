package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.*;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.TeacherDisciplineStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherService {

    private final TeacherDisciplineStorage storage = TeacherDisciplineStorage.getInstance();
    private final UserRepository userRepository = new UserRepository();

    public List<Discipline> getDisciplines(Teacher teacher, Semester semester) {
        return storage.getDisciplines(teacher, semester);
    }

    public List<Student> getStudents(Teacher teacher, Semester semester, Discipline discipline) {
        return storage.getStudentsInDiscipline(teacher, semester, discipline);
    }

    public void addLessonRecord(Teacher teacher, Semester semester, Discipline discipline, Student student, LocalDate date, String lessonName, Attendance attendance, double grade, String comment) {
        LessonRecord record = new LessonRecord(date, lessonName, attendance, grade, comment);
        storage.addLessonRecord(teacher, semester, discipline, student, record);
    }

    public void assignDisciplineToTeacher(Teacher teacher, Discipline discipline) {
        TeacherDisciplineStorage storage = TeacherDisciplineStorage.getInstance();
        storage.assignDisciplineToTeacher(teacher, discipline.getSemester(), discipline);
    }

    public List<Teacher> getAllTeachers() {
        return userRepository.findUsersByRole(UserRole.TEACHER).stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .collect(Collectors.toList());
    }
}