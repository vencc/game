package chess;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ChessImpl;
import util.IChess;
import entity.RoomPojo;
import entity.User;


public class Room extends JFrame {
	private RoomList roomList;
	private Home home;
	private int rid;// 房间编号
	private User leftPlayer;// 房间内左边玩家
	private User rightPlayer;// 房间内右边玩家
	private int status;// 房间的状态
	private ChessTable chessPanel;
	private boolean left;

	public ChessTable getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessTable chessPanel) {
		this.chessPanel = chessPanel;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public Room(RoomPojo room, boolean left) {

		this.rid = room.getRid();
		this.leftPlayer = room.getLeftPlayer();
		this.left = left;
		this.rightPlayer = room.getRightPlayer();
		this.status = room.getStatus();
		init(0);
	}

	public RoomList getRoomList() {
		return roomList;
	}

	public void setRoomList(RoomList roomList) {
		this.roomList = roomList;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public User getLeftPlayer() {
		return leftPlayer;
	}

	public void setLeftPlayer(User leftPlayer) {
		this.leftPlayer = leftPlayer;
	}

	public User getRightPlayer() {
		return rightPlayer;
	}

	public void setRightPlayer(User rightPlayer) {
		this.rightPlayer = rightPlayer;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Room(Home home) {
		this.home = home;
		init(1);// 人机
	}

	public Room(RoomList roomList) {
		this.roomList = roomList;
		init(0);
	}

	/**
	 * 功能：初始化房间、棋盘 作者：林珊珊
	 * */
	public void init(int model) {// 联网对战0 人机对战1

		this.setTitle("五子棋");
		this.setLocation(345, 120);
		this.setSize(1000, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);
		getContentPane().setLayout(null);

		chessPanel = new ChessTable();
		chessPanel.setBounds(210, 100, 568, 568);
		getContentPane().add(chessPanel);

		JPanel gamer1 = new JPanel();
		gamer1.setBounds(10, 111, 180, 250);
		getContentPane().add(gamer1);
		gamer1.setLayout(null);

		JLabel lblNewLabel = new JLabel("对手信息标签");
		lblNewLabel.setBounds(48, 109, 72, 15);
		gamer1.add(lblNewLabel);

		JLabel label = new JLabel("假装有头像");
		label.setBackground(Color.PINK);
		label.setBounds(48, 45, 95, 54);
		gamer1.add(label);

		JPanel gamer2 = new JPanel();
		gamer2.setBounds(10, 408, 180, 250);
		getContentPane().add(gamer2);
		gamer2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("个人信息标签");
		lblNewLabel_1.setBounds(53, 108, 72, 15);
		gamer2.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("假装有头像");
		label_2.setBackground(Color.PINK);
		label_2.setBounds(53, 41, 117, 57);
		gamer2.add(label_2);

		/*
		 * ImageIcon icon_ready=new ImageIcon("resource/imag/ready_icon.png");
		 * JLabel Icon_ready = new JLabel(icon_ready); JPanel toastPanel = new
		 * JPanel(); toastPanel.setBackground(Color.BLACK);
		 * toastPanel.add(Icon_ready); gameRoom.add(toastPanel,
		 * BorderLayout.SOUTH); toastPanel.setVisible(false);
		 */

		JPanel chatRoom = new JPanel();
		chatRoom.setBounds(797, 100, 197, 658);
		getContentPane().add(chatRoom);
		chatRoom.setLayout(null);

		JLabel label_1 = new JLabel("聊天室");
		label_1.setBounds(71, 147, 60, 348);
		chatRoom.add(label_1);

		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(0, 0, 994, 101);
		getContentPane().add(logoPanel);
		logoPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 0, 994, 769);
		logoPanel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(
				"resource/imag/home.png"));

		JPanel UIPanel = new JPanel();
		UIPanel.setBounds(173, 670, 515, 33);
		getContentPane().add(UIPanel);
		UIPanel.setLayout(null);

		JButton But_ready = new JButton("准备");
		But_ready.setBounds(157, 5, 73, 23);
		UIPanel.add(But_ready);

		JButton But_exit = new JButton("退出");
		But_exit.setBounds(416, 5, 73, 23);
		But_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toRoomList();
			}
		});
		UIPanel.add(But_exit);

		JButton But_regret = new JButton("悔棋");
		But_regret.setBounds(240, 5, 78, 23);
		But_regret.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ChessTable.Moves > 0)
					chessPanel.unpaintItem();
				else {
					System.out.println("当前已经没有棋子了");
				}
			}
		});
		UIPanel.add(But_regret);

		JButton But_sur = new JButton("认输");
		But_sur.setBounds(328, 5, 78, 23);
		UIPanel.add(But_sur);
	}

	/**
	 * 功能：跳转至房间列表页面 作者：林珊珊
	 * */
	public void toRoomList() {
		if (home == null)
			roomList.setVisible(true);
		else {
			home.setVisible(true);
		}
		this.dispose();
	}


	public static void main(String[] args) {
		Room r = new Room(new Home());
	}
}
