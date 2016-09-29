package msg;

import chess.Room;
import entity.RoomPojo;
import entity.User;
import net.MyServer;

/**
 * 功能: 退出房间报文类
 * @author 黄欢欢  时间: 2016-09-29
 */
public class ClientOutRoomMsg extends BaseMsg {
  private Room room;
  private int roomid;
  private boolean isleft;

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

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

  public ClientOutRoomMsg(int roomid, boolean isleft ) {
    super();
    this.room=room;
    this.roomid = roomid;
    this.isleft = isleft;
  }
  public void doBiz(){
    RoomPojo room= MyServer.getMyServer().getRooms().get(roomid);
    if(room.getStatus()==RoomPojo.PLAYING){
      room.setStatus(RoomPojo.WAIT);
    }else{
      room.setStatus(RoomPojo.IDLE);
    }
    if(isleft){
      room.setLeftPlayer(null);
    }else{
      room.setRightPlayer(null);
    }
    ServerRoomListMsg msg=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
    MyServer.getMyServer().sendMsgToAll(msg);
  }
}
