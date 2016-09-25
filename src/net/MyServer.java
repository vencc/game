package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.management.MBeanServer;

import entity.User;
import net.MyServer;
import net.MyServer.ClientChatThread;
import net.MyServer.WaitForClientThread;
import msg.BaseMsg;

/**
 * 服务器类
 * @author zzb  
 *
 */
public class MyServer {
	private static MyServer myserver;
	public static  MyServer getMyServer(){
		if(myserver==null){
			return new MyServer();
		}
		return myserver;
	}
	public static List<ClientChatThread> pool=new ArrayList();//socket池
	ServerSocket server=null;
	private static boolean started=false;
	public static void setStarted(boolean started) {
		MyServer.started = started;
	}
	public static boolean isStarted() {
		return started;
	}
	public List<User> getUserList(){
		List<User> list = new ArrayList<User>();
		for(ClientChatThread ct : pool){
			list.add(ct.getUser());
		}
		return list;
	}
	/**
	 * 启动服务器，启动线程监听客户端连接
	 * @return 返回服务器是否启动
	 * 
	 */
	public boolean startListen(){
		try {
			server=new ServerSocket(8888);
			started=true;
		    System.out.println("服务器启动成功");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		new WaitForClientThread().start();
		return true;
	}
	/**
	 * 关闭服务器套接字
	 * @return是否成功关闭
	 */
	public boolean stopListen(){
		if(server==null){
			return true;
		}
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setStarted(false);
		return true;
	}
	/**
	 * 线程类处理多客户端连接
	 * @author john
	 *
	 */
	class WaitForClientThread extends Thread{
		public void run() {
			try {
				while(true){
				     Socket client =server.accept();
				     System.out.println(client+"成功连接服务器");
				     ClientChatThread cct=new ClientChatThread(client);
				     pool.add(cct);
				     cct.start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 处理所连接的客户端的报文类数据收发
	 * @author john
	 *
	 */
	class ClientChatThread extends Thread {
		private User user;
		private Socket client;
        

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Socket getClient() {
			return client;
		}

		public void setClient(Socket client) {
			this.client = client;
		}

		public ClientChatThread(Socket client) {
			super();
			this.client = client;
		}
		
		public void sendMsg(BaseMsg msg,Socket client) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						client.getOutputStream());
				oos.writeObject(msg);
				oos.flush();
				oos.close();
				
			} catch (IOException e) {
				System.out.println("给"+client+"发送数据失败");
			}
		}
		public void run() {
			 try {
				 while(true){
				 ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				 System.out.println("收到数据");
				 BaseMsg msg = (BaseMsg)ois.readObject();
				 msg.setClient(client);
				 msg.doBiz();
				 ois.close();
				 }
			} catch (Exception e) {
					MyServer.pool.remove(this);			

			}
		}
		}
	/**
	 * 执行从客户端中收的的报文的Biz(),在Biz中调用此方法，给相应客户端发送相应的报文
	 * @param msg 应该发送的报文
	 * @param client  相应的客户端套接字
	 */
	public void sendMsgToClient(BaseMsg msg,Socket client){
		for(ClientChatThread c:pool){
			if(c.getClient()==client){
				c.sendMsg(msg, client);
				return;
			}
		}
	}
	public void bindUsername(User user,Socket client){
		for(ClientChatThread c:pool){
			if(c.getClient()==client){
				c.setUser(user);
				return;
			}
		}
	}
	public static void main(String[] args) {
		 MyServer.getMyServer().startListen();
	}
}
