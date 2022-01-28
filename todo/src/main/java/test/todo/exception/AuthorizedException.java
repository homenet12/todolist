package test.todo.exception;

public class AuthorizedException extends RuntimeException{

	public AuthorizedException(String msg) {
		super(msg);
	}

}
