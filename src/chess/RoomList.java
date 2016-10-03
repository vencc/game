package chess;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import entity.UpdatePicture;
import msg.*;
import net.MyClient;
import entity.RoomPojo;
import entity.User;
import net.MyServer;
import util.MyListCellRender;
import util.ScrollbarUI;

//房间列表界面，大厅界面

public class RoomList extends JFrame {

  RoomList roomList=this;
  JPanel floatPane=new JPanel(){
    protected void paintComponent(Graphics g) {
      Image image = new ImageIcon("resource/imag/floatPane.png").getImage();
      g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
  };
  JPanel panel_4 = new JPanel();
  ArrayList<RoomPojo> rooms = new ArrayList();
  Home home;
  User user = null;
  JList list = new JList();
  JTextArea textArea = new JTextArea();
  JButton button_1 = new JButton();
  JTextField textField = new JTextField();

  JPanel panel_2 = new JPanel();
  JButton btnNewButton_1 = new JButton("发送");

  JScrollPane scrollPane = new JScrollPane();
  JScrollPane chartScroll=new JScrollPane();
  JScrollPane userScroll=new JScrollPane();
  public RoomList(Home home, final User user) {
    list.setCellRenderer(new MyListCellRender());
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

    floatPane.setBounds(270,20,215,295);
    floatPane.setLayout(null);
    JLabel photo=new JLabel(new ImageIcon(user.getFileName()));
    photo.setBounds(70,20,70,70);
    floatPane.add(photo);
    JLabel nameLabel=new JLabel(user.getName());
    nameLabel.setBounds(100,98,45,45);
    floatPane.add(nameLabel);
    JLabel winLabel=new JLabel(user.getWinNum()+"");
    winLabel.setBounds(100,180,45,45);
    floatPane.add(winLabel);
    JLabel LoseLabel=new JLabel(user.getLoseNum()+"");
    LoseLabel.setBounds(100,230,45,45);
    floatPane.add(LoseLabel);
    panel.add(floatPane);
    floatPane.setVisible(false);
    JPanel sendPanel=new JPanel();
    sendPanel.setBounds(10,630,230,35);
    sendPanel.setOpaque(false);
    panel.add(sendPanel);

    final JPanel panel_1 = new JPanel() {
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/chart.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    panel_1.setBounds(0,70,250,560);
    panel_1.setOpaque(false);
    panel_1.setLayout(null);


    final JPanel panel_5 = new JPanel() {
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
    final JButton label1=new JButton(){
      protected void paintComponent(Graphics g) {
        Image image = new ImageIcon("resource/imag/bt1.png").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      }
    };
    label1.setBounds(50,38,140,45);
    panel.add(jPanel);
    jPanel.add(label1);
    jPanel.setOpaque(false);
    final JButton label2=new JButton(){
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
    panel_3.setBounds(200, 0, 761, 80);
    panel.add(panel_3);
    panel_3.setLayout(null);

    JButton button = new JButton("\u5FEB\u901F\u8FDB\u5165");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {//快速进入按钮
        ClientQuickEnterMsg msg=new ClientQuickEnterMsg(user);
        MyClient.getMyClient().sendMsg(msg);
      }
    });
    button.setBounds(187, 13, 127, 30);
    panel_3.add(button);

    JButton btnNewButton = new JButton("\u9000\u51FA\u767B\u5F55");
    btnNewButton.addActionListener(new ActionListener() {//退出登录按钮
      @Override
      public void actionPerformed(ActionEvent e) {
    	  ClientLogoutMsg msg = new ClientLogoutMsg();
          MyClient.getMyClient().sendMsg(msg);//发给服务器
        tohome();
      }
    });
    btnNewButton.setBounds(625, 13, 108, 30);
    panel_3.add(btnNewButton);


    JButton button_3 = new JButton("\u53CD\u9988");
    button_3.addActionListener(new ActionListener() {//反馈按钮
      @Override
      public void actionPerformed(ActionEvent e) {
        new SendmailFrame();
      }
    });
    button_3.setBounds(488, 13, 93, 30);
    panel_3.add(button_3);

    JButton button_2 = new JButton("战绩排名");
    button_2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new WinNumFrame(MyServer.getMyServer().getUserList()).setVisible(true);
      }
    });
    button_2.setBounds(352, 13, 93, 30);
    panel_3.add(button_2);

