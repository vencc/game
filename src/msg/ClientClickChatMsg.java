package msg;

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
		this.user = user;
	}
	

	public ClientClickChatMsg(String str, User user) {
		super();
		this.str = str;
		this.user = user;
	}

	public void doBiz() {
		
	}

}
