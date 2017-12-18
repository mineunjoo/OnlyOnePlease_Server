package knu.cse.blueLemonAde.PhysicalArchitecture;

import java.util.ArrayList;
import knu.cse.blueLemonAde.PhysicalArchitecture.ClientConstants;
import knu.cse.blueLemonAde.ProblemDomain.Constants;


public class ClientControl {
	
	private Client client;
	private ArrayList<String> SList;
	private ArrayList<String> CList;
	private int userState = Constants.NONE;
	
	public ClientControl(Client client)
	{
		this.client = client;
		SList = new ArrayList<String>();
		CList = new ArrayList<String>();
	}	
	
	//////////test
	public void setUserState(int userState) {
		this.userState = userState;
	}
	
	public int getUserState() {
		return userState;
	}

	public void addString(String string)
	{
		SList.add(string);
	}

	public ArrayList<String> getSList() {
		return SList;
	}
	
	public ArrayList<String> getCList() {
		return CList;
	}
	
	void handleMsg(String msg) {
		System.out.println("받은 메시지 : " + msg);
		
		if (msg.startsWith("#cmd%fin")) {//
			switch(userState) {
			case ClientConstants.WAIT_MATCHING:
				client.clientW.sendToServer("#cmd%entrance");
				userState = ClientConstants.ENTRANCE;
				break;
			case ClientConstants.SHOWROOMLIST:
				break;
			case ClientConstants.ENTRANCE:
				userState = ClientConstants.CHATTING;
				break;
			case ClientConstants.SELECT_MENU:
			case ClientConstants.CHECK_MENU:
			case ClientConstants.COMPLETE_ORDER:
			}
		} else if (msg.startsWith("#cmd%fail")) {
			switch(userState) {
			case ClientConstants.WAIT_MATCHING:
				break;
			case ClientConstants.SHOWROOMLIST:
				break;
			case ClientConstants.ENTRANCE:
				break;
			case ClientConstants.SELECT_MENU:
			case ClientConstants.CHECK_MENU:
			case ClientConstants.COMPLETE_ORDER:
			}
		} else if (msg.startsWith("#cmd%err")) {
			
		} else if (msg.startsWith("#chat")) {
			CList.add(msg.substring(6));
		}
		SList.remove(0);
	}
}