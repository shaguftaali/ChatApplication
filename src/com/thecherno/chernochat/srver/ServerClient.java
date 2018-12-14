package com.thecherno.chernochat.srver;

import java.net.InetAddress;

public class ServerClient {
	public String name;
	public InetAddress address;
	public int port;
	private final int ID;
	public int attempt=0;
	
	public ServerClient(String name, InetAddress address, int port, final int ID) {
		this.name=name;
		this.address=address;
		this.ID=ID;
		this.port=port;
	}
	
	public int getID() {
		return ID;
	}
}
