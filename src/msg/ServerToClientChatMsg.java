package msg;

import net.MyClient;

public class ServerToClientChatMsg extends BaseMsg{
    private String str;
    
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	

	public ServerToClientChatMsg(String str) {
		super();
		this.str = str;
	}

	@Override
	public void doBiz() {
		//MyClient.getMyClient().getRoom().chat(str);
		
	}

}
