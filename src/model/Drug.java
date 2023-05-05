package model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Drug {
	
	private final ReadOnlyIntegerWrapper  ID = new ReadOnlyIntegerWrapper(this,"ID");
	private final StringProperty  Description = new SimpleStringProperty(this,"Description");

	
	public Drug(int ID, String Description) 
	{
        this.ID.set(ID);
        this.Description.set(Description);
    }
	
	public final int getID() {
        return ID.get();
    }
    
    public final ReadOnlyIntegerProperty getIDProperty() {
        return ID;
    }
    
	 public final String getDescription() {
        return Description.get();
    }
    
    public final StringProperty getDescriptionProperty() {
        return Description;
    }


}
