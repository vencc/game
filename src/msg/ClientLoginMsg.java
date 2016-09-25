package msg;

import entity.User;
import net.MyServer;
class UserDao{
	public User aa(){
		return new User("zzb");
	}
}

public class ClientLoginMsg extends BaseMsg{


	private String username;
	public ClientLoginMsg(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public void doBiz() {
		if(!username.isEmpty()){
			//1.生成response報文
			ServerLoginSucMsg msg=new ServerLoginSucMsg(null);
			//2.服务器发送报文给指定客户端
			MyServer.getMyServer().sendMsgToClient(msg, this.client);
			//3.将客户端的用户名与所分配到的线程进行绑定
			//有username查询dao得到User对象user
			UserDao dao=new UserDao();
			User user=dao.aa();
			MyServer.getMyServer().bindUsername(user, client);
			//4.向客户端发送在线用户列表

			
		}else{
			ServerLoginFailMsg msg=new ServerLoginFailMsg();
			MyServer.getMyServer().sendMsgToClient(msg, this.client);
		}
	}


}
