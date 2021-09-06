package dev.patika.schoolsystem.exceptions;


/**
 * While save a new course, if the newly entered course code is the same as the code of one of the registered courses, "The course already exists error" is thrown.
 * While updating a course, if the newly entered course code is the same as the code of one of the registered courses, the error "The course already exists" is thrown.
 */
public class CourseIsAlreadyExistException extends RuntimeException{

    public CourseIsAlreadyExistException(String message) {
        super(message);
    }

}
