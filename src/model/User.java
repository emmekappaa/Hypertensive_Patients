package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class User
{
	private final StringProperty  CF = new SimpleStringProperty(this,"CF");
	private final StringProperty  Name = new SimpleStringProperty(this,"Name");
    private final StringProperty  Surname = new SimpleStringProperty(this,"Surname");
    private final StringProperty  Email = new SimpleStringProperty(this,"Email");
	private final StringProperty  Password = new SimpleStringProperty(this,"Password");
	
	public User(String CF, String Name, String Surname, String Email, String Password) 
	{
        this.CF.set(CF);
        this.Name.set(Name);
        this.Surname.set(Surname);
        this.Email.set(Email);
        this.Password.set(Password);
    }
    
    public final String getCF() {
        return CF.get();
    }
    
    public final StringProperty getCFProperty() {
        return CF;
    }
    
    public final String getName() {
        return Name.get();
    }
    
    public final StringProperty getNameProperty() {
        return Name;
    }
    
    public final String getSurname() {
        return Surname.get();
    }
    
    public final StringProperty getSurnameProperty() {
        return Surname;
    }
    
    public final String getEmail() {
        return Email.get();
    }
    
    public final StringProperty getEmailProperty() {
        return Email;
    }
    
    public final String getPassword() {
        return Password.get();
    }
    
    public final StringProperty getPasswordProperty() {
        return Password;
    }
}
