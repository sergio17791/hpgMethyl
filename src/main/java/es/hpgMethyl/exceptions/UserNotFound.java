package es.hpgMethyl.exceptions;

public class UserNotFound extends HpgMethylException {

	private static final long serialVersionUID = -2153324823530120840L;

	public UserNotFound(String message) {
		super(message);
	}
}
