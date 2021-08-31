package dev.patika.schoolsystem.ageconstraint;

import dev.patika.schoolsystem.validator.StudentAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = StudentAgeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentAgeConstraint {

    String message() default "Student cannot be younger than 18 and older than 40";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
