package model;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * 
 * Class representing a Patient, extending the User class.
 */
public class Patient extends User {

	private final StringProperty CF_doctor = new SimpleStringProperty(this, "CF_doctor");

	private ObservableList<Pathology> pathologies;
	private ObservableList<Therapy> therapies;
	private ObservableList<Info> infos;

	private int SBP = 0;
	private int DBP = 0;
	private final StringProperty hypertension = new SimpleStringProperty(this, "hypertension");

	/**
	 * 
	 * Constructs a Patient with the specified details.
	 * 
	 * @param CF        The fiscal code of the Patient.
	 * 
	 * @param Name      The name of the Patient.
	 * 
	 * @param Surname   The surname of the Patient.
	 * 
	 * @param Email     The email of the Patient.
	 * 
	 * @param Password  The password of the Patient.
	 * 
	 * @param CF_doctor The fiscal code of the doctor associated with the Patient.
	 */
	public Patient(String CF, String Name, String Surname, String Email, String Password, String CF_doctor) {
		super(CF, Name, Surname, Email, Password);
		this.CF_doctor.set(CF_doctor);

		LocalDateTime now = LocalDateTime.now();

		try {
			Model model = Model.getInstance();
			this.pathologies = model.getPatientPathologies(CF);
			this.therapies = model.getPatientTherapies(CF);
			this.infos = model.getPatientInfos(CF);
			this.SBP = model.getBPM("SBP", CF, now);
			this.DBP = model.getBPM("DBP", CF, now);
			this.hypertension.set(setHypertension());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Sets the hypertension value based on the SBP and DBP values.
	 * 
	 * @return The hypertension value.
	 */
	public String setHypertension() {
		String data = "";
		// System.out.println(SBP);
		// System.out.println(DBP);
		if (SBP == 0 || DBP == 0) {
			data = "No data";
		} else if ((SBP < 120) && (DBP < 80)) {
			data = "Optimal";
		} else if ((SBP < 130) && (DBP < 85)) {
			data = "Normal";
		} else if ((SBP >= 130 && SBP <= 140) && (DBP >= 85 && DBP <= 90)) {
			data = "Normal high";
		} else if ((SBP >= 140 && SBP <= 160) && (DBP >= 90 && DBP <= 100)) {
			data = "Grade 1";
		} else if ((SBP >= 160 && SBP <= 180) && (DBP >= 100 && DBP <= 110)) {
			data = "Grade 2";
		} else if ((SBP >= 180) && (DBP >= 110)) {
			data = "Grade 3";
		} else {
			data = "Invalid data";
		}

		return data;
	}

	/**
	 * 
	 * Returns the list of Pathologies associated with the Patient.
	 * 
	 * @return The list of Pathologies.
	 */
	public final ObservableList<Pathology> getPathologies() {
		return pathologies;
	}

	/**
	 * 
	 * Reloads and returns the list of Pathologies associated with the Patient.
	 * 
	 * @return The list of Pathologies.
	 */
	public final ObservableList<Pathology> reloadAndGetPathologies() {
		try {
			Model model = Model.getInstance();
			this.pathologies = model.getPatientPathologies(this.getCF());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pathologies;
	}

	/**
	 * 
	 * Returns the list of Therapies associated with the Patient.
	 * 
	 * @return The list of Therapies.
	 */
	public final ObservableList<Therapy> getTherapies() {
		return therapies;
	}

	/**
	 * 
	 * Returns the list of Infos associated with the Patient.
	 * 
	 * @return The list of Infos.
	 */
	public final ObservableList<Info> getInfos() {
		return infos;
	}

	/**
	 * 
	 * Returns the fiscal code of the doctor associated with the Patient.
	 * 
	 * @return The fiscal code of the doctor.
	 */
	public final String getCF_doctor() {
		return CF_doctor.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the fiscal code of the doctor
	 * associated with the Patient.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getCF_doctorProperty() {
		return CF_doctor;
	}

	/**
	 * 
	 * Returns the hypertension value of the Patient.
	 * 
	 * @return The hypertension value.
	 */
	public final String getHypertension() {
		return hypertension.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the hypertension value of the Patient.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getHypertensionProperty() {
		return hypertension;
	}
}
