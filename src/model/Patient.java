package model;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Patient extends User {
	
	private final StringProperty CF_doctor = new SimpleStringProperty(this,"CF_doctor");
	
	private ObservableList<Pathology> pathologies;
	private ObservableList<Therapy> therapies;
	private ObservableList<Info> infos;
	
	private int SBP = 0;
	private int DBP = 0;
	private final StringProperty hypertension = new SimpleStringProperty(this,"hypertension");
    
	public Patient(String CF, String Name, String Surname, String Email, String Password, String CF_doctor) {
		super(CF, Name, Surname, Email, Password);
		this.CF_doctor.set(CF_doctor);
		
		LocalDateTime now = LocalDateTime.now(); 
		 
		try {
			Model model = Model.getInstance();
			this.pathologies = model.getPatientPathologies(CF);
			this.therapies = model.getPatientTherapies(CF);
			this.infos = model.getPatientInfos(CF);
			this.SBP = model.getBPM("SBP",CF,now);
			this.DBP = model.getBPM("DBP",CF,now);
			this.hypertension.set(setHypertension());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String setHypertension(){
		String data = "";
		//System.out.println(SBP);
		//System.out.println(DBP);
		if(SBP == 0 || DBP==0) {
			data = "No data";
		}
		else if((SBP<120) && (DBP<80)) {
			data = "Grade 1";
		}
		else if((SBP<130) && (DBP<85)) {
			data = "Grade 1";
		}
		else if((SBP>=130 && SBP<=139) && (DBP>=85 && DBP<=89)) {
			data = "Grade 1";
		}
		else if((SBP>=140 && SBP<=159) && (DBP>=90 && DBP<=99)) {
			data = "Grade 1";
		}
		else if((SBP>=160 && SBP<=179) && (DBP>=100 && DBP<=109)) {
			data = "Grade 2";
		}
		else if((SBP>=180) && (DBP>=110)) {
			data = "Grade 3";
		}
		else {
			data = "Invalid data";
		}
		
		return data;
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
    
    public final String getHypertension() {
        return hypertension.get();
    }
    
    public final StringProperty getHypertensionProperty() {
        return hypertension;
    }
}



