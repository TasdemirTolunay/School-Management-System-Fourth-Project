package dev.patika.schoolsystem.exceptions;

/**
 * A "Student Age Not Valid" error is thrown if the student is younger than 18 or older than 40 when saving or updating.
 */
public class StudentAgeNotValidException extends RuntimeException{

    public StudentAgeNotValidException(String message) {
        super(message);
    }

}
