package msg;

import entity.User;
import net.MyServer;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ClientCheckLogMsg extends BaseMsg {
  String name;
  public ClientCheckLogMsg(String name){
    this.name=name;
  }
  public void doBiz(){
    User user=MyServer.getMyServer().findUser(name);
    if(user==null){
      ServerCheckLogMsg msg=new ServerCheckLogMsg(name);
      MyServer.getMyServer().sendMsgToClient(msg, this.client);
    }else{
      ServerLoMsg msg=new ServerLoMsg(user);
      MyServer.getMyServer().sendMsgToClient(msg, this.client);
    }
  }
}
