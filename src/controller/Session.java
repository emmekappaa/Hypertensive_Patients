package controller;

public final class Session {

	  private String mail;
	  private final static Session INSTANCE = new Session();
	  
	  private Session() {}
	  
	  public static Session getInstance() {
	    return INSTANCE;
	  }
	  
	  public void setMail(String mail) {
	    this.mail = mail;
	  }
	  
	  public String getMail() {
		    return this.mail;
		  }
	}