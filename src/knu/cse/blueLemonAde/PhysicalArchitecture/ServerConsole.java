package knu.cse.blueLemonAde.PhysicalArchitecture;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import knu.cse.blueLemonAde.Foundation.RoomList;
import knu.cse.blueLemonAde.ProblemDomain.Room;

public class ServerConsole {

	private ObjectOutputStream objOutput;
	private Server server;
	private int userID;
	private int state;
	
	private Room room;

	public ServerConsole(Server server, ObjectOutputStream objOutput, int userID) {
		this.server = server;
		this.objOutput = objOutput;
		this.userID = userID;
	}

	/*
	 * Ŭ���̾�Ʈ���� ���� ��ɾ ó���ϴ� �Լ� ��ɾ�� #���� �����ϰ� $�� ��ū�� �����Ѵ�.
	 */
	public void handleMeg(String msg) {
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� ���ڿ� : " + msg);

		if (msg.startsWith("#cmd$search")) {
			search(msg);
		} else if (msg.startsWith("#cmd$showroomList")) {
			showRoomList(msg);
		} else if (msg.startsWith("#cmd$entrance")) {

		} else if (msg.startsWith("#cmd$selectmenu")) {

		} else if (msg.startsWith("#cmd$checkmenu")) {

		} else if (msg.startsWith("#cmd$checkdeposit")) {

		} else if (msg.startsWith("#cmd$complete")) {

		} else if (msg.startsWith("#cmd$complete")) {

		} else if (msg.startsWith("#chat")) {

		}
	}

	// ----------------function------------//

	private void search(String msg) {
		int otherUserID;
		int[] param = null;

		if (msg.length() > 12) {
			msg = msg.substring(12);
			String[] token = msg.split("$");

			param = new int[token.length];

			otherUserID = server.getWaitingQueue().offer(userID, param);
		} else {
			otherUserID = server.getWaitingQueue().offer(userID);
		}

		if (otherUserID >= 0) { 
			server.getWaitingQueue().poll(otherUserID,
					server.getEchoThreadList().get(otherUserID).getServerConsole().room.getTypeIDs());
			server.getRoomList().removeRoom(otherUserID);

			/*
			 * TODO create chatting room func createChattingRoom(int userID, int
			 * otherUserID);
			 */
			
		} else {
			if(param != null)
				room = new Room(server.getRoomList().getRoomList().size(), userID, param);
			else 
				room = new Room(server.getRoomList().getRoomList().size(), userID);
			
			server.getRoomList().addRoom(room);
		}
	}

	private void showRoomList(String msg) {
		msg = msg.substring(18);
		String[] token = msg.split("$");

		int[] param = new int[token.length];

		for (int i = 0; i < token.length; i++) {
			param[i] = Integer.parseInt(token[i]);
		}

		try {
			objOutput.writeObject(server.getRoomList().getRoomList(param));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}