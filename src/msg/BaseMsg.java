package msg;

import java.io.Serializable;
import java.net.Socket;

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
