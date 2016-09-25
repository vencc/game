package msg;

import java.util.List;


import entity.User;
import net.MyClient;

public class ServerUserListMsg extends BaseMsg {
	private List<User> userList;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public ServerUserListMsg(List<User> userList) {
		super();
		this.userList = userList;
	}

	@Override
	public void doBiz() {
		System.out.println(userList);
		MyClient.getMyClient().getRoomlist().showUserList(userList);
		//LoginFrame.getMyClient().getHallFrame().showUserList(userList);

	}
}
