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
public class Server {
	private static final String Conatants = null;
	private ServerSocket deliveryManSocket;
	private int userPort;
	private int deliveryManPort;

	private static int userNumber = 0;
	private static int deliveryManNumber = 0;

	private static ArrayList<UserThread> userClientList;
	private static ArrayList<DeliveryManThread> deliveryManClientList;

	private RoomList roomList;
	private WaitingQueue waitingQueue;

	private UserAcceptThread userAccept;
	private DeliveryManAcceptThread deliveryManAccept;
	
	public Server(int userPort, int deliveryManPort) {
		userClientList = new ArrayList<UserThread>();

		roomList = new RoomList();
		waitingQueue = new WaitingQueue(Constants.TOP, 0);
	}


	static ArrayList<UserThread> getUserThreadList() {
		return userClientList;
	}

	static ArrayList<DeliveryManThread> getDeliveryThreadList() {
		return deliveryManClientList;
	}

	public RoomList getRoomList() {
		return roomList;
	}

	public WaitingQueue getWaitingQueue() {
		return waitingQueue;
	}

	class UserAcceptThread extends Thread {

		private ServerSocket userSocket;
		Server server;
		int userPort;

		private Socket userSock;
		public Socket getUserSocket() {
			return userSock;
		}

		public UserAcceptThread(int userPort, Server server) {
			super();
			this.userPort = userPort;
			this.server = server;
		}

		@Override
		public void run() {
			try {
				userSocket = new ServerSocket(deliveryManPort);

				while (true) {
					userSock = userSocket.accept();
					UserThread userthread = new UserThread(server, userNumber++);
					userthread.start();

					userClientList.add(userthread);
					System.out.println("userClient Count : " + userClientList.size());
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	class DeliveryManAcceptThread extends Thread {

		private ServerSocket deliveryManSocket;
		int deliveryManNumber = 0;
		Server server;
		int deliveryManPort;

		private Socket deliveryManSock;

		public Socket getDeliveryManSocket() {
			return deliveryManSock;
		}
		
		public DeliveryManAcceptThread(int deliveryManPort, Server server) {
			super();
			this.deliveryManPort = deliveryManPort;
			this.server = server;
		}

		@Override
		public void run() {
			try {
				deliveryManSocket = new ServerSocket(deliveryManPort);

				while (true) {
					deliveryManSock = deliveryManSocket.accept();
					DeliveryManThread deliveryManThread = new DeliveryManThread(server, deliveryManNumber++);
					deliveryManThread.start();

					deliveryManClientList.add(deliveryManThread);
					System.out.println("deliveryManClient Count : " + deliveryManClientList.size());
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	class UserThread extends Thread {
		private ArrayList<UserThread> clientList;

		private RoomList roomList;
		private WaitingQueue waitingQueue;

		int activeCount = 0;
		private Socket sock;
		private int userid;
		private Server server;

		private ObjectOutputStream serverOutputStream;
		private ObjectInputStream in;

		private ServerConsole serverConsole;

		public UserThread(Server server, int userid) {
			sock = server.userAccept.getUserSocket();
			this.userid = userid;
			Server.getUserThreadList().add(this);
			activeCount = Server.getUserThreadList().size();
		}

		public int getUserid() {
			return userid;
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
				serverConsole = new ServerConsole(server, serverOutputStream, userid);

				Object temp;
				String line = null;

				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp = in.readObject();

					if (temp instanceof String) {
						line = (String) temp;
						System.out.println("-----send String message to server : " + line);
						serverConsole.handleMeg(line);
					}
				}

			} catch (Exception ex) {
				activeCount--;
				System.out.println(ex);
				clientList = Server.getUserThreadList();
				for (int i = 0; i < clientList.size(); i++) {
					if (userid == clientList.get(i).getUserid())
						clientList.remove(i);
				}
			}
		}
	}

	class DeliveryManThread extends Thread {
		private ArrayList<UserThread>  List;

		private RoomList roomList;
		private WaitingQueue waitingQueue;

		int activeCount = 0;
		private Socket sock;
		private int deliveryManid;
		private Server server;

		private ObjectOutputStream serverOutputStream;
		private ObjectInputStream in;

		private ServerConsole serverConsole;

		public DeliveryManThread(Server server, int deliveryManid) {
			sock = server.deliveryManAccept.getDeliveryManSocket();
			this.deliveryManid = deliveryManid;
			Server.getDeliveryThreadList().add(this);
			activeCount = Server.getDeliveryThreadList().size();
		}

		public int getUserid() {
			return deliveryManid;
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
				serverConsole = new ServerConsole(server, serverOutputStream, deliveryManid);

				Object temp;
				String line = null;

				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp = in.readObject();

					if (temp instanceof String) {
						line = (String) temp;
						System.out.println("-----send String message to server : " + line);
						serverConsole.handleMeg(line);
					}
				}

			} catch (Exception ex) {
				activeCount--;
				System.out.println(ex);
				deliveryManClientList = Server.getDeliveryThreadList();
				for (int i = 0; i < deliveryManClientList.size(); i++) {
					if (deliveryManid == deliveryManClientList.get(i).getUserid())
						deliveryManClientList.remove(i);
				}
			}
		}
	}

}
