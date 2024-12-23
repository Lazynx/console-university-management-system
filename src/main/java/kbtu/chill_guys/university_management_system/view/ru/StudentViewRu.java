package main.java.kbtu.chill_guys.university_management_system.view.ru;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.*;

public class StudentViewRu implements StudentView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAvailableDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Нет доступных дисциплин для регистрации.");
            return;
        }

        System.out.println("Доступные дисциплины для регистрации:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Код: %s, Кредиты: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }
    }

    @Override
    public List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines) {
        System.out.println("Введите номера дисциплин для регистрации (через запятую):");
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
                    System.out.println("Дисциплины не выбраны. Попробуйте снова.");
                } else {
                    return selectedDisciplines;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите номера дисциплин, разделенные запятыми.");
            }
        }
    }

    @Override
    public void displayRegistrationConfirmation(List<Discipline> registeredDisciplines) {
        System.out.println("Вы успешно зарегистрировались на следующие дисциплины:");
        registeredDisciplines.forEach(discipline ->
                System.out.printf("- %s (Код: %s, Кредиты: %d)%n",
                        discipline.getName(), discipline.getCode(), discipline.getCredits()));
    }

    @Override
    public void displayInfo(Student student) {
        System.out.println("Здравствуйте, " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Ваш ID: " + student.getId());
        System.out.println("Длительность обучения: " + student.getStudyDuration());
        System.out.println("Школа: " + student.getSchool());
        System.out.println("Организация: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());
    }

    @Override
    public void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester) {
        System.out.println("\n=== Зарегистрированные дисциплины ===");
        System.out.println("Семестр: " + semester);

        if (disciplines.isEmpty()) {
            System.out.println("Вы не зарегистрированы на дисциплины в этом семестре.");
            return;
        }

        showDisciplineList(disciplines);
    }


    @Override
    public Semester getSemester(Set<Semester> semesters) {
        System.out.println("Хотите посмотреть дисциплины за определенный семестр или все семестры?");
        System.out.println("1. Определенный семестр");
        System.out.println("2. Все семестры");

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, 2);

        if (choice == 2) {
            return null;
        }

        if (semesters.isEmpty()) {
            System.out.println("Нет доступных семестров.");
            return null;
        }

        System.out.println("Выберите семестр:");
        List<Semester> semesterList = new ArrayList<>(semesters);
        for (int i = 0; i < semesterList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, semesterList.get(i));
        }

        int semesterChoice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, semesterList.size());
        return semesterList.get(semesterChoice - 1);
    }

    @Override
    public void showExactDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Нет зарегистрированных дисциплин для выбранного семестра.");
            return;
        }

        System.out.println("\n=== Зарегистрированные дисциплины для выбранного семестра ===");
        showDisciplineList(disciplines);
    }

    @Override
    public void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester) {
        if (disciplinesBySemester == null || disciplinesBySemester.isEmpty()) {
            System.out.println("Нет зарегистрированных дисциплин по семестрам.");
            return;
        }

        System.out.println("\n=== Все зарегистрированные дисциплины ===");
        for (Map.Entry<Semester, List<Discipline>> entry : disciplinesBySemester.entrySet()) {
            Semester semester = entry.getKey();
            List<Discipline> disciplines = entry.getValue();

            System.out.println("Семестр: " + semester);
            showDisciplineList(disciplines);
        }
    }

    @Override
    public void showDiploma(DiplomaProject project, GraduateStudent student) {
        while (true) {
            System.out.println("\n=== Детали дипломного проекта ===");
            System.out.printf("Тема: %s%n", project.getTitle());
            System.out.printf("Описание: %s%n", project.getDescription());
            System.out.printf("Научный руководитель: %s%n",
                    project.getSupervisor() != null ? project.getSupervisor().getFirstName() : "Не назначен");
            System.out.println("Опубликованные статьи:");
            if (project.getPublishedPapers().isEmpty()) {
                System.out.println("  Опубликованных статей нет.");
            } else {
                for (int i = 0; i < project.getPublishedPapers().size(); i++) {
                    ResearchPaper paper = project.getPublishedPapers().get(i);
                    System.out.printf("  %d. %s (DOI: %s)%n", i + 1, paper.getTitle(), paper.getDoi());
                }
            }

            System.out.println("\nДействия:");
            System.out.println("1. Изменить тему");
            System.out.println("2. Изменить описание");
            System.out.println("3. Добавить статьи");
            System.out.println("4. Выйти и сохранить изменения");

            int choice = InputValidatorUtil.validateIntegerInput("Выберите действие: ", 1, 4);

            switch (choice) {
                case 1 -> {
                    System.out.println("Введите новую тему:");
                    String newTitle = InputValidatorUtil.validateNonEmptyInput("Тема не может быть пустой.");
                    project.setTitle(newTitle);
                    System.out.println("Тема успешно обновлена.");
                }
                case 2 -> {
                    System.out.println("Введите новое описание:");
                    String newDescription = InputValidatorUtil.validateNonEmptyInput("Описание не может быть пустым.");
                    project.setDescription(newDescription);
                    System.out.println("Описание успешно обновлено.");
                }
                case 3 -> {
                    System.out.println("Выберите статьи для добавления:");
                    List<ResearchPaper> availablePapers = ResearcherService.getInstance().getResearchPapers(student)
                            .stream().filter(researchPaper -> !project.getPublishedPapers().contains(researchPaper))
                            .toList();
                    Vector<ResearchPaper> selectedPapers = selectResearchPapers(availablePapers);
                    project.getPublishedPapers().addAll(selectedPapers);
                    System.out.println("Статьи успешно добавлены в дипломный проект.");
                }
                case 4 -> {
                    System.out.println("Выход из просмотра дипломного проекта.");
                    return;
                }
            }
        }
    }

    @Override
    public String getOrganizationName() {
        System.out.println("Введите название организации:");
        return InputValidatorUtil.validateNonEmptyInput("Введите непустое название.");
    }

    @Override
    public void showAlreadyExistingOrganizationName() {
        System.out.println("Это название уже существует. Пожалуйста, введите уникальное название.");
    }

    @Override
    public String getOrganizationDescription() {
        System.out.println("Введите описание организации:");
        return InputValidatorUtil.validateNonEmptyInput("Введите непустое описание.");
    }

    @Override
    public Organization selectOrganization(List<Organization> organizations) {
        if (organizations == null || organizations.isEmpty()) {
            System.out.println("Нет доступных организаций.");
            return null;
        }

        System.out.println("\n=== Доступные организации ===");
        for (int i = 0; i < organizations.size(); i++) {
            Organization organization = organizations.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, organization.getName(), organization.getDescription());
            if (organization.getMembers().isEmpty()) {
                System.out.println("   Участники: Пока нет участников.");
            } else {
                System.out.println("   Участники:");
                organization.getMembers().forEach((student, role) ->
                        System.out.printf("      - %s %s (%s)%n", student.getFirstName(), student.getLastName(), role));
            }
        }
        System.out.println("Выберите организацию по номеру или нажмите 0 для отмены: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Введите корректный номер: ", 0, organizations.size());

        if (choice == 0) {
            System.out.println("Выбор отменён.");
            return null;
        }

        return organizations.get(choice - 1);
    }

    @Override
    public OrganizationRole selectOrganizationRole(List<OrganizationRole> availableRoles) {
        if (availableRoles.isEmpty()) {
            System.out.println("Нет доступных ролей.");
            return null;
        }

        System.out.println("\n=== Доступные роли ===");
        for (int i = 0; i < availableRoles.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableRoles.get(i));
        }
        System.out.println("========================\n");
        System.out.println("Выберите номер роли: ");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Введите корректный номер: ", 1, availableRoles.size());

        return availableRoles.get(choice - 1);
    }

    @Override
    public void showOrganizationInfo(List<Organization> organizations) {
        if (organizations.isEmpty()) {
            System.out.println("Вы не являетесь членом ни одной организации.");
            return;
        }

        System.out.println("\n=== Информация об организациях ===");

        for (Organization organization : organizations) {
            System.out.printf("Название: %s%n", organization.getName());
            System.out.printf("Описание: %s%n", organization.getDescription());
            System.out.println("Участники:");
            if (organization.getMembers().isEmpty()) {
                System.out.println("  В этой организации пока нет участников.");
            } else {
                organization.getMembers().forEach((student, role) -> {
                    System.out.printf("  - %s %s (Роль: %s)%n",
                            student.getFirstName(), student.getLastName(), role.name());
                });
            }
            System.out.println("=".repeat(50));
        }
    }

    @Override
    public void showClosedRegistration() {
        System.out.println("Регистрация закрыта!");
    }

    @Override
    public void noneAvailableDisciplines() {
        System.out.println("Нету подходящих дисциплин");
    }


    private Vector<ResearchPaper> selectResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("Нет доступных статей.");
            return new Vector<>();
        }

        System.out.println("Доступные статьи:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
        }

        System.out.println("Введите номера статей (через запятую) или нажмите Enter для пропуска:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        Vector<ResearchPaper> selectedPapers = new Vector<>();
        if (!input.isEmpty()) {
            try {
                String[] indices = input.split(",");
                for (String index : indices) {
                    int paperIndex = Integer.parseInt(index.trim()) - 1;
                    if (paperIndex >= 0 && paperIndex < papers.size()) {
                        selectedPapers.add(papers.get(paperIndex));
                    } else {
                        System.out.println("Некорректный номер статьи: " + (paperIndex + 1));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пропуск выбора статей.");
            }
        }

        return selectedPapers;
    }


    private void showDisciplineList(List<Discipline> disciplines) {
        System.out.printf("%-10s %-30s %-10s %-15s%n", "Код", "Название", "Кредиты", "Тип курса");
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
        System.out.println("Семестр не выбран.");
    }

    @Override
    public void showDiscipline(Discipline discipline) {
        System.out.printf("Дисциплина: %s (Код: %s, Кредиты: %d, Семестр: %s, Тип курса: %s)%n",
                discipline.getName(), discipline.getCode(), discipline.getCredits(),
                discipline.getSemester(), discipline.getCourseType());
    }

    @Override
    public void showNoDisciplinesAvailableMessage() {
        System.out.println("Дисциплины отсутствуют.");
    }

    @Override
    public void showMarksHeader() {
        System.out.printf("%-10s %-10s %-15s %-10s %-20s%n", "Дата", "Урок", "Посещаемость", "Оценка", "Комментарий");
    }

    @Override
    public void showMarkRow(LessonRecord record) {
        System.out.printf("%-10s %-10s %-15s %-10.2f %-20s%n",
                record.getDate(), record.getLesson(), record.getAttendance(), record.getGrade(), record.getComment());
    }

    @Override
    public void showMarksFooter(double totalMarks, int totalPresence, int totalAbsence) {
        System.out.println("------------------------------------------------");
        System.out.printf("Общая сумма оценок: %.2f%n", totalMarks);
        System.out.printf("Общее количество присутствий: %d%n", totalPresence);
        System.out.printf("Общее количество отсутствий: %d%n", totalAbsence);
        System.out.println("================================================");
    }

    @Override
    public void showNoMarksMessage(Discipline discipline) {
        System.out.println("Оценки по дисциплине: " + discipline.getName() + " отсутствуют!");
    }

    @Override
    public void showSemesterHeader(Semester semester) {
        System.out.printf("\n=== Семестр: %s ===\n", semester.toString());
    }

    @Override
    public void showDisciplineWithoutGrades(Discipline discipline) {
        System.out.printf("Дисциплина: %s (Оценки недоступны; аттестация еще не закрыта)%n", discipline.getName());
    }

    @Override
    public void showTranscriptRecordWithGrades(Transcript transcript) {
        System.out.printf(
                "Дисциплина: %s | Итоговая оценка: %.2f | GPA: %s | Традиционная оценка: %s%n",
                transcript.getSubject().getName(),
                transcript.getTotalGrade(),
                transcript.getGpaLetter(),
                transcript.getGpaTraditional()
        );
    }

    @Override
    public Teacher selectTeacher(List<Teacher> teachers) {
        System.out.println("Выберите преподавателя для оценки:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.printf("%d. %s %s (Рейтинг: %s)%n", i + 1, teachers.get(i).getFirstName(), teachers.get(i).getLastName(), teachers.get(i).getRating());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Введите ваш выбор: ", 1, teachers.size());
        return teachers.get(choice - 1);
    }

    @Override
    public int getTeacherRating() {
        System.out.println("Введите оценку преподавателю (0-100):");
        return InputValidatorUtil.validateIntegerInput("Неверная оценка. Пожалуйста, введите число от 0 до 100.", 0, 100);
    }

    @Override
    public void showTeacherRatedMessage(Teacher teacher, int rating) {
        System.out.printf("Вы оценили %s %s на %d баллов. Их новый рейтинг: %s.%n",
                teacher.getFirstName(),
                teacher.getLastName(),
                rating,
                teacher.getRating()
        );
    }

    @Override
    public void showNoTeachersAvailableMessage() {
        System.out.println("Нет доступных учителей.");
    }
}
