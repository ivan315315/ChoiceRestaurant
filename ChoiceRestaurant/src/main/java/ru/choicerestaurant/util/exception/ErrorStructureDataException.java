package ru.choicerestaurant.util.exception;

import ru.choicerestaurant.repository.RoleRep;

public class ErrorStructureDataException extends RuntimeException{
    public ErrorStructureDataException(String message){
        super(message);
    }
}
