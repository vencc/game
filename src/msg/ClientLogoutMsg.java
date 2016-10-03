package msg;

import entity.User;
import net.MyServer;

/**
 * 功能: 退出发送报文
 */
public class ClientLogoutMsg extends BaseMsg{

public void doBiz() {
    MyServer.getMyServer().deleteUserCilent(this.client);
    ServerUserListMsg msg2=new ServerUserListMsg(MyServer.getMyServer().getUserList());
    MyServer.getMyServer().sendMsgToAll(msg2);
  }
}
