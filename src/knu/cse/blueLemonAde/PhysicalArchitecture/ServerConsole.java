package knu.cse.blueLemonAde.PhysicalArchitecture;

import java.awt.SecondaryLoop;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import knu.cse.blueLemonAde.Foundation.RoomList;
import knu.cse.blueLemonAde.PhysicalArchitecture.Server.UserThread;
import knu.cse.blueLemonAde.ProblemDomain.Constants;
import knu.cse.blueLemonAde.ProblemDomain.Order;
import knu.cse.blueLemonAde.ProblemDomain.Room;
import knu.cse.blueLemonAde.Test.UserClientStart;

public class ServerConsole {

	private ObjectOutputStream objOutput;
	private Server server;
	private int userID;
	private int otherUserID = -1;

	private int userState = Constants.NONE;
	private int orderState = Constants.NONE;

	private Room room = null;
	private Order order = null;

	public ServerConsole(Server server, ObjectOutputStream objOutput, int userID) {
		this.server = server;
		this.objOutput = objOutput;
		this.userID = userID;
	}

	public void handleMsg(String msg) {
		System.out.println("받은 메시지 : " + msg);
		if (msg.startsWith("#cmd%start")) {//
			start(msg);
		} else if (msg.startsWith("#cmd%search")) {//
			search(msg);
		} else if (msg.startsWith("#cmd%makeroom")) {//
			msg = msg.substring(12);
			msg = "#cmd%search%" + msg;
			search(msg);
		} else if (msg.startsWith("#cmd%showroomList")) {//
			showRoomList(msg);
		} else if (msg.startsWith("#cmd%entrance")) {
			entrance(msg);
		} else if (msg.startsWith("#cmd%selectmenu")) {
			
		} else if (msg.startsWith("#cmd%checkmenu")) {

		} else if (msg.startsWith("#cmd%checkdeposit")) {

		} else if (msg.startsWith("#cmd%ordertrace")) {

		} else if (msg.startsWith("#cmd%complete")) {

		} else if (msg.startsWith("#chat")) {

		}
	}

	// ----------------function------------//

	private void start(String msg) {
		// TODO 저장된 위도경도랑 거리 비교하기
		sendToClientString("#cmd%fin");
	}
	
	private void search(String msg) {

		userState = Constants.WAIT_MATCHING;
		
		int[] param = null;

		if (msg.length() > 12) {
			msg = msg.substring(12);
			String[] token = msg.split("%");

			param = new int[token.length];

			for (int i = 0; i < token.length; i++) 
				param[i] = Integer.parseInt(token[i]);

			if (server == null)
				System.out.println("server is null");

			otherUserID = server.getWaitingQueue().offer(userID, param);
		} else {
			otherUserID = server.getWaitingQueue().offer(userID);
		}

		if (otherUserID >= 0) {
		//	server.getWaitingQueue().poll(otherUserID,
		//			server.getUserClientList().get(otherUserID).getServerConsole().room.getTypeIDs());
		//	server.getRoomList().removeRoom(otherUserID);

			/*
			 * TODO create chatting room func createChattingRoom(int userID, int
			 * otherUserID);
			 */
			sendToClientString("#cmd%fin%" + otherUserID);
			server.getUserClientList().get(otherUserID).getServerConsole().sendToClientString("#cmd%fin%" + userID);
			
		} else {
			if (param != null)
				room = new Room(server.getRoomList().getRoomList().size(), userID, param);
			else
				room = new Room(server.getRoomList().getRoomList().size(), userID);

			server.getRoomList().addRoom(room);

			sendToClientString("#cmd%fail%" + room.getRoomNum());
		}
	}

	private void showRoomList(String msg) {
		if (msg.length() > 17) {

			msg = msg.substring(18);
			String[] token = msg.split("%");

			int[] param = new int[token.length];

			for (int i = 0; i < token.length; i++) {
				param[i] = Integer.parseInt(token[i]);
			}
			sendToClientObject(server.getRoomList().getRoomList());
		}
		else
			sendToClientObject(server.getRoomList().getRoomList());
	}
	
	private void entrance(String msg){
		userState = ClientConstants.ENTRANCE;
		
		if(room != null) {
			String temp = "";
			
			for(int type : room.getTypeIDs()) {
				temp += "%";
				temp += type;
			}
			
			server.getWaitingQueue().poll(userID, room.getTypeIDs());
			server.getRoomList().removeRoom(userID);
			
			server.getUserClientList().get(otherUserID).getServerConsole().sendToClientString("#cmd%fin" + temp);
		}
		
		userState = ClientConstants.CHATTING;
		
		sendToClientString("#cmd%fin");
	}
	
	private void selectMenu(String msg) {
		
		msg = msg.substring(12);
		String[] token = msg.split("%");

		if(token.length < 3) {
			System.out.println("wrong param in select menu message");
			sendToClientString("#cmd%err");
			return;
		}
		
		int[] param = new int[token.length];

		for (int i = 0; i < token.length; i++) {
			param[i] = Integer.parseInt(token[i]);
		}
		sendToClientString("#cmd%fin");
		
		userState = Constants.WAIT_SELECT;
		
		for(UserThread userThread : server.getUserClientList()) {
			if(userThread.getUserid() == otherUserID) {
				userThread.getServerConsole().sendToClientString("#cmd%selectedmenu");
			}
		}
			
	}
	
	/*
	 * 
	 */
	private void sendToClientString(String line) {
		try {
			objOutput.writeObject(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendToClientObject(Object obj){
		try {
			objOutput.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}