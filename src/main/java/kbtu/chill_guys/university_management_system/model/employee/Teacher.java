package universityManagementSystem.models.employee;

import universityManagementSystem.permissions.CanViewCourses;
import universityManagementSystem.permissions.CanViewStudents;

public class Teacher implements CanViewCourses, CanViewStudents {
    private Rating rating;
    private School school;
    private TeachingDegree teachingDegree;

    public Rating getRating() {
        return this.rating;
    }

    public Rating setRating(Rating rating) {
        this.rating = rating;
    }

    public School getSchool() {
        return this.school;

    }
    public void setSchool(School school) {
        this.school = school;
    }
    public TeachingDegree getTeachingDegree() {
        return this.teachingDegree;
    }
    public void setTeachingDegree(TeachingDegree teachingDegree) {
        this.teachingDegree = teachingDegree;
    }
}
