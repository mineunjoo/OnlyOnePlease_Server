package knu.cse.blueLemonAde.PhysicalArchitecture;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ServerConsole {

	private ObjectOutputStream objOutput;
	private Server server;
	private int userID;
	
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
		//	register(msg);
		}else if(msg.startsWith("#cmd$roomList")) {
			
		}else if(msg.startsWith("#cmd$entrance")) {
			
		}else if(msg.startsWith("#cmd$selectmenu")) {
			
		}else if(msg.startsWith("#cmd$checkmenu")) {
			
		}else if(msg.startsWith("#cmd$checkdeposit")) {
			
		}else if(msg.startsWith("#cmd$complete")) {
			
		}else if(msg.startsWith("#cmd$complete")) {
			
		}else if(msg.startsWith("#chat")) {
			
		}
	}

	// ----------------function------------//
		
}