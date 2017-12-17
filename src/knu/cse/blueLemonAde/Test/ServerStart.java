package knu.cse.blueLemonAde.Test;

import knu.cse.blueLemonAde.PhysicalArchitecture.Client;
import knu.cse.blueLemonAde.PhysicalArchitecture.Server;

public class ServerStart {

	Server server;
	Client client;
	
	public ServerStart() {
		// TODO Auto-generated constructor stub
		
		server = new Server(11113, 11114);
	}
	
	public static void main(String[] args){
		ServerStart start = new ServerStart();
	}
}
