package msg;

import net.MyClient;
/**
 * 服务端向客户端发送悔棋失败的报文类
 * @author john
 *
 */
public class ServerBackFail extends BaseMsg{

	public void doBiz() {
		MyClient.getMyClient().getRoom().BackFail();
		System.out.println("悔棋失败");
	}

}
