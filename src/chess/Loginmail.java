package chess;

import javax.swing.JFrame;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

import java.awt.Rectangle;
import java.awt.Dimension;

public class Loginmail extends JFrame{
	
	public String username=null;
	public String password=null;
	private JPasswordField passwordField;
	JTextArea textField=null;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public Loginmail(String username, String password) throws HeadlessException {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Loginmail() {
		setBounds(new Rectangle(200, 200, 50, 50));
		setSize(400,400);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("lib\\2.png"));
		/*
		 * 創建一個窗體界面，代表郵箱登陸的界面
		 * 
		 */
		setTitle("\u6B61\u8FCE\u4F7F\u7528QQ\u90F5\u7BB1\u53CD\u994B");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u90AE\u7BB1\u5E10\u53F7");
		label.setBounds(82, 86, 54, 15);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\u90AE\u7BB1\u5BC6\u7801");
		lblNewLabel.setBounds(82, 126, 54, 15);
		panel.add(lblNewLabel);
		
		/*
		 * 設置賬戶名輸入文本框和密碼框
		 */
		textField= new JTextArea();
		textField.setBounds(146, 82, 123, 24);
		panel.add(textField);
		

		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 120, 123, 24);
		panel.add(passwordField);
		
		
		JLabel lblNewLabel_1 = new JLabel("\u90AE\u7BB1\u767B\u9646\u5165\u53E3");
		lblNewLabel_1.setBounds(140, 25, 83, 36);
		panel.add(lblNewLabel_1);
		
	/*	JRadioButton rdbtnNewRadioButton = new JRadioButton("QQ\u767B\u9646\u5165\u53E3");
		rdbtnNewRadioButton.setBounds(131, 170, 121, 23);
		panel.add(rdbtnNewRadioButton);*/
		
		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				 username=textField.getText();
				 password=passwordField.getText();
					if((username!=null)&&(password!=null))
					{
						new Sendmail().setVisible(true);	
						init();
				 }
				/*	else{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(),
							       "弹出的是消息提示框!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
					}*/
			}
			

		});
	
		btnNewButton.setBounds(159, 199, 93, 23);
		panel.add(btnNewButton);
		
	
	}
	public void init(){
		this.setVisible(false);
	}
	public static void main(String[] args) {
		
		new Sendmail().setVisible(true);
		
	}

}
