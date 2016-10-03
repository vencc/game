package chess;

import entity.User;
import msg.ClientLoginMsg;
import msg.ClientLogoutMsg;
import net.MyClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 功能: 登录界面
 * 作者: 黄欢欢  时间: 2016-09-20
 */
public class Home extends JFrame {
  private User user = new User("游客");
  private Home home = this;
  private JButton userButton = new JButton();       // 用户已登录显示自己姓名的按钮
  private JButton netButton = new JButton("联网对战");    // 联网对战按钮
  private JButton robotButton = new JButton("人机对战");  // 人机对战按钮
  private JButton logoffButton = new JButton("退出");  // 退出按钮
  private JPanel contentPane = new JPanel() {
    protected void paintComponent(Graphics g) {
      Image image = new ImageIcon("resource/imag/home.png").getImage();
      g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

  };

  public Home() {
    init();
  }

  /**
   * 功能: 初始化界面
   * 作者: 黄欢欢   时间: 2016-09-21
   */
  private void init() {
    this.setTitle("五子棋");
    this.setSize(1000, 562);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setIconImage(new ImageIcon("resource/imag/logo.png").getImage());
    contentPane.setLayout(null);

    // 初始化组件
    netButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.5), this.getWidth() / 6, this.getHeight() / 14);
    netButton.setFocusPainted(false);
    userButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.5), this.getWidth() / 6, this.getHeight() / 14);
    robotButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.6), this.getWidth() / 6, this.getHeight() / 14);
    logoffButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.7), this.getWidth() / 6, this.getHeight() / 14);

    // 初始化事件监听
    addAction();

    // 添加组件
    contentPane.add(netButton);
    contentPane.add(robotButton);

    this.add(contentPane);
    this.setVisible(true);
  }

  /**
   * 功能: 给成员属性添加监听事件
   * 作者: 黄欢欢   时间: 2016-09-21
   */
  private void addAction() {
    logoffButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        home.dispose();
        new Home();
      }
    });
    netButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new NameDialog(home);
      }
    });

    robotButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        toRoom();
      }
    });

    userButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ClientLoginMsg msg = new ClientLoginMsg(user.getName());
        MyClient.getMyClient().sendMsg(msg);
      }


    });

    /**
     * 功能: 监听窗体关闭按钮
     * 作者:黄欢欢  时间: 2016-09-23
     */
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("退出程序");
        ClientLogoutMsg msg = new ClientLogoutMsg();
        MyClient.getMyClient().sendMsg(msg);
      }
    });


  }

  /**
   * 功能: 当窗口缩放拖动时重绘窗口
   * 作者: 黄欢欢   时间: 2016-09-21
   *
   * @param time   重绘时间
   * @param x      起点横坐标
   * @param y      起点纵坐标
   * @param width  窗体宽度
   * @param height 窗体高度
   */
  public void repaint(long time, int x, int y, int width, int height) {
    netButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.5), this.getWidth() / 6, this.getHeight() / 13);
    robotButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.6), this.getWidth() / 6, this.getHeight() / 13);
  }

  /**
   * 功能: 跳转至房间列表界面
   * 作者: 黄欢欢  时间: 2016-09-21
   */
  public void toRoomList(User user) {
    this.user = user;
    new RoomList(this, user);
    userButton.setText(user.getName());
    contentPane.remove(netButton);
    contentPane.add(userButton);
    contentPane.add(logoffButton);
    this.setVisible(false);
  }

  public void toRoom() {
    new Room(this);
    this.setVisible(false);
  }
}
