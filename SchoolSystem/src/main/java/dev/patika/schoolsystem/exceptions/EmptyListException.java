package dev.patika.schoolsystem.exceptions;

/**
 * If the list you want to find is empty, "the list is empty" error is thrown.
 */
public class EmptyListException extends RuntimeException{

    public EmptyListException(String message) {
        super(message);
    }

}
