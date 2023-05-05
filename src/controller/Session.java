package controller;

public final class Session {

	private String mail;
	private String CF_Patient_shmem;
	private final static Session INSTANCE = new Session();

	private Session() {
	}

	public static Session getInstance() {
		return INSTANCE;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}
	public void setCF_shmem(String CF) {
		this.CF_Patient_shmem = CF;
	}

	public String getCF_shmem() {
		return this.CF_Patient_shmem;
	}
}