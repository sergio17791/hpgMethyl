package es.hpgMethyl.exceptions;

public class UpdateUserException extends HpgMethylException {

	private static final long serialVersionUID = 3219479321564627113L;

	public UpdateUserException(Throwable exception) {
		super(exception);
	}

	public UpdateUserException(String message) {
		super(message);
	}
}
