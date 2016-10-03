package msg;

import entity.RoomPojo;
import entity.User;
import net.MyClient;
import net.MyServer;

import java.util.Iterator;
import java.util.List;

/**
 * 功能: 快速进入房间报文
 */
public class ClientQuickEnterMsg extends BaseMsg{
  private User user;
  public ClientQuickEnterMsg(User user){
    this.user=MyServer.getMyServer().findUser(user.getName());
  }
  public void doBiz(){
    List<RoomPojo> list=MyServer.getMyServer().getRooms();
    System.out.println(list);
    Iterator<RoomPojo> it=list.iterator();
    int one=-1;
    int no=-1;
    while(it.hasNext()){
      RoomPojo roomPojo=it.next();
      if(roomPojo.getStatus()==RoomPojo.WAIT){
        one=roomPojo.getRid();
        break;
      }else if(roomPojo.getStatus()==RoomPojo.IDLE)
        no=roomPojo.getRid();
    }
    System.out.println("一人房间: "+one+"无人房间: "+no);
    if(one!=-1){
      RoomPojo room=MyServer.getMyServer().getRooms().get(one);
      room.setStatus(RoomPojo.PLAYING);
      if(room.getLeftPlayer()==null) {
        room.setLeftPlayer(user);
        room.setRightPlayer(MyServer.getMyServer().findUser(room.getRightPlayer().getName()));
        ServerEnterRoomMsg msg = new ServerEnterRoomMsg(one, true,user);
        MyServer.getMyServer().sendMsgToClient(msg, this.client);
        ServerRoomListMsg msg1=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
        MyServer.getMyServer().sendMsgToAll(msg1);
        ServerRoomPlayerMsg msg2=new ServerRoomPlayerMsg(room);
        MyServer.getMyServer().sendMsgToAll(msg2);
      }else{
        room.setRightPlayer(user);
        room.setLeftPlayer(MyServer.getMyServer().findUser(room.getLeftPlayer().getName()));
        ServerEnterRoomMsg msg = new ServerEnterRoomMsg(one, false,user);
        MyServer.getMyServer().sendMsgToClient(msg, this.client);
        ServerRoomListMsg msg1=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
        MyServer.getMyServer().sendMsgToAll(msg1);
        ServerRoomPlayerMsg msg2=new ServerRoomPlayerMsg(room);
        MyServer.getMyServer().sendMsgToAll(msg2);
      }
    }else if(no!=-1){
      RoomPojo room=MyServer.getMyServer().getRooms().get(no);
      room.setStatus(RoomPojo.WAIT);
      room.setLeftPlayer(user);
      ServerEnterRoomMsg msg = new ServerEnterRoomMsg(no, true,user);
      MyServer.getMyServer().sendMsgToClient(msg, this.client);
      ServerRoomListMsg msg1=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
      MyServer.getMyServer().sendMsgToAll(msg1);
      ServerRoomPlayerMsg msg2=new ServerRoomPlayerMsg(room);
      MyServer.getMyServer().sendMsgToAll(msg2);
    }
  }
}
