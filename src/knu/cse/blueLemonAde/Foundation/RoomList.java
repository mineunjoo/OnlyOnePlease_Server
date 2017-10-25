package knu.cse.blueLemonAde.Foundation;

import java.util.ArrayList;

import knu.cse.blueLemonAde.ProblemDomain.Room;

/**
 * @author jm showed room in waiting queue. user can see all or specific
 *         category room
 */
public class RoomList {
	private ArrayList<Room> roomList;

	public RoomList() {
		roomList = new ArrayList<Room>();
	}

	public RoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	
	public void addRoom(Room room) {
		roomList.add(room);
	}

	public void removeRoom(int userID) {
		for(Room room : roomList) {
			if(room.getUserID() == userID) {
				roomList.remove(room);
				return;
			}
		}
	}

	public void removeRoom(Room room) {
		roomList.remove(room);
		return;
	}
	
	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	
	/**
	 * @param is
	 *            : 1. category 2. brand 3. menu
	 * @return A room list that satisfies above param.
	 */
	public RoomList getRoomList(int... is) {
		ArrayList<Room> temp;

		if (is.length > 3) {
			System.out.println("invalid param");
			return null;
		}
		
		temp = new ArrayList<Room>();
		
		for (Room room : roomList) 
			if (room.getCategory() == is[0])
				temp.add(room);
		
		if(is.length == 1)
			return new RoomList(temp);
		
		for(Room room : temp) 
			if(room.getBrand() != is[1])
				temp.remove(room);
		
		if(is.length == 2)
			return new RoomList(temp);
		
		for(Room room : temp) {
			if(room.getMenu() != is[2]) 
				temp.remove(room);
		}
		
		return new RoomList(temp);
	}
	
	public ArrayList<Room> getRoomList(){
		return roomList;
	}
}
