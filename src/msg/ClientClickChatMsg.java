package msg;

import net.MyServer;
import entity.User;

public class ClientClickChatMsg extends BaseMsg{
    private String str;
    private User user;
    
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = MyServer.getMyServer().findUser(user.getName());
	}
	

	public ClientClickChatMsg(String str, User user) {
		super();
		this.str = str;
		this.user = user;
	}

	public void doBiz() {
		String s=user.getName()+" : "+str;
		ServerGroupChat msg=new ServerGroupChat(s);
		MyServer.getMyServer().sendMsgToAll(msg);	
	}

}
