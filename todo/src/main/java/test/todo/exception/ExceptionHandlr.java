package test.todo.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlr {

	@ExceptionHandler(value = NoSuchElementException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse notFound(){
		return new ExceptionResponse(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class, IllegalArgumentException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponse validation(Exception ex){
		return new ExceptionResponse(HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse runtime(Exception ex){
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
