package es.hpgMethyl.exceptions;

public class SignupUserException extends HpgMethylException {
	public SignupUserException(Throwable exception) {
		super(exception);
	}

	public SignupUserException(String message) {
		super(message);
	}	
}
