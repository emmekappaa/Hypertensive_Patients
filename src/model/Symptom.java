package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Symptom {
	
	private final StringProperty ID = new SimpleStringProperty(this, "ID");
	private final StringProperty date = new SimpleStringProperty(this, "date");
	
	public Symptom(String id,String date) {
		this.ID.set(id);
		this.date.set(date);
	}
	
	public final String getID() {
		return ID.get();
	}

	public final StringProperty idProperty() {
		return ID;
	}
	
	public final String getDate() {
		return date.get();
	}

	public final StringProperty dateProperty() {
		return date;
	}

	

}
