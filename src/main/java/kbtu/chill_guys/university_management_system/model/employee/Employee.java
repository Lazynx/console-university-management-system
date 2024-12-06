package universityManagementSystem.models.employee;
import universityManagementSystem.models.BaseUser;
import universityManagementSystem.models.student.Student;
import universityManagementSystem.permission.CanViewCourses;

import java.util.Vector;

public class Employee extends BaseUser implements CanViewCourses {
    private Integer salary;
    private Teacher teacher;

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Integer getSalary() {
        return this.salary;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Teacher getTeacher() {
        return this.teacher;
    }
}
