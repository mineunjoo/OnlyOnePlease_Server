package knu.cse.blueLemonAde.ProblemDomain;

public class Room {
	private int roomNum;
	private int userID;
	private int guestID;
	
	private int category = -1; 
	private int brand = -1;
	private int menu = -1;
	
	private boolean wait = true;
	
	public Room(int roomNum, int userID, int...is) {
		this.roomNum = roomNum;
		this.userID = userID;
		
		for(int i=0; i<is.length && i<3; i++) {
			switch(i) {
			case 0:
				category = is[i];
				break;
			case 1:
				brand = is[i];
			case 2:
				menu = is[i];
			}
		}
	}
	
	public void inConversation() {
		wait = false;
	}
	
	public void waiting() {
		wait = true;
	}
	
	public void entrance(int guestID) {
		this.guestID = guestID;
	}
	
}
