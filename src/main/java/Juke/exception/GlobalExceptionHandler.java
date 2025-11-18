package Juke.exception;

import Juke.model.MessageModel;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageModel> handleValidation(MethodArgumentNotValidException ex) {
        MessageModel msg = new MessageModel();
        msg.setStatus(false);
        msg.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        msg.setData(null);

        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageModel> handleNotFound(EntityNotFoundException ex) {
        MessageModel msg = new MessageModel();
        msg.setStatus(false);
        msg.setMessage(ex.getMessage());
        msg.setData(null);

        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageModel> handleConstraint(DataIntegrityViolationException ex) {
        MessageModel msg = new MessageModel();
        msg.setStatus(false);

        String error = ex.getMostSpecificCause().getMessage();

        if (error.toLowerCase().contains("email")) {
            msg.setMessage("Email sudah digunakan");
        } else {
            msg.setMessage("Data melanggar constraint database");
        }

        msg.setData(null);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageModel> handleOther(Exception ex) {
        MessageModel msg = new MessageModel();
        msg.setStatus(false);
        msg.setMessage(ex.getMessage());
        msg.setData(null);

        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
