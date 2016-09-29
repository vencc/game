package msg;

import net.MyClient;
/**
 * 服务器向胜利的一方发送的报文类
 * @author john
 *
 */
public class ServerWinMsg extends BaseMsg{

	public void doBiz() {
        MyClient.getMyClient().getRoom().win();		
	}

}
