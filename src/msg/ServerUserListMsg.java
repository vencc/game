package msg;

import java.util.List;


import entity.User;

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
<<<<<<< HEAD
		MyClient.getMyClient().getRoomlist().showUserList(userList);
=======
		//LoginFrame.getMyClient().getHallFrame().showUserList(userList);
>>>>>>> 84dff651532ad0cd83bf24e1cca02f2077115b3d
		
	}
}
