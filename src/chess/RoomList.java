package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import msg.ClientClickRoomMsg;
import net.MyClient;
import entity.RoomPojo;
import entity.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

public class RoomList extends JFrame{
	
	JPanel panel_4 = new JPanel();
	ArrayList<RoomPojo> rooms = new ArrayList();
	Home home = new Home();
    User user = null;
    JList list = new JList();
	public RoomList(Home home,final User user) {
		
		this.home= home;
		this.user=user;
	//	home.getMyClient().setRoomList(this);//让客户端拥有游戏大厅
		setSize(new Dimension(1000, 700));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(232, 45, 761, 621);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 751, 611);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPane);
	
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(new GridLayout(6, 2, 0, 0));
	    
	    
	    for(int i = 0;i<12;i++)
	    {
	    	RoomPojo r = new RoomPojo(0,null,null,RoomPojo.IDLE);
	    	rooms.add(r);
	    }
	    showRoomList(rooms);
		
	       
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(232, 0, 761, 45);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton button = new JButton("\u5FEB\u901F\u8FDB\u5165");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addMouseListener(new MouseAdapter() {//快速进入按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				int k=0;
				 for(int i=0;i<rooms.size();i++){
					 if(rooms.get(i).getStatus()==1){
						 int roomid = Integer.parseInt(((JComponent) e.getSource())
									.getParent().getName());//获得对应房间的名字
			        		System.out.println("roomid:" + roomid);
							//房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
							ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid,user,true);
							MyClient.getMyClient().sendMsg(msg);//发给服务器
						 k=1;
						 return;
					 }else if(rooms.get(i).getStatus()==0){
						 
						 int roomid = Integer.parseInt(((JComponent) e.getSource())
									.getParent().getName());//获得对应房间的名字
			        		System.out.println("roomid:" + roomid);
							//房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
							ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid,user,true);
						    MyClient.getMyClient().sendMsg(msg);//发给服务器
						 k=1;
						 return;
					      }
					 }
				       if(k==0){
				    	   JOptionPane.showMessageDialog(null, "所有房间已满人，请等候！");
				       }
				 }
		});
		button.setBounds(228, 5, 127, 30);
		panel_3.add(button);
		
		JButton btnNewButton = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseAdapter() {//退出登录按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tohome();
			}
		});
		btnNewButton.setBounds(393, 5, 108, 30);
		panel_3.add(btnNewButton);
		
		JButton button_1 = new JButton(new ImageIcon(user.getFileName()));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //用户头像按钮
			//	new UserInfoFrame(user).setVisible(true);//进入个人信息界面
			}
		});
		button_1.setBounds(113, 0, 45, 45);
		panel_3.add(button_1);
		
		JButton button_3 = new JButton("\u53CD\u9988");
		button_3.addMouseListener(new MouseAdapter() {//反馈按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				new SendmailFrame();
			}
		});
		button_3.setBounds(537, 5, 93, 30);
		panel_3.add(button_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 232, 661);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("在线用户", null, panel_1, null);
		panel_1.setLayout(null);
		
		JList list = new JList();
		list.setBackground(Color.CYAN);
		list.setBounds(0, 0, 232, 632);
		panel_1.add(list);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("在线聊天", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.YELLOW);
		lblNewLabel_1.setBounds(0, 0, 232, 594);
		panel_5.add(lblNewLabel_1);
		
		
	  }
	
	public void showUserList(final List<User> userlist){
		
		list.setModel(new AbstractListModel() {

			@Override
			public int getSize() {
				
				return userlist.size();
			}

			@Override
			public Object getElementAt(int index) {
				// TODO Auto-generated method stub
				return userlist.get(index);
			}
			
		});
	}
	
	public void showRoomList(List<RoomPojo> rooms) {
		// TODO Auto-generated method stub
		this.panel_4.removeAll();
		//JPanel[] jpanel = new JPanel[12];
     // 	JButton[] jbutton = new JButton[36];
          for(int i = 0;i< rooms.size();i++){//房间
	        
        	  final RoomPojo r1 = rooms.get(i);
	    	JPanel jpanel = new JPanel();
	    	jpanel.setBackground(new Color(0, 255, 255));
	    	//
	    	
	    	JButton leftjbutton1 = new JButton();
	    	if(r1.getLeftPlayer()!=null)
	    	     leftjbutton1.setIcon(new ImageIcon(r1.getLeftPlayer().getFileName()));
	    	else
	    		leftjbutton1.setIcon(new ImageIcon());
	    	leftjbutton1.setSize(45,45);
	    	leftjbutton1.setPreferredSize(new Dimension(45,45));
	    	jpanel.add(leftjbutton1);
	    	leftjbutton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	leftjbutton1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	         //房间左边座位按钮		
	        		int roomid = Integer.parseInt(((JComponent) e.getSource())
							.getParent().getName());//获得对应房间的名字
	        		System.out.println("roomid:" + roomid);
					//房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
					ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid,user,true);
					MyClient.getMyClient().sendMsg(msg);//发给服务器
	        	}

	        });
	    	
	    	JButton jbutton2= new JButton(new ImageIcon());
	    	jbutton2.setSize(150,150);
	    	jbutton2.setPreferredSize(new Dimension(150,150));
	        jpanel.add(jbutton2);
	        
	        JButton rightjbutton3= new JButton();
	        if(r1.getRightPlayer()!=null)
	        rightjbutton3.setIcon(new ImageIcon(r1.getRightPlayer().getFileName()));
	        else
	    		rightjbutton3.setIcon(new ImageIcon());
	    	rightjbutton3.setSize(45,45);
	    	jpanel.add(rightjbutton3);
	    	rightjbutton3.setPreferredSize(new Dimension(45,45));
	    	rightjbutton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    	rightjbutton3.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//房间右边座位按钮
	        		int roomid = Integer.parseInt(((JComponent) e.getSource())
							.getParent().getName());//获得对应房间的名字
	        		System.out.println("roomid:" + roomid);
					//房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
					ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid,user,true);
					MyClient.getMyClient().sendMsg(msg);//发给服务器
	        	}
	        });
	    	panel_4.add(jpanel);	
	    }
          RoomList.this.validate();//强制刷新主窗口
	}
    
	public void toRoom(int roomid,User user,boolean isleft){
		new Room(rooms.get(roomid),isleft).setVisible(true);
	}
	
	public void tohome() {
		// TODO Auto-generated method stub
		home.setVisible(true);
		this.dispose();
	}
	
	public void init() {
	    this.setTitle("五子棋");
	    this.setSize(1000, 700);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setVisible(true);
	  }
	
      public static void main(String[] args) {
			new RoomList(null,null).setVisible(true);
     }
}
