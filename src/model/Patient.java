package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient extends User {
	
	private final StringProperty CF_doctor = new SimpleStringProperty(this,"CF_doctor");

	public Patient(String CF, String Name, String Surname, String Email, String Password, String CF_doctor) {
		super(CF, Name, Surname, Email, Password);
		this.CF_doctor.set(CF_doctor);
		// TODO Auto-generated constructor stub
	}
	
	public final String getCF_doctor() {
        return CF_doctor.get();
    }
    
    public final StringProperty getCF_doctorProperty() {
        return CF_doctor;
    }
}



