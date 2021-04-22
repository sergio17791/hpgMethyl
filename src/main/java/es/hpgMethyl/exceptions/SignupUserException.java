package es.hpgMethyl.exceptions;

public class SignupUserException extends HpgMethylException {

	private static final long serialVersionUID = -6541298773766025518L;

	public SignupUserException(Throwable exception) {
		super(exception);
	}

	public SignupUserException(String message) {
		super(message);
	}	
}
