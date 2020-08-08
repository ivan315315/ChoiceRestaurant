package ru.choicerestaurant.util.exception;

/*@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "No data found") // it's in ExceptionInfoHandler*/  // 422
public class ProhibitedOperationException extends RuntimeException {
    public ProhibitedOperationException(String message){
        super(message);
    }
}
