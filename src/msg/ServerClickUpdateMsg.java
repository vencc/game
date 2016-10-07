package msg;

import entity.User;
import net.MyClient;

public class ServerClickUpdateMsg extends BaseMsg{

	@Override
	public void doBiz() {
		MyClient.getMyClient().getUpdatePicture().setDispose();
		
	}

}
