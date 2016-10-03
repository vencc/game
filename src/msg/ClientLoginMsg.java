package msg;


import entity.User;
import net.MyClient;
import net.MyServer;
/**
 * 用户登录报文类，由客户端发送至服务器端
 * @author john
 *时间：2016.09.21
 */

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
			User user= MyServer.getMyServer().findUser(username);
			if(!MyServer.getMyServer().loged(user)) {
				ServerDidLogMsg msg3=new ServerDidLogMsg();
				MyServer.getMyServer().sendMsgToClient(msg3, this.client);
				return;
			}
			ServerLoginSucMsg msg=new ServerLoginSucMsg(user);
			//2.服务器发送报文给指定客户
			System.out.println(msg);
			MyServer.getMyServer().sendMsgToClient(msg, this.client);
			//3.将客户端的用户名与所分配到的线程进行绑定
			//有username查询dao得到User对象user
			
			MyServer.getMyServer().bindUsername(user, client);
			//4.向所有客户端发送在线用户列表
			ServerUserListMsg msg2=new ServerUserListMsg(MyServer.getMyServer().getUserList());
			MyServer.getMyServer().sendMsgToAll(msg2);
			//5.服务器向登录的客户端发送房间列表报文
			ServerRoomListMsg msg3=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
			MyServer.getMyServer().sendMsgToClient(msg3, this.client);
			

		}else{
			ServerLoginFailMsg msg=new ServerLoginFailMsg();
			MyServer.getMyServer().sendMsgToClient(msg, this.client);
		}
	}


}
