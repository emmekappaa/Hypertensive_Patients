package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public abstract class Therapy {

	private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
	private final StringProperty CF_Patient = new SimpleStringProperty(this, "CF_Patient");
	private final StringProperty CF_Doctor = new SimpleStringProperty(this, "CF_Doctor");
	private final IntegerProperty ID_Drug = new SimpleIntegerProperty(this, "ID_Drug");
	private final IntegerProperty Quantity = new SimpleIntegerProperty(this, "Quantity");
	private final IntegerProperty Assumptions = new SimpleIntegerProperty(this, "Assumptions");
	private final StringProperty Indication = new SimpleStringProperty(this, "Indication");
	private final StringProperty Status = new SimpleStringProperty(this, "Status");

	public Therapy(Integer ID, String CF_Patient, String CF_Doctor, Integer ID_Drug, Integer Quantity, Integer Assumptions, String Indication, String Status) {
		this.ID.set(ID);
		this.CF_Patient.set(CF_Patient);
		this.CF_Doctor.set(CF_Doctor);
		this.ID_Drug.set(ID_Drug);
		this.Quantity.set(Quantity);
		this.Assumptions.set(Assumptions);
		this.Indication.set(Indication);
		this.Status.set(Status);
	}


	public final Integer getID() {
		return ID.get();
	}

	public final IntegerProperty IDProperty() {
		return ID;
	}

	public final String getCF_Patient() {
		return CF_Patient.get();
	}

	public final StringProperty CF_PatientProperty() {
		return CF_Patient;
	}

	public final String getCF_Doctor() {
		return CF_Doctor.get();
	}

	public final StringProperty CF_DoctorProperty() {
		return CF_Doctor;
	}

	public final Integer getID_Drug() {
		return ID_Drug.get();
	}

	public final IntegerProperty ID_DrugProperty() {
		return ID_Drug;
	}

	public final Integer getQuantity() {
		return Quantity.get();
	}

	public final IntegerProperty QuantityProperty() {
		return Quantity;
	}

	public final Integer getAssumptions() {
		return Assumptions.get();
	}

	public final IntegerProperty AssumptionsProperty() {
		return Assumptions;
	}

	public final String getIndication() {
		return Indication.get();
	}

	public final StringProperty IndicationProperty() {
		return Indication;
	}

	public final String getStatus() {
		return Status.get();
	}

	public final StringProperty StatusProperty() {
		return Status;
	}
}