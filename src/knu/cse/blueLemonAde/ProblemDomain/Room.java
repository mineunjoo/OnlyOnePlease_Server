package knu.cse.blueLemonAde.ProblemDomain;

public class Room {
	private int roomNum;
	private int userID;
	private int guestID = -1;
	
	private int[] typeID;
	
	private boolean wait = true;
	
	public Room(int roomNum, int userID, int...is) {
		typeID = new int[3];

		for(int i=0; i<3; i++)
			typeID[i] = -1;
		
		this.roomNum = roomNum;
		this.userID = userID;

		for(int i=0; i<is.length; i++) {
			typeID[i] = is[i];
		}
	}

	/*
	 * getter and setter
	 */
	
	public int getCategory() {
		if(typeID[0] != -1)
			return typeID[0];
		else return -1;
	}

	public void setCategory(int category) {
		typeID[0] = category;
	}

	public int getBrand() {
		if(typeID[1] != -1)
			return typeID[1];
		else return -1;
	}

	public void setBrand(int brand) {
		typeID[1] = brand;
	}

	public int getMenu() {
		if(typeID[2] != -1)
			return typeID[2];
		else return -1;
	}

	public void setMenu(int menu) {
		typeID[2] = menu;
	}

	public boolean isWait() {
		return wait;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public int getUserID() {
		return userID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}
	
	public int[] getTypeIDs() {
		return typeID;
	}
}
