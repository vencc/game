package msg;

import entity.User;
import net.MyServer;

/**
 * Created by huanghuanhuan on 10/7/16.
 */
public class ClientSavePictureMsg extends BaseMsg {
  User user;
  String fileName;
  int flag;
  public ClientSavePictureMsg(String fileName, User user,int flag){
    this.flag=flag;
    this.user=user;
    this.fileName=fileName;
  }
  public void doBiz(){
    MyServer.getMyServer().updateUserImag(fileName,user.getName());
    user=MyServer.getMyServer().findUser(user.getName());
    if(flag==1) {
      ServertoRoomListMsg msg = new ServertoRoomListMsg(user);
      MyServer.getMyServer().sendMsgToClient(msg, this.client);
    }else{
      ServerClickUpdateMsg msg=new ServerClickUpdateMsg();
      MyServer.getMyServer().sendMsgToClient(msg, this.client);
    }
  }
}
