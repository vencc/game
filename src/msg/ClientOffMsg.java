package msg;

import net.MyServer;

/**
 * 功能: 退出客户端发送退出报文
 */
public class ClientOffMsg extends BaseMsg{
  public void doBiz(){
    MyServer.getMyServer().deleteClientSocket(client);
    ServerUserListMsg msg2=new ServerUserListMsg(MyServer.getMyServer().getUserList());
    MyServer.getMyServer().sendMsgToAll(msg2);

  }
}
