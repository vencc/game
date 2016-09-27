package msg;

import net.MyClient;

/**
 *服务器传递开始游戏报文给客户端 
 * @author john
 *
 */
public class ServerStartGame extends BaseMsg{

	public void doBiz() {
		//MyClient.getMyClient().getRoom().gameStart();
	}

}
