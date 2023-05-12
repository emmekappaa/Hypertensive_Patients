package model;

/**
 * 
 * Class representing a drug.
 */
public class Drug {

	private int taken;
	private int assumption;
	private String description;

	/**
	 * 
	 * Constructs a Drug object with the specified details.
	 * 
	 * @param taken       The number of times the drug has been taken.
	 * @param assumption  The number of times the drug should be taken.
	 * @param description The description of the drug.
	 */
	public Drug(int taken, int assumption, String stringa) {
		// TODO Auto-generated constructor stub
		this.description = stringa;
		this.assumption = assumption;
		this.taken = taken;
	}

	/**
	 * 
	 * Returns the number of times the drug needs to be taken.
	 * 
	 * @return The number of times to take the drug.
	 */
	public int getToTake() {
		int daFare = assumption - taken;
		if (daFare <= 0)
			return 0;
		return daFare;
	}

	/**
	 * 
	 * Returns the description of the drug.
	 * 
	 * @return The drug description.
	 */
	public String getDescription() {

		return description;
	}
}
