package dev.patika.schoolsystem.exceptions;

/**
 * While save a new instructor,
 * if the newly entered instructor phone number is the same as the phone number of one of the registered instructors,
 * "The instructor already exists error" is thrown.
 *
 * While updating a instructor,
 * if the newly entered instructor phone number is the same as the phone number of one of the registered courses,
 * the error "The instructor already exists" is thrown.
 */
public class InstructorIsAlreadyExistException extends RuntimeException{

    public InstructorIsAlreadyExistException(String message) {
        super(message);
    }

}
