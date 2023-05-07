package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Info {
	
	private final StringProperty  CF_Doctor = new SimpleStringProperty(this,"CF_Doctor");
    private final StringProperty  infoText = new SimpleStringProperty(this,"Info");
    private final StringProperty infoDate = new SimpleStringProperty(this, "Info_Date");

	
	public Info(String CF_Doctor, String infoText, String infoDate) 
	{
        this.CF_Doctor.set(CF_Doctor);
        this.infoText.set(infoText);
        this.infoDate.set(infoDate);
    }
    
    public final String getCF_Doctor() {
        return CF_Doctor.get();
    }
    
    public final StringProperty getCF_DoctorProperty() {
        return CF_Doctor;
    }
    
    public final String getInfo() {
        return infoText.get();
    }
    
    public final StringProperty getInfoProperty() {
        return infoText;
    }
    
    public final String getInfoDate() {
        return infoDate.get();
    }
    
    public final StringProperty getInfoDateProperty() {
        return infoDate;
    }
 
}

