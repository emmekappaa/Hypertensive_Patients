package model;

import java.util.ArrayList;

public class PathologyWithSymptoms extends Pathology {
	private ArrayList<Symptom> symptoms;

	public PathologyWithSymptoms(Integer ID, String description, ArrayList<Symptom> symptoms) {
		super(ID, description);
		this.symptoms = symptoms;
	}

	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
}