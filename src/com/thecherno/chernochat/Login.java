package com.thecherno.chernochat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JLabel lblIpAddress;
	private JLabel lblPort;
	private JTextField txtPort;

	
	public Login() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setResizable(false);
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setTitle("Login");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 421);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(83, 80, 184, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(136, 49, 79, 20);
		contentPane.add(lblNewLabel);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(83, 153, 184, 20);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblIpAddress = new JLabel("IP Address");
		lblIpAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIpAddress.setBounds(107, 122, 136, 20);
		contentPane.add(lblIpAddress);
		
		lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPort.setBounds(107, 195, 136, 20);
		contentPane.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(83, 226, 184, 20);
		contentPane.add(txtPort);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =txtName.getText();
				String address=txtAddress.getText();
				int port=Integer.parseInt(txtPort.getText());
				login(name, address, port);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(131, 329, 89, 23);
		contentPane.add(btnLogin);
	}
	
	/**
	 * Login stuff
	 */
	private void login(String name, String address, int port) {
		dispose();
		new ClientWindow(name, address, port);
		System.out.println(name+" , "+address+" , "+port);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
