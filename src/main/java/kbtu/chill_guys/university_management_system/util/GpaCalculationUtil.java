package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.exception.InvalidGpaException;

public class GpaCalculationUtil {

    private GpaCalculationUtil() {
    }

    public static Gpa fromNumeric(double numericGpa) {
        if (numericGpa > 4.0 || numericGpa < 0.0) {
            throw new InvalidGpaException("GPA must be in the range 0.0 to 4.0");
        }

        if (numericGpa >= 3.85) return Gpa.A;
        if (numericGpa >= 3.7) return Gpa.A_MINUS;
        if (numericGpa >= 3.3) return Gpa.B_PLUS;
        if (numericGpa >= 3.0) return Gpa.B;
        if (numericGpa >= 2.7) return Gpa.B_MINUS;
        if (numericGpa >= 2.3) return Gpa.C_PLUS;
        if (numericGpa >= 2.0) return Gpa.C;
        if (numericGpa >= 1.7) return Gpa.C_MINUS;
        if (numericGpa >= 1.3) return Gpa.D_PLUS;
        if (numericGpa >= 1.0) return Gpa.D;
        if (numericGpa >= 0.7) return Gpa.D_MINUS;

        return Gpa.F;
    }
}