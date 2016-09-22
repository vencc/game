package chess;

import javax.swing.*;

/**
 * 功能: 个人信息
 */
public class Personal extends JFrame {
  private RoomList roomList;
  public void init() {
    this.setTitle("五子棋");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  private void toRoomList(){
    roomList.setVisible(true);
    //关闭本窗口  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
}
