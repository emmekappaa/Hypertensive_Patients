package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pathology {
    private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private final StringProperty Description = new SimpleStringProperty(this, "Description");

    public Pathology(Integer ID, String Description) {
        
        this.ID.set(ID);
        this.Description.set(Description);
    }

    public final Integer getID() {
        return ID.get();
    }

    public final IntegerProperty IDProperty() {
        return ID;
    }

    public final String getDescription() {
        return Description.get();
    }

    public final StringProperty DescriptionProperty() {
        return Description;
    }
}