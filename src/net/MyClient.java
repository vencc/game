package net;

import java.io.IOException;
/**
 *客户端类 
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import msg.BaseMsg;

public class MyClient {
     private Socket client=null;
     private boolean connected;
	 public boolean isConnectrd() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	/**
	 * 连接8888端口上的服务器
	 * @return是否成功连接
	 */
	public boolean connect(){
		try {
			client=new Socket("localhost",8888);
			connected=true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ReceiveServerThread(client).start();
		return true;
	}
	/**
	 *关闭客户端连接 
	 * @return
	 */
	public boolean disConnect(){
		if(client==null){
			return true;
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connected=false;
		return true;
	}
	/**
	 * 不断接受报文类并执行其Biz方法的线程
	 * @author john
	 *
	 */
	class ReceiveServerThread extends Thread{
		private  Socket client;

		public ReceiveServerThread(Socket client) {
			super();
			this.client = client;
		}

		public void run() {
			
				try {
					while(true){
					 ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
					 BaseMsg msg = (BaseMsg)ois.readObject();
					 msg.setClient(client);
					 msg.doBiz();
					 ois.close();
				}
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		}
		
	}
	/**
	 * 供用户界面的监听器调用，向服务器发送报文类的方法
	 * @param msg
	 */
	
	public void sendMsg(BaseMsg msg){
		if(!this.isConnectrd()){
			return;
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(msg);
		    oos.flush();
		    oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
