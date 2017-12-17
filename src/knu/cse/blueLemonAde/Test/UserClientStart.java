package knu.cse.blueLemonAde.Test;

import knu.cse.blueLemonAde.PhysicalArchitecture.Client;
import knu.cse.blueLemonAde.PhysicalArchitecture.Server;

public class UserClientStart {

	Client client;
	
	public UserClientStart() {
		// TODO Auto-generated constructor stub
		client = new Client("127.0.0.1", 11113);
	}
	
	public static void main(String[] args){
		UserClientStart start = new UserClientStart();
	}
}
