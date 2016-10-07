package msg;

import net.MyClient;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ServerCheckLogMsg extends BaseMsg {
  String name;
  public ServerCheckLogMsg(String name){
    this.name=name;
  }
  public void doBiz(){
    MyClient.getMyClient().getNamedialog().loginCheck(name);
  }
}
