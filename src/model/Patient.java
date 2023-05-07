package model;

import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Patient extends User {
	
	private final StringProperty CF_doctor = new SimpleStringProperty(this,"CF_doctor");
	
	private ObservableList<Pathology> pathologies;
	private ObservableList<Therapy> therapies;
	private ObservableList<Info> infos;
    
	public Patient(String CF, String Name, String Surname, String Email, String Password, String CF_doctor) {
		super(CF, Name, Surname, Email, Password);
		this.CF_doctor.set(CF_doctor);
		
		try {
			Model model = Model.getInstance();
			this.pathologies = model.getPatientPathologies(CF);
			this.therapies = model.getPatientTherapies(CF);
			this.infos = model.getPatientInfos(CF);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final ObservableList<Pathology> getPathologies() {
        return pathologies;
    }
	
	public final ObservableList<Pathology> reloadAndGetPathologies() {
		try {
			Model model = Model.getInstance();
			this.pathologies = model.getPatientPathologies(this.getCF());
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return pathologies;
    }
	
	public final ObservableList<Therapy> getTherapies() {
        return therapies;
    }
    
    public final ObservableList<Info> getInfos() {
        return infos;
    }
	
	public final String getCF_doctor() {
        return CF_doctor.get();
    }
    
    public final StringProperty getCF_doctorProperty() {
        return CF_doctor;
    }
}



