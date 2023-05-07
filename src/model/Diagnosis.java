package model;

import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Diagnosis {
	private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
	private final StringProperty CF_Patient = new SimpleStringProperty(this, "CF_Patient");
	private final StringProperty Date = new SimpleStringProperty(this, "Date");
	private final IntegerProperty SBP = new SimpleIntegerProperty(this, "SBP");
	private final IntegerProperty DBP = new SimpleIntegerProperty(this, "DBP");
	
	private ObservableList<Symptom> symptoms;

	public Diagnosis(Integer ID, String CF_Patient, String Date, Integer SBP, Integer DBP) {
		this.ID.set(ID);
		this.CF_Patient.set(CF_Patient);
		this.Date.set(Date);
		this.SBP.set(SBP);
		this.DBP.set(DBP);
		
		
		try {
			Model model = Model.getInstance();
			this.symptoms = model.getDiagnosiSymptom(ID);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public final Integer getID() {
		return ID.get();
	}

	public final IntegerProperty IDProperty() {
		return ID;
	}

	public final String getCF_Patient() {
		return CF_Patient.get();
	}

	public final StringProperty CF_PatientProperty() {
		return CF_Patient;
	}

	public final String getDate() {
		return Date.get();
	}

	public final StringProperty DateProperty() {
		return Date;
	}


	public final Integer getSBP() {
		return SBP.get();
	}

	public final IntegerProperty SBPProperty() {
		return SBP;
	}

	public final Integer getDBP() {
		return DBP.get();
	}

	public final IntegerProperty DBPProperty() {
		return DBP;
	}
	
	public ObservableList<Symptom> getSymptoms() {
		return symptoms;
	}
}
