package msg;

import entity.User;
import net.MyClient;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ServertoRoomListMsg extends BaseMsg {
  User user;
  public ServertoRoomListMsg(User user){
    this.user=user;
  }
  public void doBiz(){
    MyClient.getMyClient().getUpdatePicture().login(user);
  }
}
