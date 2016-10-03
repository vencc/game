package msg;

import net.MyClient;

/**
 * 功能: 判断是否已登陆报文
 */
public class ServerDidLogMsg extends BaseMsg {
  public void doBiz(){
    MyClient.getMyClient().getNamedialog().showMessage();
  }
}
