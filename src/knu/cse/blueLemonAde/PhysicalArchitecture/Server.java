package knu.cse.blueLemonAde.PhysicalArchitecture;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import knu.cse.blueLemonAde.Foundation.RoomList;
import knu.cse.blueLemonAde.Foundation.WaitingQueue;
import knu.cse.blueLemonAde.ProblemDomain.Constants;

/*
 * main server. It has roomList and client list.
 */
public class Server extends Thread
{
	private static final String Conatants = null;
	private ServerSocket serverSocket;
	private Socket sock;
	private int port;					
	private int clientNumber;								
	private static ArrayList<EchoThread> clientList;		
	
	private RoomList roomList;
	private WaitingQueue waitingQueue;
	
	public Server(int port) {
		clientList = new ArrayList<EchoThread>();
		this.port = port;
		clientNumber = 0;
		
		roomList = new RoomList();
		waitingQueue = new WaitingQueue(Constants.TOP, 0);
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(port);

			while (true) {
				sock = serverSocket.accept(); 
				EchoThread echothread = new EchoThread(this, clientNumber++); 
				echothread.start(); 
				System.out.println("ActiveCount : " + EchoThread.activeCount);
				clientList.add(echothread);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Socket getSocket() {
		return sock;
	}
	
	public static ArrayList<EchoThread> getEchoThreadList() {
		return clientList;
	}
	
	public RoomList getRoomList() {
		return roomList;
	}
	
	public WaitingQueue getWaitingQueue() {
		return waitingQueue;
	}
}

class EchoThread extends Thread { 
	private ArrayList<EchoThread> clientList;
	static int activeCount = 0;

	private Socket sock;
	private int clientNumber;
	private Server server;
	
	private ObjectOutputStream serverOutputStream;
	private ObjectInputStream in;

	private ServerConsole serverConsole; 

	public EchoThread(Server server, int clientNumber) {
		sock = server.getSocket();
		activeCount++; 
		this.clientNumber = clientNumber;
		Server.getEchoThreadList();
	} 

	public int getClientNumber() {
		return clientNumber;
	}

	public ObjectOutputStream getOutput() {
		return serverOutputStream;
	}

	public ServerConsole getServerConsole() {
		return serverConsole;
	}
	
	public void run() { 
		try { 
			InetAddress inetaddr = sock.getInetAddress();
			System.out.println(inetaddr.getHostAddress() + " �κ��� �����Ͽ����ϴ�.");
			serverOutputStream = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			serverConsole = new ServerConsole(server, serverOutputStream, clientNumber);

			Object temp;
			String line = null;

			while(true)
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				temp = in.readObject();
				
				if(temp instanceof String)
				{
					line = (String) temp;
					System.out.println("-----send String message to server : " + line);
					serverConsole.handleMeg(line);
				}
			}

		} catch (Exception ex) {
			activeCount--;
			System.out.println(ex);
			clientList = Server.getEchoThreadList();
			for (int i = 0; i < clientList.size(); i++)
			{
				if (clientNumber == clientList.get(i).getClientNumber())
					clientList.remove(i);
			}
		}
	}


}

