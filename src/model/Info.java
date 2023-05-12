package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * Class representing an information entry.
 */
public class Info {

	private final StringProperty CF_Doctor = new SimpleStringProperty(this, "CF_Doctor");
	private final StringProperty infoText = new SimpleStringProperty(this, "Info");
	private final StringProperty infoDate = new SimpleStringProperty(this, "Info_Date");

	/**
	 * Constructs an Info object with the specified details.
	 * 
	 * @param CF_Doctor The CF of the doctor who provided the information.
	 * @param infoText  The text of the information.
	 * @param infoDate  The date of the information.
	 */
	public Info(String CF_Doctor, String infoText, String infoDate) {
		this.CF_Doctor.set(CF_Doctor);
		this.infoText.set(infoText);
		this.infoDate.set(infoDate);
	}

	/**
	 * 
	 * Returns the CF of the doctor who provided the information.
	 * 
	 * @return The CF of the doctor.
	 */
	public final String getCF_Doctor() {
		return CF_Doctor.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the CF of the doctor.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getCF_DoctorProperty() {
		return CF_Doctor;
	}

	/**
	 * 
	 * Returns the text of the information.
	 * 
	 * @return The information text.
	 */
	public final String getInfo() {
		return infoText.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the information text.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getInfoProperty() {
		return infoText;
	}

	/**
	 * 
	 * Returns the date of the information.
	 * 
	 * @return The information date.
	 */
	public final String getInfoDate() {
		return infoDate.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the information date.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty getInfoDateProperty() {
		return infoDate;
	}

}
