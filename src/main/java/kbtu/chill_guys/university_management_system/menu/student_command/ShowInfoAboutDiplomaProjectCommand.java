package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

public class ShowInfoAboutDiplomaProjectCommand implements Command {

    private StudentView view;

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        view = ViewFactory.getStudentView(language);

        GraduateStudent student = (GraduateStudent) Menu.getInstance().getLoggedUser();
        if(student.getProject() == null){
            student.setProject(new DiplomaProject());
        }
        view.showDiploma(student.getProject(), student);
    }
}