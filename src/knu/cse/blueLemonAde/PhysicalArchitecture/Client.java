package knu.cse.blueLemonAde.PhysicalArchitecture;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
	Socket sock;
	clientWrite clientW;
	clientRead clientR;
	private ClientControl cControl;

	public Client(String host, int port)
	{
		cControl = new ClientControl(this);
		try { 
			System.out.println("-----클라이언트가 실행되었습니다.");
			sock = new Socket(host, port);

		} catch (IOException e) {
			e.printStackTrace();
		} 

		// TO-DO cControl 없애야함.
		clientW=new clientWrite(sock, cControl);
		clientR=new clientRead(sock,cControl);
		clientW.start();
		clientR.start();
	}

	public void sendToServer(String msg)
	{
		clientW.sendToServer(msg);
	}
	public ClientControl getcControl() {
		return cControl;
	}
	public void setcControl(ClientControl cControl) {
		this.cControl = cControl;
	}
}
class clientRead extends Thread//서버로 부터 메세지 받기.
{
	Socket socket;
	private ClientControl cControl;

	public clientRead(Socket socket,ClientControl cControl)
	{
		this.socket=socket;
		this.cControl=cControl;
	}
	
	public void run()
	{
		try {
			ObjectInputStream clientInputStream = new ObjectInputStream(socket.getInputStream());
			Object temp;
			while(true)
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(cControl.getSList().size() > 0) {
					cControl.handleMsg(cControl.getSList().get(0));
				}
				while(cControl.getCList().size() > 0) {
					if(cControl.getUserState() == ClientConstants.CHATTING)
						System.out.println(cControl.getCList().get(0));
					else
						cControl.getCList().clear();
				}
				
				temp = clientInputStream.readObject();
				
				if(temp instanceof String)
				{
					String line = (String) temp;
					System.out.println("-----서버로 받은 msg >: "+line);
					cControl.addString(line);
				}
			}	
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {

				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}	


}

class clientWrite extends Thread//서버로 메세지 보내기
{
	private Socket socket;
	private String console;
	
	// TO-DO 테스트용임 나중에 없애야함.
	private ClientControl cControl;
	
	private boolean sendToReadyString;
	
	private Scanner scanner;
	private String temp;
	
	public clientWrite(Socket socket, ClientControl cControl)
	{
		this.socket=socket;
		this.cControl = cControl;
		sendToReadyString=false;
		
		scanner = new Scanner(System.in);
	}
	public void run()
	{
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(socket.getOutputStream());

			while (true) //&전체 thread의 반복문
			{			
				temp = scanner.nextLine();
				
				if(temp.startsWith("#cmd%search"))
					cControl.setUserState(ClientConstants.WAIT_MATCHING);
				
				sendToServer(temp);
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(sendToReadyString==true)//& 보낼준비가 될때만 실행도록 하는 반복문
				{
					out.writeObject(console);
					sendToReadyString=false;
					System.out.println("-----server로 -메세지-전송");					
				}

			}		
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendToServer(String msg)
	{
		console=msg;
		sendToReadyString=true;
	}
}