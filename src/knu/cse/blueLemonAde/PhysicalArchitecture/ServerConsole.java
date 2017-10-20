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
	 * 클라이언트에서 받은 명령어를 처리하는 함수 명령어는 #으로 시작하고 $로 토큰을 구분한다.
	 */
	public void handleMeg(String msg) {
		System.out.println("클라이언트로부터 받은 문자열 : " + msg);
		
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