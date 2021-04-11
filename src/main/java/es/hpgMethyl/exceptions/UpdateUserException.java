package es.hpgMethyl.exceptions;

public class UpdateUserException extends HpgMethylException {
	public UpdateUserException(Throwable exception) {
		super(exception);
	}

	public UpdateUserException(String message) {
		super(message);
	}
}
