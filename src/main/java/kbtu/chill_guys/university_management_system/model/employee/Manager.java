package universityManagementSystem.models.employee;

import universityManagementSystem.permission.CanViewRequests;
import universityManagementSystem.permission.CanViewTeachers;
import universityManagementSystem.enumeration.organization.ManagerType;

public class Manager implements CanViewRequests, CanViewTeachers {
    private ManagerType ManagerType;

    public ManagerType getManagerType() {
        return this.ManagerType;
    }

    public void setManagerType(ManagerType ManagerType) {
        this.ManagerType = ManagerType;
    }
}
