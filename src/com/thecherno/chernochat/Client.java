package com.thecherno.chernochat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends JFrame {

	private JPanel contentPane;

	private String name, address;
	private int port;
	private JTextField txtMessage;
	JTextArea txtHistory;
	
	private DatagramSocket socket;
	private InetAddress ip;
	
	private Thread sendTherad;
	
	
	
	public Client(String name, String address, int port) {
		setTitle("Cherno Chat Client");
		this.name=name;
		this.address=address;
		this.port=port;
		boolean connection=openConnection(address, port);
		if(!connection) {
			System.err.println("Connection failed");
			console("Connection Failed");
		}
		createWindow();
		console("Attempting a connection to "+ address+" : "+port+", user : "+name);
	}
	
	private boolean openConnection(String address, int port) {
		try {
			ip=InetAddress.getByName(address);
			try {
				socket=new DatagramSocket(port);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private String recive() {
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
	
	private void send(final byte[] data) {
		sendTherad=new Thread("Send") {
			public void run() {
				DatagramPacket packet=new DatagramPacket(data,data.length,ip,port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		sendTherad.start();
	}

	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 840, 573);
		setSize(840,550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50,750, 30,10};
		gbl_contentPane.rowHeights = new int[]{60,470,20};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		txtHistory = new JTextArea();
		txtHistory.setEditable(false);
		JScrollPane scroll=new JScrollPane(txtHistory);
		GridBagConstraints gbc_txtHistory = new GridBagConstraints();
		gbc_txtHistory.insets = new Insets(0, 0, 5, 5);
		gbc_txtHistory.fill = GridBagConstraints.BOTH;
		gbc_txtHistory.gridx = 0;
		gbc_txtHistory.gridy = 0;
		gbc_txtHistory.gridwidth=3;
		gbc_txtHistory.gridheight=2;
		gbc_txtHistory.insets=new Insets(20,20,20,0);
		contentPane.add(scroll, gbc_txtHistory);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					send();
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth=2;
		gbc_txtMessage.insets=new Insets(0,20,0,0);
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		gbc_btnSend.insets=new Insets(0,20,0,0);
		contentPane.add(btnSend, gbc_btnSend);
		setVisible(true);
		txtMessage.requestFocus();
	}
	
	private void send() {
		String msg=txtMessage.getText();
		if(msg.equals("")) {
			return;
		}
		msg=name+ ": "+msg;
		console(msg);
		txtMessage.setText("");
	}	
	
	public void console(String msg) {
		txtHistory.append(msg+"\n\r");
	}
}
