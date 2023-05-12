package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * Class representing a Symptom.
 */
public class Symptom {

	private final StringProperty ID = new SimpleStringProperty(this, "ID");
	private final StringProperty date = new SimpleStringProperty(this, "date");

	/**
	 * 
	 * Constructs a Symptom with the specified ID and date.
	 * 
	 * @param id   The ID of the Symptom.
	 * @param date The date of the Symptom.
	 */
	public Symptom(String id, String date) {
		this.ID.set(id);
		this.date.set(date);
	}

	/**
	 * 
	 * Returns the ID of the Symptom.
	 * 
	 * @return The ID.
	 */
	public final String getID() {
		return ID.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the ID of the Symptom.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty idProperty() {
		return ID;
	}

	/**
	 * 
	 * Returns the date of the Symptom.
	 * 
	 * @return The date.
	 */
	public final String getDate() {
		return date.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the date of the Symptom.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty dateProperty() {
		return date;
	}

}
