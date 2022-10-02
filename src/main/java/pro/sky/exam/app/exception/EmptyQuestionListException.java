package pro.sky.exam.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyQuestionListException extends RuntimeException{
    public EmptyQuestionListException(String message){
        super(message);
    }
}
