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

/**
 * @author zzizzh
 * main server. It has roomList and client list.
 */
public class Server {
	private static final String Conatants = null;
	private int userPort;
	private int deliveryManPort;

	private static int userNumber = 0;
	private static int deliveryManNumber = 0;

	private static ArrayList<UserThread> userClientList; // 연결된 주문 배달 앱 사용자
															// 클라이언트 리스트
	private static ArrayList<DeliveryManThread> deliveryManClientList; // 연결된
																		// 배달원
																		// 클라이언트
																		// 리스트

	private RoomList roomList; // 현재 만들어진 방
	private WaitingQueue waitingQueue; // 매칭 대기열 큐

	private UserAcceptThread userAccept; // 사용자 클라이언트 accept 스레드
	private DeliveryManAcceptThread deliveryManAccept; // 배달원 클라이언트 accept 스레드

	
	/**
	 * @param userPort						
	 * @param deliveryManPort
	 */
	public Server(int userPort, int deliveryManPort) {
		userClientList = new ArrayList<UserThread>();
		deliveryManClientList = new ArrayList<DeliveryManThread>();

		roomList = new RoomList();
		waitingQueue = new WaitingQueue(Constants.TOP, 0);
		waitingQueue.printQueue();

		userAccept = new UserAcceptThread(userPort, this);
		userAccept.start();

		deliveryManAccept = new DeliveryManAcceptThread(deliveryManPort, this);
		deliveryManAccept.start();
	}

	static ArrayList<UserThread> getUserClientList() {
		return userClientList;
	}

	static ArrayList<DeliveryManThread> getDeliveryClientList() {
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
				userSocket = new ServerSocket(userPort);

				while (true) {
					userSock = userSocket.accept();
					UserThread userthread = new UserThread(server, userSock, userNumber++);
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

		private ServerSocket acceptSocket;
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
				acceptSocket = new ServerSocket(deliveryManPort);

				while (true) {
					deliveryManSock = acceptSocket.accept();
					DeliveryManThread deliveryManThread = new DeliveryManThread(server, deliveryManSock,
							deliveryManNumber++);
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

		private RoomList roomList;
		private WaitingQueue waitingQueue;

		int activeCount = 0;
		private Socket sock;
		private int userid;
		private Server server;

		private ObjectOutputStream serverOutputStream;
		private ObjectInputStream in;

		private ServerConsole serverConsole;

		public UserThread(Server server, Socket socket, int userid) {
			this.server = server;
			sock = socket;
			this.userid = userid;
			userClientList.add(this);
			activeCount = userClientList.size();
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
				System.out.println(inetaddr.getHostAddress() + " 에서 유저 클랑이언트 접속.");
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
				for (int i = 0; i < userClientList.size(); i++) {
					if (userid == userClientList.get(i).getUserid())
						userClientList.remove(i);
				}
			}
		}
	}

	class DeliveryManThread extends Thread {

		private RoomList roomList;
		private WaitingQueue waitingQueue;

		int activeCount = 0;
		private Socket sock;
		private int deliveryManid;
		private Server server;

		private ObjectOutputStream serverOutputStream;
		private ObjectInputStream in;

		private ServerConsole serverConsole;

		public DeliveryManThread(Server server, Socket deliveryManSock, int deliveryManid) {
			sock = deliveryManSock;
			this.deliveryManid = deliveryManid;
			activeCount = deliveryManClientList.size();
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
				for (int i = 0; i < deliveryManClientList.size(); i++) {
					if (deliveryManid == deliveryManClientList.get(i).getUserid())
						deliveryManClientList.remove(i);
				}
			}
		}
	}

}
