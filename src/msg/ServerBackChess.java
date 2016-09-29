package msg;

import net.MyClient;
import net.MyServer;
/**
 * 服务端向被悔棋的客户端发送的报文类
 * @author john
 *
 */
public class ServerBackChess extends BaseMsg{

	public void doBiz() {
          MyClient.getMyClient().getRoom().decide();  
	}

}
