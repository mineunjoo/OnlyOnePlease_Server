package knu.cse.blueLemonAde.Test;

import knu.cse.blueLemonAde.PhysicalArchitecture.Client;

public class DeliveryClientStart {


	Client client;
	
	public DeliveryClientStart() {
		// TODO Auto-generated constructor stub
		client = new Client("127.0.0.1", 11114);
	}
	
	public static void main(String[] args){
		DeliveryClientStart start = new DeliveryClientStart();
	}
}
