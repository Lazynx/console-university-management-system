package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.*;

public class StudentViewKz implements StudentView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAvailableDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Тіркелу үшін қолжетімді пәндер жоқ.");
            return;
        }

        System.out.println("Тіркелу үшін қолжетімді пәндер:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Коды: %s, Кредиттер: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }
    }

    @Override
    public List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines) {
        System.out.println("Тіркелу үшін пән нөмірлерін енгізіңіз (үтірмен бөлінген):");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(",");
                List<Discipline> selectedDisciplines = Arrays.stream(parts)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(i -> i >= 1 && i <= availableDisciplines.size())
                        .mapToObj(i -> availableDisciplines.get(i - 1))
                        .toList();

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("Пәндер таңдалған жоқ. Қайтадан көріңіз.");
                } else {
                    return selectedDisciplines;
                }
            } catch (NumberFormatException e) {
                System.out.println("Қате енгізу. Пәндердің нөмірлерін үтірмен бөлініп енгізіңіз.");
            }
        }
    }

    @Override
    public void displayRegistrationConfirmation(List<Discipline> registeredDisciplines) {
        System.out.println("Сіз келесі пәндерге сәтті тіркелдіңіз:");
        registeredDisciplines.forEach(discipline ->
                System.out.printf("- %s (Коды: %s, Кредиттер: %d)%n",
                        discipline.getName(), discipline.getCode(), discipline.getCredits()));
    }

    @Override
    public void displayInfo(Student student) {
        System.out.println("Сәлеметсіз бе, " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Сіздің ID: " + student.getId());
        System.out.println("Оқу ұзақтығы: " + student.getStudyDuration());
        System.out.println("Мектеп: " + student.getSchool());
        System.out.println("Ұйым: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());
    }

    @Override
    public void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester) {
        System.out.println("\n=== Тіркелген пәндер ===");
        System.out.println("Семестр: " + semester);

        if (disciplines.isEmpty()) {
            System.out.println("Осы семестрде пәндерге тіркелмегенсіз.");
            return;
        }

        showDisciplineList(disciplines);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Semester getSemester(Set<Semester> semesters) {
        System.out.println("Белгілі бір семестрдегі немесе барлық семестрлердегі пәндерді көргіңіз келе ме?");
        System.out.println("1. Белгілі бір семестр");
        System.out.println("2. Барлық семестрлер");

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, 2);

        if (choice == 2) {
            return null;
        }

        if (semesters.isEmpty()) {
            System.out.println("Қолжетімді семестрлер жоқ.");
            return null;
        }

        System.out.println("Семестрді таңдаңыз:");
        List<Semester> semesterList = new ArrayList<>(semesters);
        for (int i = 0; i < semesterList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, semesterList.get(i));
        }

        int semesterChoice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, semesterList.size());
        return semesterList.get(semesterChoice - 1);
    }

    @Override
    public void showExactDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Таңдалған семестрге тіркелген пәндер жоқ.");
            return;
        }

        System.out.println("\n=== Таңдалған семестр үшін тіркелген пәндер ===");
        showDisciplineList(disciplines);
    }

    @Override
    public void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester) {
        if (disciplinesBySemester == null || disciplinesBySemester.isEmpty()) {
            System.out.println("Семестрлер бойынша тіркелген пәндер жоқ.");
            return;
        }

        System.out.println("\n=== Барлық тіркелген пәндер ===");
        for (Map.Entry<Semester, List<Discipline>> entry : disciplinesBySemester.entrySet()) {
            Semester semester = entry.getKey();
            List<Discipline> disciplines = entry.getValue();

            System.out.println("Семестр: " + semester);
            showDisciplineList(disciplines);
        }
    }

    private void showDisciplineList(List<Discipline> disciplines) {
        System.out.printf("%-10s %-30s %-10s %-15s%n", "Код", "Атауы", "Кредиттер", "Курс түрі");
        System.out.println("---------------------------------------------------------------");

        for (Discipline discipline : disciplines) {
            System.out.printf("%-10s %-30s %-10d %-15s%n",
                    discipline.getCode(),
                    discipline.getName(),
                    discipline.getCredits(),
                    discipline.getCourseType());
        }
    }

    @Override
    public void showNoSemesterSelectedMessage() {
        System.out.println("Семестр таңдалмады.");
    }

    @Override
    public void showDiscipline(Discipline discipline) {
        System.out.printf("Пән: %s (Коды: %s, Кредиттер: %d, Семестр: %s, Курстың түрі: %s)%n",
                discipline.getName(), discipline.getCode(), discipline.getCredits(),
                discipline.getSemester(), discipline.getCourseType());
    }

    @Override
    public void showNoDisciplinesAvailableMessage() {
        System.out.println("Пәндер жоқ.");
    }

    @Override
    public void showMarksHeader() {
        System.out.printf("%-10s %-10s %-15s %-10s %-20s%n", "Күні", "Сабақ", "Қатысу", "Баға", "Пікір");
    }

    @Override
    public void showMarkRow(LessonRecord record) {
        System.out.printf("%-10s %-10s %-15s %-10.2f %-20s%n",
                record.getDate(), record.getLesson(), record.getAttendance(), record.getGrade(), record.getComment());
    }

    @Override
    public void showMarksFooter(double totalMarks, int totalPresence, int totalAbsence) {
        System.out.println("------------------------------------------------");
        System.out.printf("Бағалардың жалпы сомасы: %.2f%n", totalMarks);
        System.out.printf("Жалпы қатысу саны: %d%n", totalPresence);
        System.out.printf("Жалпы болмау саны: %d%n", totalAbsence);
        System.out.println("================================================");
    }

    @Override
    public void showNoMarksMessage(Discipline discipline) {
        System.out.println("Пән: "  + discipline.getName() + " бойынша бағалар жоқ!");
    }
}