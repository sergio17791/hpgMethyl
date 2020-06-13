package es.hpgMethyl.exceptions;

public class UserNotFound extends HpgMethylException {
	public UserNotFound(String message, Throwable exception) {
		super(message, exception);
	}
}
