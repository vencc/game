package msg;

import entity.RoomPojo;
import net.MyClient;
import net.MyServer;

/**
 * 功能: 显示对手玩家
 * @author 黄欢欢  时间: 2016-10-01
 */
public class ServerRoomPlayerMsg extends BaseMsg{
  private RoomPojo rid;
  public ServerRoomPlayerMsg(RoomPojo rid){
    this.rid=rid;
  }
  public void doBiz(){
    if(MyClient.getMyClient().getRoom()==null) return;
    MyClient.getMyClient().getRoom().setAnotherPlayer(rid);
  }
}
