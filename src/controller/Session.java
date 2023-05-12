package controller;

/**
 * 
 * Singleton class representing the current session.
 */
public final class Session {

	private String mail;
	private String CF_Patient_shmem;
	private final static Session INSTANCE = new Session();

	private Session() {
	}

	/**
	 * 
	 * Returns the instance of the Session class.
	 * 
	 * @return The Session instance.
	 */

	public static Session getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 * Sets the email associated with the session.
	 * 
	 * @param mail The email to set.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 
	 * Returns the email associated with the session.
	 * 
	 * @return The email associated with the session.
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * 
	 * Sets the shared patient fiscal code.
	 * 
	 * @param CF The shared patient fiscal code.
	 */
	public void setCF_shmem(String CF) {
		this.CF_Patient_shmem = CF;
	}

	/**
	 * 
	 * Returns the shared patient fiscal code.
	 * 
	 * @return The shared patient fiscal code.
	 */
	public String getCF_shmem() {
		return this.CF_Patient_shmem;
	}
}