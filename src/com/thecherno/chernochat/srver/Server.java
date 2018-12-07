package com.thecherno.chernochat.srver;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable{

	int port;
    private	DatagramSocket socket;
    private boolean running=false;
    private Thread run;
	
	public Server(int port) {
		this.port=port;
		try {
			socket=new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run=new Thread(this,"Server");
	}

	@Override
	public void run() {
		running=true;
		manageClients();
		recive();
		
	}
	
	private void manageClients() {
		
	}
	
	private void recive() {
		
	}
}
