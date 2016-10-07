package msg;

import entity.User;
import net.MyClient;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ServerUpdatePictureMsg extends BaseMsg {
  User user;
  public ServerUpdatePictureMsg(User user){
    this.user=user;
  }
  public void doBiz(){
    MyClient.getMyClient().getNamedialog().toUpdatePicture(user);
  }
}
