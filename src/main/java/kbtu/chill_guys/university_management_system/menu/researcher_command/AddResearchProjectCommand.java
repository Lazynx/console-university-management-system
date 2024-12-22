package main.java.kbtu.chill_guys.university_management_system.menu.researcher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.view.ResearcherView;

public class AddResearchProjectCommand implements Command {
    private final ResearcherService service = ResearcherService.getInstance();

    @Override
    public void execute() {
        Language language = Menu.getInstance().getLanguage();
        ResearcherView view = ViewFactory.getResearcherView(language);
        ResearchProject project = view.getResearchProject();
        service.addNewResearchProject(project);
    }
}
