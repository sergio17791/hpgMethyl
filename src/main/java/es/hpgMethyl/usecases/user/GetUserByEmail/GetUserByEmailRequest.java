package es.hpgMethyl.usecases.user.GetUserByEmail;

public class GetUserByEmailRequest {

	private String email;

	public GetUserByEmailRequest(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
