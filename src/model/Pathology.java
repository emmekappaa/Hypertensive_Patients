package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * Class representing a Pathology.
 */
public class Pathology {
	private final StringProperty ID = new SimpleStringProperty(this, "ID");
	private final StringProperty Description = new SimpleStringProperty(this, "Description");
	private final StringProperty StartDate = new SimpleStringProperty(this, "StartDate");
	private final StringProperty EndDate = new SimpleStringProperty(this, "EndDate");

	/**
	 * 
	 * Constructs a Pathology with the specified details.
	 * 
	 * @param ID          The ID of the Pathology.
	 * 
	 * @param Description The description of the Pathology.
	 * 
	 * @param start       The start date of the Pathology.
	 * 
	 * @param end         The end date of the Pathology.
	 */
	public Pathology(String ID, String Description, String start, String end) {

		this.ID.set(ID);
		this.Description.set(Description);
		this.StartDate.set(start);
		this.EndDate.set(end);
	}

	/**
	 * 
	 * Returns the ID of the Pathology.
	 * 
	 * @return The ID.
	 */
	public final String getID() {
		return ID.get();
	}

	/**
	 * 
	 * Returns the end date of the Pathology.
	 * 
	 * @return The end date.
	 */
	public final String getEndDate() {
		return EndDate.get();
	}

	/**
	 * 
	 * Returns the start date of the Pathology.
	 * 
	 * @return The start date.
	 */
	public final String getStartDate() {
		return StartDate.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the ID of the Pathology.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty IDProperty() {
		return ID;
	}

	/**
	 * 
	 * Returns the description of the Pathology.
	 * 
	 * @return The description.
	 */
	public final String getDescription() {
		return Description.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the description of the Pathology.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty DescriptionProperty() {
		return Description;
	}
}