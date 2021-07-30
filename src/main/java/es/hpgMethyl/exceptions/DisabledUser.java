package es.hpgMethyl.exceptions;

public class DisabledUser extends HpgMethylException {

	private static final long serialVersionUID = 7240869744066284313L;

	public DisabledUser(String message) {
		super(message);
	}
}
