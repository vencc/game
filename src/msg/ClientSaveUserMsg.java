package msg;

import entity.User;
import net.MyServer;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ClientSaveUserMsg extends BaseMsg {
  String name;
  public ClientSaveUserMsg(String name){
    this.name=name;
  }
  public void doBiz(){
    User user=new User(name);
    MyServer.getMyServer().insertUser(user);
    ServerUpdatePictureMsg msg=new ServerUpdatePictureMsg(user);
    MyServer.getMyServer().sendMsgToClient(msg, this.client);
  }
}
