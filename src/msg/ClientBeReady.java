package msg;

import net.MyServer;
import entity.RoomPojo;

/**
 * 玩家 准备 报文类
 *
 * @author john
 */
public class ClientBeReady extends BaseMsg {
  private int roomid;
  private boolean isleft;


  public int getRoomid() {
    return roomid;
  }


  public void setRoomid(int roomid) {
    this.roomid = roomid;
  }


  public boolean isIsleft() {
    return isleft;
  }


  public void setIsleft(boolean isleft) {
    this.isleft = isleft;
  }


  public ClientBeReady(int roomid, boolean isleft) {
    super();
    this.roomid = roomid;
    this.isleft = isleft;
  }


  public void doBiz() {
    /*System.out.println(roompojo.equals(this.getRoompojo()));
		System.out.println("3333333");
		System.out.println(this);
		System.out.println(this.roompojo);
		System.out.println("3333333");*/
    RoomPojo roompojo = MyServer.getMyServer().getRooms().get(roomid);
    System.out.println("123" + roompojo);
    if (roompojo.isLeftReady() && roompojo.isRightReady()) {
      return;
    }
    if (isleft) {
      if (roompojo.isLeftReady()) {
        System.out.println("左边取消准备了");
        roompojo.setLeftReady(false);
        MyServer.getMyServer().getRooms().set(roomid,roompojo);
        if (roompojo.getRightPlayer() != null) {
          ServerBeReady msg4 = new ServerBeReady(roompojo);
          MyServer.getMyServer().sendMsgToClient(msg4, roompojo.getRightPlayer());
        }
        return;
      } else
        roompojo.setLeftReady(true);
      MyServer.getMyServer().getRooms().set(roomid,roompojo);
      System.out.println("左边准备了");
      if (roompojo.getRightPlayer() != null) {
        ServerBeReady msg4 = new ServerBeReady(roompojo);
        MyServer.getMyServer().sendMsgToClient(msg4, roompojo.getRightPlayer());
      }
      if (roompojo.isRightReady()) {
        System.out.println("左边右边都准备了");
        ServerStartGame msg = new ServerStartGame();
        MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
        ServerStartGame msg2 = new ServerStartGame();
        MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getRightPlayer());
        return;
      }
    } else {
      if (roompojo.isRightReady()) {
        System.out.println("右边取消准备了");
        roompojo.setRightReady(false);
        MyServer.getMyServer().getRooms().set(roomid,roompojo);
        if (null != roompojo.getLeftPlayer()) {
          ServerBeReady msg4 = new ServerBeReady(roompojo);
          MyServer.getMyServer().sendMsgToClient(msg4, roompojo.getLeftPlayer());
        }
        return;
      } else
        roompojo.setRightReady(true);
      MyServer.getMyServer().getRooms().set(roomid,roompojo);
      System.out.println(roompojo.isLeftReady() + "右边准备了");
      if (null != roompojo.getLeftPlayer()) {
        ServerBeReady msg4 = new ServerBeReady(roompojo);
        MyServer.getMyServer().sendMsgToClient(msg4, roompojo.getLeftPlayer());
      }
      if (roompojo.isLeftReady()) {
        // 报文还没写完整
        System.out.println("左边右边都准备了");
        roompojo.setRightReady(true);
        System.out.println("----------------");
        ServerStartGame msg = new ServerStartGame();
        MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());

        ServerStartGame msg2 = new ServerStartGame();
        MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getRightPlayer());
        return;
      }

    }

  }

}
