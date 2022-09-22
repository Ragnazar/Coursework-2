package pro.sky.exam.app.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
