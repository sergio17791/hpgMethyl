package es.hpgMethyl.exceptions;

public class ChangePasswordException extends HpgMethylException {
	public ChangePasswordException(Throwable exception) {
		super(exception);
	}

	public ChangePasswordException(String message) {
		super(message);
	}	
}
