package es.hpgMethyl.exceptions;

public class ChangePasswordException extends HpgMethylException {

	private static final long serialVersionUID = -2456630531756726107L;

	public ChangePasswordException(Throwable exception) {
		super(exception);
	}

	public ChangePasswordException(String message) {
		super(message);
	}	
}
