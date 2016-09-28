package msg;

import net.MyClient;

public class ServerGroupChat extends BaseMsg{
    private String str;
    
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	

	public ServerGroupChat(String str) {
		super();
		this.str = str;
	}

	public void doBiz() {
		MyClient.getMyClient().getRoomlist().showChatMsg(str);
	}

}
