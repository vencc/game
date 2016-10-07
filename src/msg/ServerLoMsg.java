package msg;

import entity.User;
import net.MyClient;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ServerLoMsg extends BaseMsg {
  User user;
  public ServerLoMsg(User user){
    this.user=user;
  }
  public void doBiz(){
    MyClient.getMyClient().getNamedialog().sendLogMsg(user);
  }
}
