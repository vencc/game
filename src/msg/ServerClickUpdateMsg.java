package msg;

import entity.User;
import net.MyClient;

public class ServerClickUpdateMsg extends BaseMsg{
	String fileName;
	public ServerClickUpdateMsg(String fileName){
		this.fileName=fileName;
	}

	@Override
	public void doBiz() {
		MyClient.getMyClient().getUpdatePicture().setDispose(MyClient.getMyClient().getRoomlist(),fileName);
	}

}