    list.setOpaque(false);
    userScroll.setBounds(23, 20, 205, 520);
    userScroll.setOpaque(false);
    userScroll.setBorder(null);
    userScroll.getVerticalScrollBar().setUI(new ScrollbarUI());
    userScroll.getViewport().setOpaque(false);
    userScroll.setViewportView(list);
    panel_1.add(userScroll);
    button_1.setIcon(new ImageIcon(user.getFileName()));
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { //用户头像按钮
        new UpdatePicture(user,0);//进入个人信息界面
        ClientClickUpdateMsg msg = new ClientClickUpdateMsg(user);
        MyClient.getMyClient().sendMsg(msg);//发给服务器
      }
    });
    button_1.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        floatPane.setVisible(true);
        Point point = MouseInfo.getPointerInfo().getLocation();
        floatPane.setBounds((int)point.getX(),(int)point.getY(),215,295);
      }
    });
    button_1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        floatPane.setVisible(false);
      }
    });
    button_1.setBounds(105, 5, 70, 70);
    panel_3.add(button_1);

    panel_2.setOpaque(false);
    panel_2.setBounds(getWidth()/3, getHeight()/5-10, getWidth()/5*3, getHeight()/5*3+15);
    panel.add(panel_2);
    panel_2.setLayout(null);

    scrollPane.setBounds(0, 4, getWidth()/7*4, getHeight()*6/10);
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



    textArea.setEditable(false);
    textArea.setBounds(23, 20, 205, 520);
    textArea.setOpaque(false);
    textArea.setLineWrap(true);
    chartScroll.setViewportView(textArea);
    chartScroll.setOpaque(false);
    chartScroll.setBorder(null);
    chartScroll.getViewport().setOpaque(false);
    chartScroll.setBounds(23, 20, 205, 520);
    chartScroll.getVerticalScrollBar().setUI(new ScrollbarUI());
    panel_5.add(chartScroll);
    textField.setBounds(0, 583, 149, 25);
    sendPanel.add(textField);
    textField.setColumns(12);
    textField.setVisible(false);

    textField.addKeyListener(new KeyAdapter() {//聊天回车键事件
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
          String str = textField.getText().trim();
          if(str.length()!=0){
            //房间选择报文传输 聊天信息传输给其他用户的界面
            ClientClickChatMsg msg = new ClientClickChatMsg(str, user);
            MyClient.getMyClient().sendMsg(msg);//发给服务器

          }
        }
      }
    });


    btnNewButton_1.setVisible(false);
    btnNewButton_1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) { //在线聊天发送按钮
        String str = textField.getText().trim();
        //房间选择报文传输 聊天信息传输给其他用户的界面

        if(str.length()!=0) {
          //房间选择报文传输 聊天信息传输给其他用户的界面
          ClientClickChatMsg msg = new ClientClickChatMsg(str, user);
          MyClient.getMyClient().sendMsg(msg);//发给服务器

        }

      }
    });
    btnNewButton_1.setBounds(148, 583, 79, 25);
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
  /**
   * 功能: 当窗口缩放拖动时重绘窗口
   * 作者: 黄欢欢   时间: 2016-09-29
   * @param time    重绘时间
   * @param x       起点横坐标
   * @param y       起点纵坐标
   * @param width   窗体宽度
   * @param height  窗体高度
   */
  public void repaint(long time, int x, int y, int width, int height){
    panel_2.setBounds(getWidth()/3, getHeight()/5-10, getWidth()/5*3, getHeight()/5*3+15);
    scrollPane.setBounds(0, 4, getWidth()/7*4, getHeight()*6/10);
  }
  //更新头像显示
  public void showPictrue(User user){
	  button_1.setIcon(new ImageIcon(user.getFileName()));
  }


  //聊天信息显示
  public void showChatMsg(String str) {
    String str1 = this.textArea.getText();
    this.textArea.setText(str1 + "\n" + str + "\n");
    textField.setText("");
    int height=10;
    Point p = new Point();
    p.setLocation(0,this.textArea.getLineCount()*height);
    this.chartScroll.getViewport().setViewPosition(p);
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
      if (r1.getLeftPlayer() != null) {
        ImageIcon icon=new ImageIcon(rooms.get(i).getLeftPlayer().getFileName());
        leftjbutton1.setIcon(new ImageIcon(icon.getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH)));
      }
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
      if (r1.getRightPlayer() != null) {
        ImageIcon icon=new ImageIcon(rooms.get(i).getRightPlayer().getFileName());
        rightjbutton3.setIcon(new ImageIcon(icon.getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH)));
      }
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

  public void toRoom(int roomid, boolean isleft,User user) {

	  new Room(roomid,isleft,this,user);
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
