package chess;

import javax.swing.*;

/**
 * 功能: 房间界面
 */
public class Room extends JFrame {
  private RoomList roomList;
  public Room(RoomList roomList){
    this.roomList=roomList;
  }
  public void init() {
    this.setTitle("五子棋");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  public void toRoomList(){
    roomList.setVisible(true);
    //关闭本窗口  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
}
