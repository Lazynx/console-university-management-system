package universityManagementSystem.models.employee;

import universityManagementSystem.models.academic.Complaint;
import universityManagementSystem.permissions.CanViewRequests;
import universityManagementSystem.permissions.CanViewStudents;

import java.util.Vector;

public class Dean implements CanViewRequests, CanViewStudents {
    public Vector<Complaint> ViewComplaints() {
        //TODO
        return null;
    }
    
    public void SignRequest() {
        //TODO
    }
    
    public void RejectRequest() {
        //TODO
    }
}
