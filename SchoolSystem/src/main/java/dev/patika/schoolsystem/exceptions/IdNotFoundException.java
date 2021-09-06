package dev.patika.schoolsystem.exceptions;

/**
 * If there is no one with the id you want to find, the "Id Not Found" error is thrown.
 */
public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(String message) {
        super(message);
    }

}
