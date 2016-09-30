package msg;

import java.util.ArrayList;

import net.MyClient;
import entity.User;

public class ServerWinNumMsg extends BaseMsg{

  ArrayList<User> userlist = null;

  public ServerWinNumMsg(ArrayList<User> userlist) {
    // TODO Auto-generated constructor stub
    super();
    this.userlist=userlist;
  }

  @Override
  public void doBiz() {
    // TODO Auto-generated method stub
    System.out.println(userlist);
    MyClient.getMyClient().getRoomlist().logout(userlist);
  }

  public ArrayList<User> getUserlist() {
    return userlist;
  }

  public void setUserlist(ArrayList<User> userlist) {
    this.userlist = userlist;
  }


}
