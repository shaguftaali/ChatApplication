package com.thecherno.chernochat.srver;

public class ServerMain {
	
	int port;
	Server server;
	
	public ServerMain(int port) {
		this.port=port;
		server=new Server(port);
	}
	
	public static void main(String[] args) {
		int port;
		System.out.println("args "+args.length);
		if(args.length!=1) {
			System.out.println("usage: java -jar ChernoChatServer.jar [port]");
			return;
		}
		port=Integer.parseInt(args[0]);
		new ServerMain(port);
		
		
	}
}
