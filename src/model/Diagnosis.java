package model;

import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * 
 * The Diagnosis class represents a medical diagnosis made for a patient,
 * containing information about the diagnosis ID, patient's fiscal code, date,
 * systolic and diastolic blood pressure readings and symptoms. Diagnosis
 * objects are constructed with a Diagnosis ID, the patient's fiscal code, the
 * date of the diagnosis, and the systolic and diastolic blood pressure
 * readings. The class provides getter methods to retrieve the diagnosis ID,
 * patient's fiscal code, date, systolic and diastolic blood pressure readings,
 * and symptoms. The Diagnosis object is associated with a list of symptoms
 * using an ObservableList<Symptom> object.
 * 
 * @see Symptom
 * @see Model
 */
public class Diagnosis {
	private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
	private final StringProperty CF_Patient = new SimpleStringProperty(this, "CF_Patient");
	private final StringProperty Date = new SimpleStringProperty(this, "Date");
	private final IntegerProperty SBP = new SimpleIntegerProperty(this, "SBP");
	private final IntegerProperty DBP = new SimpleIntegerProperty(this, "DBP");

	private ObservableList<Symptom> symptoms;

	/**
	 * 
	 * Constructs a Diagnosis object with the given ID, patient's fiscal code, date,
	 * systolic and diastolic blood pressure readings.
	 * 
	 * @param ID         the ID of the diagnosis
	 * @param CF_Patient the patient's tax id code
	 * @param Date       the date of the diagnosis
	 * @param SBP        the systolic blood pressure reading
	 * @param DBP        the diastolic blood pressure reading
	 * @throws SQLException if there is an error accessing the database
	 */
	public Diagnosis(Integer ID, String CF_Patient, String Date, Integer SBP, Integer DBP) {
		this.ID.set(ID);
		this.CF_Patient.set(CF_Patient);
		this.Date.set(Date);
		this.SBP.set(SBP);
		this.DBP.set(DBP);

		try {
			Model model = Model.getInstance();
			this.symptoms = model.getDiagnosiSymptom(ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Returns the ID of the diagnosis.
	 * 
	 * @return the ID of the diagnosis
	 */
	public final Integer getID() {
		return ID.get();
	}

	/**
	 * 
	 * Returns the property object for the ID of the diagnosis.
	 * 
	 * @return the property object for the ID of the diagnosis
	 */
	public final IntegerProperty IDProperty() {
		return ID;
	}

	/**
	 * 
	 * Returns the patient's tax id code for the diagnosis.
	 * 
	 * @return the patient's tax id code for the diagnosis
	 */
	public final String getCF_Patient() {
		return CF_Patient.get();
	}

	/**
	 * 
	 * Returns the property object for the patient's tax id code for the diagnosis.
	 * 
	 * @return the property object for the patient's tax id code for the diagnosis
	 */
	public final StringProperty CF_PatientProperty() {
		return CF_Patient;
	}

	/**
	 * 
	 * Returns the date of the diagnosis.
	 * 
	 * @return the date of the diagnosis
	 */
	public final String getDate() {
		return Date.get();
	}

	/**
	 * 
	 * Returns the property object for the date of the diagnosis.
	 * 
	 * @return the property object for the date of the diagnosis
	 */
	public final StringProperty DateProperty() {
		return Date;
	}

	/**
	 * 
	 * Returns the systolic blood pressure reading for the diagnosis.
	 * 
	 * @return the systolic blood pressure reading for the diagnosis
	 */
	public final Integer getSBP() {
		return SBP.get();
	}

	/**
	 * 
	 * Returns the IntegerProperty object representing the SBP (systolic blood
	 * pressure) property of the Diagnosis.
	 * 
	 * @return the IntegerProperty object representing the SBP property.
	 */
	public final IntegerProperty SBPProperty() {
		return SBP;
	}

	/**
	 * 
	 * Returns the value of the DBP (diastolic blood pressure) property of the
	 * Diagnosis.
	 * 
	 * @return the value of the DBP property.
	 */
	public final Integer getDBP() {
		return DBP.get();
	}

	/**
	 * 
	 * Returns the IntegerProperty object representing the DBP (diastolic blood
	 * pressure) property of the Diagnosis.
	 * 
	 * @return the IntegerProperty object representing the DBP property.
	 */
	public final IntegerProperty DBPProperty() {
		return DBP;
	}

	/**
	 * 
	 * Returns the list of symptoms associated with the Diagnosis object.
	 * 
	 * @return the ObservableList of Symptom objects associated with the Diagnosis.
	 */
	public ObservableList<Symptom> getSymptoms() {
		return symptoms;
	}
}
