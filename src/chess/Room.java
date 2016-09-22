package chess;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Chess;
public class Room extends JFrame{
	private RoomList roomList;
	
	public Room() {
		//this.roomList = roomList;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel gamerInfo = new JPanel();
		getContentPane().add(gamerInfo, BorderLayout.WEST);

		JPanel gameRoom = new JPanel();
		getContentPane().add(gameRoom, BorderLayout.CENTER);
		gameRoom.setLayout(new BorderLayout(0, 0));
		
		JPanel UIPanel = new JPanel();
		gameRoom.add(UIPanel, BorderLayout.SOUTH);
		gamerInfo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel gamer1 = new JPanel();
		gamerInfo.add(gamer1);
		
		JPanel gamer2 = new JPanel();
		gamerInfo.add(gamer2);
		

		Chess chessPanel=new Chess();
		gameRoom.add(chessPanel, BorderLayout.CENTER);
		UIPanel.setLayout(new BorderLayout(0, 0));
		
		JButton But_ready = new JButton("准备");
		UIPanel.add(But_ready, BorderLayout.WEST);
		
		JButton But_start = new JButton("开始");
		UIPanel.add(But_start, BorderLayout.CENTER);
		
		JButton But_exit = new JButton("退出");
		UIPanel.add(But_exit, BorderLayout.EAST);
		
		gamer1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("对手信息标签");
		gamer1.add(lblNewLabel);
				
		JLabel lblNewLabel_1 = new JLabel("个人信息标签");
		gamer2.add(lblNewLabel_1);
		
		
		JPanel logoPanel = new JPanel();
		gameRoom.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lbllogo = new JLabel("五子棋LOGO");
		logoPanel.add(lbllogo, BorderLayout.NORTH);
		
		JPanel chatRoom = new JPanel();
		getContentPane().add(chatRoom, BorderLayout.EAST);
		chatRoom.setLayout(new BorderLayout(0, 0));
		
		JLabel label_1 = new JLabel("聊天室");
		chatRoom.add(label_1);
		init();
	}
	/**
	 * 功能：初始化房间、棋盘
	 * 作者：林珊珊
	 * */
	public void init() {

		this.setTitle("五子棋");
		this.setLocation(345, 120);
		this.setSize(1000, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	/**
	 * 功能：跳转至房间列表页面
	 * 作者：林珊珊
	 * */
	public void toRoomList() {
		roomList.setVisible(true);
		// �رձ����� this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Room roompanel=new Room();
	}
}
