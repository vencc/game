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
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import msg.*;
import net.MyClient;
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
	private static boolean canplay=false;
	private static boolean beforeRegret=false;
	public static JLabel labelnow =null;
	//private boolean isLeftPlay=false;//左边玩家是否可落子
	//private boolean isRightPlay=false;//右边玩家是否可落子
	


	public boolean isCanplay() {
		return canplay;
	}

	public void setCanplay(boolean canplay) {
		this.canplay = canplay;
	}

	private int status;// 房间的状态
	private ChessTable chessPanel;
	public static boolean isleft;

	public ChessTable getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessTable chessPanel) {
		this.chessPanel = chessPanel;
	}

	/**
	 * @wbp.parser.constructor
	 */
     
	public Room(int roomid, boolean isleft,RoomList roomList) {
		this.roomList=roomList;  
		MyClient.getMyClient().setRoom(this);
		System.out.println("网络对战");
		this.rid = roomid;
		this.isleft = isleft;
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

	/**
	 * 功能：初始化房间、棋盘 作者：林珊珊
	 * */
	public void init(final int model) {// 联网对战0 人机对战1

		this.setTitle("五子棋");
		this.setLocation(345, 120);
		this.setSize(1000, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);
		getContentPane().setLayout(null);

		if(model==0)//网络对战
		chessPanel = new ChessTable(this);
		else{
			chessPanel = new ChessTable();
		}
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
		 labelnow = new JLabel("isCanplay()="+isCanplay());
		labelnow.setBounds(300, 400, 150, 150);
		chessPanel.add(labelnow);
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
		But_ready.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientBeReady msg = new ClientBeReady(rid,isleft);
				MyClient.getMyClient().sendMsg(msg);//发给服务器
			}
		});
		But_ready.setBounds(157, 5, 73, 23);
		UIPanel.add(But_ready);

		/**
		 * 退出按钮
		 */
		JButton But_exit = new JButton("退出");
		But_exit.setBounds(416, 5, 73, 23);
		But_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(model==0) {
					ClientOutRoomMsg msg = new ClientOutRoomMsg(rid, isleft);
					MyClient.getMyClient().sendMsg(msg);
				}
				toRoomList();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(model==0) {
					ClientOutRoomMsg msg = new ClientOutRoomMsg(rid, isleft);
					MyClient.getMyClient().sendMsg(msg);
					ClientOffMsg msg1 = new ClientOffMsg();
					MyClient.getMyClient().sendMsg(msg1);
				}
			}
		});
		UIPanel.add(But_exit);

		JButton But_regret = new JButton("悔棋");
		But_regret.setBounds(240, 5, 78, 23);
		But_regret.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(model==0){//联机
				beforeRegret=isCanplay();
				setCanplay(false);
				labelnow.setText("isCanplay="+isCanplay());
				ClientBackChess msg=new ClientBackChess(rid,isleft);
				MyClient.getMyClient().sendMsg(msg);//发给服务器
				
				}else{//人机
					chessPanel.unpaintItem();
				}
				//if (ChessTable.Moves > 0){
					//chessPanel.unpaintItem();
					
			//	}
				//else {
					//System.out.println("当前已经没有棋子了");
				//}
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

	public void gameStart() {
		if(isleft){
			setCanplay(true);			
		}
		
		
		//if((isLeftPlay()==false)&&(isRightPlay()==false)){
			//setLeftPlay(true);//黑棋先手
		//}
	}

	public void decide() {
		boolean result;
		 String[] options = { "同意", "不同意"  }; 
		 int res=JOptionPane.showOptionDialog(this, "对方请求悔棋", "是否同意", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION, 
		null, options, options[0]); 

		if(res==0){
			result=true;
		}else{
			result=false;
		}
		if(result){
			setCanplay(false);
		}
		ClientBackResult msg=new ClientBackResult(result, rid, isleft);
		MyClient.getMyClient().sendMsg(msg);
	}

	public void BackFail() {
		if(beforeRegret){
			setCanplay(true);
		}
		JOptionPane.showMessageDialog(this,
				"对方不同意你的请求", "", JOptionPane.ERROR_MESSAGE); 
		
		
	}

	public void BackSucceed() {
		setCanplay(true);
		chessPanel.unpaintItem();//本身面板
		ClientMovePieces msg=new ClientMovePieces(rid,isleft,ChessImpl.chess,true);
		MyClient.getMyClient().sendMsg(msg);
		JOptionPane.showMessageDialog(this,
				"alert", "对方同意了你的请求", JOptionPane.ERROR_MESSAGE); 
	}

	public void win() {
		setCanplay(false);
		JOptionPane.showMessageDialog(this,
				"你赢了！", "大侠，在下甘拜下风！！", JOptionPane.ERROR_MESSAGE); 
		
	}

	public void deafeat() {
		JOptionPane.showMessageDialog(this,
				"你输了！", "胜败乃兵家常事，壮士请重新来过", JOptionPane.ERROR_MESSAGE); 
	}
}
