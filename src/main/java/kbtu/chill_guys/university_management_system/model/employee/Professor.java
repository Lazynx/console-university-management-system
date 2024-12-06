package universityManagementSystem.models.employee;

import universityManagementSystem.models.research.ResearchProject;
import universityManagementSystem.models.research.ResearchPaper;
import universityManagementSystem.models.student.research.Researcher;

import java.util.Vector;


public class Professor implements Researcher {
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }
}
