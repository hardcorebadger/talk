package trefethen.talk.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CommunicationServer implements Runnable {
	
	private int port;
	private boolean listen = true;
	
	public CommunicationServer(int p) {
		port = p;
	}
	
	@Override
	public void run() {
		// Create Server Socket
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server initialized");
		} 
		catch (IOException e) {
			System.out.println("Server failed to initialize");
			e.printStackTrace();
			System.exit(-1);
		}
		
		// Socket Loop
		while (listen) {
			Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				clientSocket.setTcpNoDelay(true);
				CommunicationServlet servlet = new CommunicationServlet(clientSocket);
				servlet.start();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		// if listen is turned off, close socket
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
