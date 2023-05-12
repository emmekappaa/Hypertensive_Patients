package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * Abstract class representing a User.
 */
public abstract class User {
	private final StringProperty CF = new SimpleStringProperty(this, "CF");
	private final StringProperty Name = new SimpleStringProperty(this, "Name");
	private final StringProperty Surname = new SimpleStringProperty(this, "Surname");
	private final StringProperty Email = new SimpleStringProperty(this, "Email");
	private final StringProperty Password = new SimpleStringProperty(this, "Password");

	/**
	 * 
	 * Constructs a User with the specified details.
	 * 
	 * @param CF       The tax ID code of the User.
	 * @param Name     The name of the User.
	 * @param Surname  The surname of the User.
	 * @param Email    The email address of the User.
	 * @param Password The password of the User.
	 */

	public User(String CF, String Name, String Surname, String Email, String Password) {
		this.CF.set(CF);
		this.Name.set(Name);
		this.Surname.set(Surname);
		this.Email.set(Email);
		this.Password.set(Password);
	}

	/**
	 * 
	 * Returns the tax ID code of the User.
	 * 
	 * @return The tax ID code.
	 */
	public final String getCF() {
		return CF.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the tax ID code of the User.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getCFProperty() {
		return CF;
	}

	/**
	 * 
	 * Returns the name of the User.
	 * 
	 * @return The name.
	 */
	public final String getName() {
		return Name.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the name of the User.
	 * 
	 * @return The StringProperty object.
	 */

	public final StringProperty getNameProperty() {
		return Name;
	}

	/**
	 * 
	 * Returns the surname of the User.
	 * 
	 * @return The surname.
	 */
	public final String getSurname() {
		return Surname.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the surname of the User.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getSurnameProperty() {
		return Surname;
	}

	/**
	 * 
	 * Returns the email address of the User.
	 * 
	 * @return The email address.
	 */
	public final String getEmail() {
		return Email.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the email address of the User.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getEmailProperty() {
		return Email;
	}

	/**
	 * 
	 * Returns the password of the User.
	 * 
	 * @return The password.
	 */
	public final String getPassword() {
		return Password.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the password of the User.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getPasswordProperty() {
		return Password;
	}
}
