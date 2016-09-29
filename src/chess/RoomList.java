package chess;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
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

import msg.ClientClickChatMsg;
import msg.ClientClickRoomMsg;
import msg.ClientClickUpdateMsg;
import msg.ClientClickWinNumMsg;
import msg.ClientLogoutMsg;
import msg.ClientOffMsg;
import net.MyClient;
import entity.RoomPojo;
import entity.UpdatePicture;
import entity.User;
import util.ScrollbarUI;
import util.TabbedPaneUI;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//房间列表界面，大厅界面

public class RoomList extends JFrame {

  JPanel panel_4 = new JPanel();
  ArrayList<RoomPojo> rooms = new ArrayList();
  Home home;
  User user = null;
  JList list = new JList();
  JTextArea textArea = new JTextArea();
  JTextArea textArea_1 = new JTextArea();
  JButton button_1 = new JButton();

  JButton btnNewButton_1 = new JButton("发送");

  public RoomList(Home home, final User user) {

    this.home = home;
    this.user = user;
    MyClient.getMyClient().setRoomlist(this);
    this.setTitle("五子棋");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);


    JPanel panel = new JPanel() {
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/ting.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    getContentPane().add(panel, BorderLayout.CENTER);
    panel.setLayout(null);

    JPanel sendPanel=new JPanel();
    sendPanel.setBounds(10,630,230,35);
    sendPanel.setOpaque(false);
    panel.add(sendPanel);

    JPanel panel_1 = new JPanel() {
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/chart.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    panel_1.setBounds(0,70,250,560);
    panel_1.setOpaque(false);
    panel_1.setLayout(null);


    JPanel panel_5 = new JPanel() {
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/chart.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    panel_5.setBounds(0,70,250,560);
    panel_5.setOpaque(false);
    panel_5.setVisible(false);
    panel_5.setLayout(null);

    JPanel jPanel=new JPanel();
    jPanel.setBounds(0,0,232,200);
    jPanel.setLayout(null);
    JButton label1=new JButton(){
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/bt1.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    label1.setBounds(50,38,140,45);
    panel.add(jPanel);
    jPanel.add(label1);
    jPanel.setOpaque(false);
    JButton label2=new JButton(){
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/bt1small.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    label2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel_1.setVisible(false);
        panel_5.setVisible(true);
        label2.setBounds(50,38,140,45);
        label1.setBounds(70,10,100,40);
        textField.setVisible(true);
        btnNewButton_1.setVisible(true);
      }
    });

    label1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel_5.setVisible(false);
        panel_1.setVisible(true);
        label1.setBounds(50,38,140,45);
        label2.setBounds(70,10,100,40);
        textField.setVisible(false);
        btnNewButton_1.setVisible(false);
      }
    });
    label2.setBounds(70,10,100,40);
    jPanel.add(label2);

    panel.add(panel_1);
    panel.add(panel_5);

    JPanel panel_3 = new JPanel();
    panel_3.setOpaque(false);
    panel_3.setBounds(200, 0, 761, 65);
    panel.add(panel_3);
    panel_3.setLayout(null);

    JButton button = new JButton("\u5FEB\u901F\u8FDB\u5165");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {//快速进入按钮
        int k = 0;
        for (int i = 0; i < rooms.size(); i++) {
          if (rooms.get(i).getStatus() == 1) {  //等待状态
            int roomid = Integer.parseInt(((JComponent) e.getSource())
                .getParent().getName());//获得对应房间的名字
            Boolean isleft;
            System.out.println("roomid:" + roomid);
            if (rooms.get(i).getLeftPlayer() != null) {
              isleft = false;
            } else {
              isleft = true;
            }
            //房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
            ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid, user, true);
            MyClient.getMyClient().sendMsg(msg);//发给服务器
            k = 1;
            return;
          } else if (rooms.get(i).getStatus() == 0) {//空闲状态

            int roomid = Integer.parseInt(((JComponent) e.getSource())
                .getParent().getName());//获得对应房间的名字
            System.out.println("roomid:" + roomid);
            //房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
            ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid, user, true);
            MyClient.getMyClient().sendMsg(msg);//发给服务器
            k = 1;
            return;
          }
        }
        if (k == 0) {
          JOptionPane.showMessageDialog(null, "所有房间已满人，请等候！");
        }
      }
    });
    button.setBounds(187, 5, 127, 30);
    panel_3.add(button);

    JButton btnNewButton = new JButton("\u9000\u51FA\u767B\u5F55");
    btnNewButton.addActionListener(new ActionListener() {//退出登录按钮
      @Override
      public void actionPerformed(ActionEvent e) {
    	  ClientLogoutMsg msg = new ClientLogoutMsg(user);
          MyClient.getMyClient().sendMsg(msg);//发给服务器
        tohome();
      }
    });
    btnNewButton.setBounds(625, 5, 108, 30);
    panel_3.add(btnNewButton);


    JButton button_3 = new JButton("\u53CD\u9988");
    button_3.addActionListener(new ActionListener() {//反馈按钮
      @Override
      public void actionPerformed(ActionEvent e) {
        new SendmailFrame();
      }
    });
    button_3.setBounds(488, 5, 93, 30);
    panel_3.add(button_3);

    JButton button_2 = new JButton("战绩排名");
    button_2.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		 //战绩排名按钮
    		 ClientClickWinNumMsg msg = new ClientClickWinNumMsg();
             MyClient.getMyClient().sendMsg(msg);

    	}
    });
    button_2.setBounds(352, 5, 93, 30);
    panel_3.add(button_2);

    list.setOpaque(false);
    list.setModel(new AbstractListModel() {
      String[] values = new String[]{"list1", "list2", "list3", "list4", "list5"};

      public int getSize() {
        return values.length;
      }

      public Object getElementAt(int index) {
        return values[index];
      }
    });

    list.setBounds(23, 20, 205, 520);
    panel_1.add(list);
    button_1.setIcon(new ImageIcon(user.getFileName()));
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { //用户头像按钮
        new UpdatePicture(user).setVisible(true);//进入个人信息界面
      }
    });
    button_1.setBounds(113, 0, 45, 45);
    panel_3.add(button_1);

    JPanel panel_2 = new JPanel();
    panel_2.setOpaque(false);
    panel_2.setBounds(330, 130, 590, 435);
    panel.add(panel_2);
    panel_2.setLayout(null);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(0, 4, 580, 425);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.getVerticalScrollBar().setUI(new ScrollbarUI());
    panel_2.add(scrollPane);
    scrollPane.setOpaque(false);
    scrollPane.setBorder(null);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setViewportView(panel_4);
    panel_4.setOpaque(false);
    panel_4.setLayout(new GridLayout(6, 2, 15, 15));


    for (int i = 0; i < 12; i++) {
      RoomPojo r = new RoomPojo(0, null, null, RoomPojo.IDLE);
      rooms.add(r);
    }
    showRoomList(rooms);





    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(0, 0, 250, 560);
    panel_5.add(scrollPane_1);
    
    textArea.setBackground(Color.YELLOW);
    textArea.setEditable(false);
    textArea.setBounds(23, 20, 205, 520);
    textArea.setOpaque(false);
    textArea.setLineWrap(true);
    scrollPane_1.add(textArea);
    
    

    btnNewButton_1.setVisible(false);
    btnNewButton_1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) { //在线聊天发送按钮
    	  String str = textArea_1.getText();
	        
			int index=0;
			for(int i=0;i<str.length();i++)
				if(str.charAt(i)!=' ')
					index++;
			if(index==0)
				JOptionPane.showMessageDialog(null,"消息不能为空，请重新输入！");
			else{
				//房间选择报文传输 聊天信息传输给其他用户的界面
	        ClientClickChatMsg msg = new ClientClickChatMsg(str, user);
	        MyClient.getMyClient().sendMsg(msg);//发给服务器
	      
			}
      }
    });
    sendPanel.setLayout(null);
    
    textArea_1.addKeyListener(new KeyAdapter() {//聊天回车键事件
    	@Override
    	public void keyPressed(KeyEvent e) {
    		if(e.getKeyCode()==KeyEvent.VK_ENTER){
    			String str = textArea_1.getText();
    	        
				int index=0;
				for(int i=0;i<str.length();i++)
					if(str.charAt(i)!=' ')
						index++;
				if(index==0)
					JOptionPane.showMessageDialog(null,"消息不能为空，请重新输入！");
				else{
					//房间选择报文传输 聊天信息传输给其他用户的界面
    	        ClientClickChatMsg msg = new ClientClickChatMsg(str, user);
    	        MyClient.getMyClient().sendMsg(msg);//发给服务器
    	       
    			}
    		}
    	}
    });
    
    
    textArea_1.setBounds(10, 5, 143, 24);
    textArea_1.setLineWrap(true);
    sendPanel.add(textArea_1);
    btnNewButton_1.setBounds(163, 5, 57, 23);
    sendPanel.add(btnNewButton_1);

  }

  //在线用户显示
  public void showUserList(final List<User> userlist) {
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("退出程序");
        ClientOffMsg msg = new ClientOffMsg();
        MyClient.getMyClient().sendMsg(msg);
      }
    });

    final String[] values = new String[userlist.size()];
    list.setModel(new AbstractListModel() {
      {
        for (int i = 0; i < values.length; i++) {
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
        return userlist.get(index).getName();
      }

    });
  }
  
  //更新头像显示
  public void showPictrue(User user){
	  button_1.setIcon(new ImageIcon(user.getFileName()));
  }
  
  //战绩排名显示
  public void logout(ArrayList<User> userlist){
	  new WinNumFrame(userlist).setVisible(true);
  }

  //聊天信息显示
  public void showChatMsg(String str) {
    String str1 = this.textArea.getText();
    this.textArea.setText(str1 + "\n" + str + "\n");
    textArea_1.setText("");
  }

  //房间列表显示
  public void showRoomList(List<RoomPojo> rooms) {
    // TODO Auto-generated method stub
    this.panel_4.removeAll();
    //JPanel[] jpanel = new JPanel[12];
    // 	JButton[] jbutton = new JButton[36];
    for (int i = 0; i < rooms.size(); i++) {//房间

      final RoomPojo r1 = rooms.get(i);
      JPanel jpanel = new JPanel();
      jpanel.setName(i + "");
      jpanel.setOpaque(false);
      JButton leftjbutton1 = new JButton();
      if (r1.getLeftPlayer() != null)
        leftjbutton1.setIcon(new ImageIcon(r1.getLeftPlayer().getFileName()));
      else
        leftjbutton1.setIcon(new ImageIcon("resource/imag/kong.png"));
      leftjbutton1.setSize(45, 45);
      leftjbutton1.setPreferredSize(new Dimension(45, 45));
      jpanel.add(leftjbutton1);
      leftjbutton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
      leftjbutton1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //房间左边座位按钮
          int roomid = Integer.parseInt(((JComponent) e.getSource()).getParent().getName());//获得对应房间的名字
          System.out.println("roomid:" + roomid);
          //房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
          ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid, user, true);
          MyClient.getMyClient().sendMsg(msg);//发给服务器
        }

      });

      Image image = new ImageIcon("resource/imag/table.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
      JLabel jbutton2 = new JLabel(new ImageIcon(image));
      jbutton2.setSize(80, 80);
      jbutton2.setPreferredSize(new Dimension(80, 80));
      jpanel.add(jbutton2);

      JButton rightjbutton3 = new JButton();
      if (r1.getRightPlayer() != null)
        rightjbutton3.setIcon(new ImageIcon(rooms.get(i).getRightPlayer().getFileName()));
      else
        rightjbutton3.setIcon(new ImageIcon("resource/imag/kong.png"));
      rightjbutton3.setSize(45, 45);
      jpanel.add(rightjbutton3);
      rightjbutton3.setPreferredSize(new Dimension(45, 45));
      rightjbutton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
      rightjbutton3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //房间右边座位按钮
          int roomid = Integer.parseInt(((JComponent) e.getSource())
              .getParent().getName());//获得对应房间的名字
          System.out.println("roomid:" + roomid);
          //房间选择报文传输 roomid、username、isleft  传输给其他用户的界面
          ClientClickRoomMsg msg = new ClientClickRoomMsg(roomid, user, false);
          MyClient.getMyClient().sendMsg(msg);//发给服务器
        }
      });
      panel_4.add(jpanel);
    }
    RoomList.this.validate();//强制刷新主窗口
  }

  public void toRoom(int roomid, boolean isleft) {

	  new Room(roomid,isleft,this);
    this.setVisible(false);
  }

  //返回大厅
  public void tohome() {
    // TODO Auto-generated method stub
    home.setVisible(true);
    this.dispose();
  }

  public static void main(String[] args) {
    new RoomList(null, null).setVisible(true);
  }
}
