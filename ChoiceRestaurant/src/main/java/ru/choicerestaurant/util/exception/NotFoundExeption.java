package ru.choicerestaurant.util.exception;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message){
        super(message);
    }
}
