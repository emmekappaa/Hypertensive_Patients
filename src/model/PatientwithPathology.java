package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientwithPathology extends Patient {
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty endDate;
    private final SimpleIntegerProperty pathologyId;
    private final SimpleStringProperty pathologyDescription;

    public PatientwithPathology(String CF, String Name, String Surname, String Email, String Password, String CF_doctor, String startDate, String endDate, int pathologyId, String pathologyDescription) {
        super(CF, Name, Surname, Email, Password, CF_doctor);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.pathologyId = new SimpleIntegerProperty(pathologyId);
        this.pathologyDescription = new SimpleStringProperty(pathologyDescription);
    }

    public final String getStartDate() {
        return startDate.get();
    }

    public final StringProperty startDateProperty() {
        return startDate;
    }

    public final String getEndDate() {
        return endDate.get();
    }

    public final StringProperty endDateProperty() {
        return endDate;
    }

    public final int getPathologyId() {
        return pathologyId.get();
    }

    public final SimpleIntegerProperty pathologyIdProperty() {
        return pathologyId;
    }

    public final String getPathologyDescription() {
        return pathologyDescription.get();
    }

    public final SimpleStringProperty pathologyDescriptionProperty() {
        return pathologyDescription;
    }
}