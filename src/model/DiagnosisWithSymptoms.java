package model;

import java.util.ArrayList;

public class DiagnosisWithSymptoms extends Diagnosis {
	private ArrayList<Symptom> symptoms;

	public DiagnosisWithSymptoms(Integer ID, String CF_Patient, String Date, String Time, Integer SBP, Integer DBP, ArrayList<Symptom> symptoms) {
		super(ID, CF_Patient, Date, Time, SBP, DBP);
		this.symptoms = symptoms;
	}

	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
}
