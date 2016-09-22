package chess;

import entity.User;

import javax.swing.*;

/**
 * 功能: 房间列表
 */
public class RoomList extends JFrame {
  private User user;  // 玩家信息
  private JButton backButton;
  public Home home;
  public RoomList(Home home, User user){
    this.user=user;
    this.home=home;
  }
  public void init() {
    this.setTitle("五子棋");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  private void toRoom(){
    new Room(this).init();
    this.setVisible(false);
  }
  private void toHome(){
    home.setVisible(true);
  }

}
