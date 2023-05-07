package model;

public class Drug {
	
	private int taken;
	private int assumption;
	private String description;
	
	public Drug(int taken,int assumption,String stringa) {
		// TODO Auto-generated constructor stub
		this.description = stringa;
		this.assumption = assumption;
		this.taken = taken;
	}
	
	public int getToTake(){
		int daFare = assumption-taken;
		if(daFare <= 0)
			return 0;
		return daFare;
	}
	

	public String getDescription(){
		
		return description;
	}
}
