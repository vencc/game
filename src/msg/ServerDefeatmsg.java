package msg;

import entity.User;
import net.MyClient;
/**
 * 服务端向失败的一方发送的报文类
 * @author john
 *
 */
public class ServerDefeatmsg extends BaseMsg{
	User user,user1;
	public ServerDefeatmsg(User user,User user1){
		this.user=user;
		this.user1=user1;
	}

	public void doBiz() {
		MyClient.getMyClient().getRoom().deafeat(user,user1);
	}
	

}
