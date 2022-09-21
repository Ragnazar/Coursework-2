package pro.sky.exam.app.exceptions;

public class EmptyQuestionListException extends RuntimeException{
    public EmptyQuestionListException(String message){
        super(message);
    }
}
