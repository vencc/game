package chess;

import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能: 登录界面
 * 作者: 黄欢欢  时间: 2016-09-20
 */
public class Home extends JFrame{
  private User user=new User("游客");
  private Home home=this;
  private JButton netButton=new JButton("联网对战");    // 联网对战按钮
  private JButton robotButton=new JButton("人机对战");  // 人机对战按钮
  private JPanel contentPane=new JPanel(){
    protected void paintComponent(Graphics g){
      Image image=new ImageIcon("resource/imag/home.png").getImage();
      g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }
  };

  public Home(){
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
    netButton.setBounds((int)(this.getWidth()*0.2),(int)(this.getHeight()*0.5),this.getWidth()/6,this.getHeight()/14);
    netButton.setFocusPainted(false);
    robotButton.setBounds((int)(this.getWidth()*0.2),(int)(this.getHeight()*0.6),this.getWidth()/6,this.getHeight()/14);

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
  private void addAction(){
    netButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new NameDialog(home,user);
      }
    });

    robotButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        toRoomList();
      }
    });
  }

  /**
   * 功能: 当窗口缩放拖动时重绘窗口
   * 作者: 黄欢欢   时间: 2016-09-21
   * @param time    重绘时间
   * @param x       起点横坐标
   * @param y       起点纵坐标
   * @param width   窗体宽度
   * @param height  窗体高度
   */
  public void repaint(long time, int x, int y, int width, int height){
    netButton.setBounds((int)(this.getWidth()*0.2),(int)(this.getHeight()*0.5),this.getWidth()/6,this.getHeight()/13);
    robotButton.setBounds((int)(this.getWidth()*0.2),(int)(this.getHeight()*0.6),this.getWidth()/6,this.getHeight()/13);
  }

  /**
   * 功能: 跳转至房间列表界面
   * 作者: 黄欢欢  时间: 2016-09-21
   */
  public void toRoomList(){
    new RoomList(this,user).init();
    this.setVisible(false);
  }
}
