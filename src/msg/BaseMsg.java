package msg;

import java.io.Serializable;
import java.net.Socket;
/**
 * 报文类基类
 * @author john
 * 时间：2016.09.21
 */
public abstract class BaseMsg implements Serializable{
	protected Socket client;

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}
	public abstract void doBiz();

}
