package dev.patika.schoolsystem.exceptions;


/**
 * There can be a maximum of 20 students in a course.
 * If more than 20 students are requested to register, the error "Student Number For One Course Exceeded" will be thrown.
 */
public class StudentNumberForOneCourseExceededException extends RuntimeException{

    public StudentNumberForOneCourseExceededException(String message) {
        super(message);
    }

}
