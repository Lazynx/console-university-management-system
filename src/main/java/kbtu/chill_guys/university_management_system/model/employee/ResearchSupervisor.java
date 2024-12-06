package universityManagementSystem.models.employee;

import universityManagementSystem.models.research.ResearchPaper;
import universityManagementSystem.models.research.ResearchProject;
import universityManagementSystem.models.student.GraduateStudent;
import universityManagementSystem.models.student.research.Researcher;

import java.util.Vector;

public class ResearchSupervisor implements Researcher {
    private Integer hIndex;
    private Vector<ResearchProject> supervisedProjects ;
    private Vector<GraduateStudent> supervisedStudents ;
    private Vector<ResearchPaper> supervisedResearchPapers;

    public Integer getHIndex() {
        return this.hIndex;
    }

    public void setHIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }

    public Vector<ResearchProject> getSupervisedProjects () {
        return this.supervisedProjects ;
    }

    public void setSupervisedProjects (Vector<ResearchProject> supervisedProjects ) {
        this.supervisedProjects  = supervisedProjects ;
    }

    public Vector<GraduateStudent> getSupervisedStudents () {
        return this.supervisedStudents ;
    }

    public void setSupervisedStudents (Vector<GraduateStudent> supervisedStudents ) {
        this.supervisedStudents  = supervisedStudents ;
    }

    public Vector<ResearchPaper> getSupervisedResearchPapers() {
        return this.supervisedResearchPapers;
    }

    public void setSupervisedResearchPapers(Vector<ResearchPaper> supervisedResearchPapers) {
        this.supervisedResearchPapers = supervisedResearchPapers;
    }
}
