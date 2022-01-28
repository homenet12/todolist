package test.todo.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlr {

	@ExceptionHandler(value = NoSuchElementException.class)
	public ExceptionResponse notFound(){
		return new ExceptionResponse(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ExceptionResponse validation(Exception ex){
		return new ExceptionResponse(HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = RuntimeException.class)
	public ExceptionResponse runtime(Exception ex){
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
