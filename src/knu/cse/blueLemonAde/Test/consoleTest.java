package knu.cse.blueLemonAde.Test;

import knu.cse.blueLemonAde.PhysicalArchitecture.Client;
import knu.cse.blueLemonAde.PhysicalArchitecture.Server;

public class consoleTest {

	Server server;
	Client client;
	
	public consoleTest() {
		// TODO Auto-generated constructor stub
		
		server = new Server(55555, 55556);
		client = new Client("127.0.0.1", 55555);
	}
	
	public static void main(String[] args){
		consoleTest start = new consoleTest();
	}
}
