package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pathology {
    private final StringProperty ID = new SimpleStringProperty(this, "ID");
    private final StringProperty Description = new SimpleStringProperty(this, "Description");
    private final StringProperty StartDate = new SimpleStringProperty(this, "StartDate");
    private final StringProperty EndDate = new SimpleStringProperty(this, "EndDate");


    public Pathology(String ID, String Description,String start, String end) {
        
        this.ID.set(ID);
        this.Description.set(Description);
        this.StartDate.set(start);
        this.EndDate.set(end);
    }

    public final String getID() {
        return ID.get();
    }
    
    public final String getEndDate() {
        return EndDate.get();
    }
    
    public final String getStartDate() {
        return StartDate.get();
    }

    public final StringProperty IDProperty() {
        return ID;
    }

    public final String getDescription() {
        return Description.get();
    }

    public final StringProperty DescriptionProperty() {
        return Description;
    }
}