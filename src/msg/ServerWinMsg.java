package msg;

import entity.User;
import net.MyClient;
/**
 * 服务器向胜利的一方发送的报文类
 * @author john
 *
 */
public class ServerWinMsg extends BaseMsg{
	User user,user1;
	public ServerWinMsg(User user,User user1){
		this.user1=user1;
		this.user=user;
	}

	public void doBiz() {
        MyClient.getMyClient().getRoom().win(user,user1);
	}

}
