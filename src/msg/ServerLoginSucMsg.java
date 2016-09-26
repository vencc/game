package msg;

import net.MyClient;
import entity.User;
/**
 * 登录成功报文类，由服务器发往客户端
 * @author john
 * 时间：2016.09.22
 */
public class ServerLoginSucMsg extends BaseMsg{
    //跟新使用数据库的User对象
	private User user;
	public ServerLoginSucMsg(User user) {
		super();
		this.user = user;
	}
	public void doBiz() {
		System.out.println(user);
		MyClient.getMyClient().getNamedialog().loginSuc(user);
	}

}
