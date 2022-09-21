package pro.sky.exam.app.exceptions;

public class QuestionAlreadyExistsException extends RuntimeException{
    public QuestionAlreadyExistsException(String message){
        super(message);
    }
}
