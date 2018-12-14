package com.thecherno.chernochat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {


	private String name, address;
	private DatagramSocket socket;
	private int port;
	private InetAddress ip;
	
	private Thread sendTherad;
	private int ID;

	public Client(String name, String address, int port) {
		
		this.name=name;
		this.address=address;
		this.port=port;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;	
	}
	
	public int getPort() {
		return port;
	}
	
	public boolean openConnection(String address, int port) {
		try {
			try {
				socket=new DatagramSocket();
				ip=InetAddress.getByName(address);
			} catch (SocketException e) {
				e.printStackTrace();
				return false;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String recive() {
		byte[] data=new byte[1024];
		DatagramPacket packet=new DatagramPacket(data,data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		String msg=new String(packet.getData());
		return msg;
	}
	
	public void send(final byte[] data) {
		sendTherad=new Thread("Send") {
			public void run() {
				DatagramPacket packet=new DatagramPacket(data,data.length,ip,port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		sendTherad.start();
	}
	
	public void close() {
		new Thread() {
			public void run() {
				
				synchronized(socket){
					
					socket.close();
				}
			}
		}.start();
	}

	public void setID(int id) {
		this.ID=id;
		
	}
	
	public int getID() {
		return ID;
	}


}
