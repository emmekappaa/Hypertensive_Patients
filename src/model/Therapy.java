package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * Class representing a Therapy.
 */
public class Therapy {

	private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
	private final StringProperty CF_Patient = new SimpleStringProperty(this, "CF_Patient");
	private final StringProperty CF_Doctor = new SimpleStringProperty(this, "CF_Doctor");
	private final StringProperty ID_Drug = new SimpleStringProperty(this, "ID_Drug");
	private final IntegerProperty Quantity = new SimpleIntegerProperty(this, "Quantity");
	private final IntegerProperty Assumptions = new SimpleIntegerProperty(this, "Assumptions");
	private final StringProperty Indication = new SimpleStringProperty(this, "Indication");
	private final StringProperty Status = new SimpleStringProperty(this, "Status");

	/**
	 * 
	 * Constructs a Therapy with the specified details.
	 * 
	 * @param ID          The ID of the Therapy.
	 * @param CF_Patient  The tax id code of the patient associated with the
	 *                    Therapy.
	 * @param CF_Doctor   The tax id code of the doctor associated with the Therapy.
	 * @param ID_Drug     The ID of the drug associated with the Therapy.
	 * @param Quantity    The quantity of the drug for the Therapy.
	 * @param Assumptions The number of assumptions for the Therapy.
	 * @param Indication  The indication for the Therapy.
	 * @param Status      The status of the Therapy.
	 */
	public Therapy(int ID, String CF_Patient, String CF_Doctor, String ID_Drug, int Quantity, int Assumptions,
			String Indication, String Status) {
		this.ID.set(ID);
		this.CF_Patient.set(CF_Patient);
		this.CF_Doctor.set(CF_Doctor);
		this.ID_Drug.set(ID_Drug);
		this.Quantity.set(Quantity);
		this.Assumptions.set(Assumptions);
		this.Indication.set(Indication);
		this.Status.set(Status);
	}

	/**
	 * 
	 * Returns the ID of the Therapy.
	 * 
	 * @return The ID.
	 */
	public final int getID() {
		return ID.get();
	}

	/**
	 * 
	 * Returns the IntegerProperty object for the ID of the Therapy.
	 * 
	 * @return The IntegerProperty object.
	 */
	public final IntegerProperty IDProperty() {
		return ID;
	}

	/**
	 * 
	 * Returns the tax id code of the patient associated with the Therapy.
	 * 
	 * @return The tax id code of the patient.
	 */
	public final String getCF_Patient() {
		return CF_Patient.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the tax id code of the patient
	 * associated with the Therapy.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty CF_PatientProperty() {
		return CF_Patient;
	}

	/**
	 * 
	 * Returns the tax id code of the doctor associated with the Therapy.
	 * 
	 * @return The tax id code of the doctor.
	 */
	public final String getCF_Doctor() {
		return CF_Doctor.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the tax id code of the doctor
	 * associated with the Therapy.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty CF_DoctorProperty() {
		return CF_Doctor;
	}

	/**
	 * 
	 * Returns the ID of the drug associated with the Therapy.
	 * 
	 * @return The ID of the drug.
	 */
	public final String getID_Drug() {
		return ID_Drug.get();
	}

	/**
	 * 
	 * Returns the StringProperty object for the ID of the drug associated with the
	 * Therapy.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty ID_DrugProperty() {
		return ID_Drug;
	}

	/**
	 * 
	 * Returns the quantity of the drug for the Therapy.
	 * 
	 * @return The quantity.
	 */
	public final int getQuantity() {
		return Quantity.get();
	}

	/**
	 * 
	 * Returns the IntegerProperty object for the quantity of the drug for the
	 * Therapy.
	 * 
	 * @return The IntegerProperty object.
	 */
	public final IntegerProperty QuantityProperty() {
		return Quantity;
	}

	/**
	 * Returns the number of assumptions for the Therapy.
	 *
	 * @return The number of assumptions.
	 */
	public final int getAssumptions() {
		return Assumptions.get();
	}

	/**
	 * Returns the IntegerProperty object for the number of assumptions for the
	 * Therapy.
	 * 
	 * @return The IntegerProperty object.
	 */
	public final IntegerProperty AssumptionsProperty() {
		return Assumptions;
	}

	/**
	 * Returns the indication for the Therapy.
	 * 
	 * @return The indication.
	 */
	public final String getIndication() {
		return Indication.get();
	}

	/**
	 * Returns the StringProperty object for the indication for the Therapy.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty IndicationProperty() {
		return Indication;
	}

	/**
	 * Returns the status of the Therapy.
	 * 
	 * @return The status.
	 */
	public final String getStatus() {
		return Status.get();
	}

	/**
	 * Returns the StringProperty object for the status of the Therapy.
	 * 
	 * @return The StringProperty object.
	 */
	public final StringProperty StatusProperty() {
		return Status;
	}
}