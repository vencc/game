package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import net.MyClient;
import entity.RoomPojo;
import entity.User;

public class RoomList extends JFrame{
	
	JPanel panel_4 = new JPanel();
	JList list = new JList();
	ArrayList<RoomPojo> rooms = new ArrayList();
	Home home = new Home();
	public RoomList(Home home,final User user) {
		MyClient.getMyClient().setRoomlist(this);
		this.home= home;
		setSize(new Dimension(1000, 700));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 232, 666);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u5728\u7EBF\u7528\u6237");
		label.setBounds(80, 19, 123, 15);
		panel_1.add(label);
		list.setBackground(Color.CYAN);
		list.setForeground(Color.RED);
		
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"list1","list2","list3","list4","list5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(0, 45, 232, 621);
		panel_1.add(list);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("resource/imag/13.jpg"));
		lblNewLabel_1.setBounds(0, 45, 232, 611);
		panel_1.add(lblNewLabel_1);
		
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
	    
	    
/*	    for(int i = 0;i<12;i++)
	    {
	    	RoomPojo r = new RoomPojo(0,null,null,RoomPojo.IDLE);
	    	rooms.add(r);
	    }
	    showRoomList(rooms);*/
		
	       
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(232, 0, 761, 45);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton button = new JButton("\u5FEB\u901F\u8FDB\u5165");
		button.addMouseListener(new MouseAdapter() {//快速进入按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				int k=0;
				 for(int i=0;i<rooms.size();i++){
					 if(rooms.get(i).getStatus()==1){
						 toRoom();
						 k=1;
						 return;
					 }else if(rooms.get(i).getStatus()==0){
						 toRoom();
						 k=1;
						 return;
					      }
					 }
				       if(k==0){
				    	   JOptionPane.showMessageDialog(null, "所有房间已满人，请等候！");
				       }
				 }
		});
		button.setBounds(245, 5, 127, 30);
		panel_3.add(button);
		
		JButton btnNewButton = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseAdapter() {//退出登录按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tohome();
			}
		});
		btnNewButton.setBounds(377, 5, 108, 30);
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("resource/imag/12.jpg"));
		lblNewLabel.setBounds(0, 0, 761, 45);
		panel_3.add(lblNewLabel);
		
		
	  }
	
	public void showUserList(final List<User> userlist){
		final String[] values = new String[userlist.size()] ;
		list.setModel(new AbstractListModel() {
			{
				for(int i=0;i<values.length;i++){
					values[i] = userlist.get(i).toString();
				}
			}

			@Override
			public int getSize() {
				
				return userlist.size();
			}

			@Override
			public Object getElementAt(int index) {
				// TODO Auto-generated method stub
				return userlist.get(index).toString();
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
	    	JButton jbutton1 = new JButton(new ImageIcon("resource\\imag\\9_meitu_2.jpg"));
	    	jbutton1.setSize(50,50);
	    	jbutton1.setPreferredSize(new Dimension(50,50));
	    	jpanel.add(jbutton1);
	    	jbutton1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	         //房间左边座位按钮		
	        	    if(r1.getLeftPlayer()==null){
	        	    	toRoom();
	        	    }else{
	        	    	JOptionPane.showMessageDialog(null, "此位置已有人");
	        	    }
	        	}

	        });
	    	
	    	JButton jbutton2= new JButton(new ImageIcon("resource\\imag\\1.jpg"));
	    	jbutton2.setSize(150,150);
	    	jbutton2.setPreferredSize(new Dimension(150,150));
	        jpanel.add(jbutton2);
	        
	        JButton jbutton3= new JButton(new ImageIcon("resource\\imag\\10_meitu_3.jpg"));
	    	jbutton3.setSize(50,50);
	    	jpanel.add(jbutton3);
	    	jbutton3.setPreferredSize(new Dimension(50,50));
	    	jbutton3.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//房间右边座位按钮
	        		if(r1.getLeftPlayer()==null){
	        	    	toRoom();
	        	    }else{
	        	    	JOptionPane.showMessageDialog(null, "此位置已有人");
	        	    }
	        	}
	        });
	    	panel_4.add(jpanel);	
	    }
          RoomList.this.validate();//强制刷新主窗口
	}

	private void toRoom() {
		// TODO Auto-generated method stub
	//报错注释---------------	new Room(null).setVisible(true);
	}

	private void tohome() {
		// TODO Auto-generated method stub
		home.setVisible(true);
		
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
