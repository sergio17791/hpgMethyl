package es.hpgMethyl.exceptions;

public class HpgMethylException extends Exception {
	public HpgMethylException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public HpgMethylException(Throwable exception) {
		super(exception);
	}
	
	public HpgMethylException(String message) {
		super(message);
	}
	
	public HpgMethylException() {
		super();
	}
}
