package msg;

import entity.RoomPojo;
import net.MyClient;

/**
 * Created by huanghuanhuan on 10/1/16.
 */
public class ServerBeReady extends BaseMsg {
  RoomPojo roomPojo;
  public ServerBeReady(RoomPojo roomPojo){
    this.roomPojo=roomPojo;
  }
  public void doBiz(){
    System.out.println("左边准备了吗"+roomPojo.isLeftReady()+"右边准备了吗: "+roomPojo.isRightReady());

    MyClient.getMyClient().getRoom().setReady(roomPojo);
  }
}
