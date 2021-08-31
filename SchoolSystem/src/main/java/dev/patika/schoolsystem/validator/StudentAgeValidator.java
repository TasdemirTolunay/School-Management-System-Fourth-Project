package dev.patika.schoolsystem.validator;

import dev.patika.schoolsystem.ageconstraint.StudentAgeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class StudentAgeValidator implements ConstraintValidator<StudentAgeConstraint, LocalDate> {
    @Override
    public void initialize(StudentAgeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {

        int nowYear  = LocalDate.now().getYear();
        int dateYear = date.getYear();
        int age = nowYear - dateYear;
        return age >= 18 && age <= 40;

    }


}
