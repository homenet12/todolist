package test.todo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionResponse {

	private int status;
	private String error;
	
	public ExceptionResponse(HttpStatus status) {
		this.status = status.value();
		this.error = status.name();
	}
	
	public ExceptionResponse(int status, String error) {
		this.status = status;
		this.error = error;
	}


}
