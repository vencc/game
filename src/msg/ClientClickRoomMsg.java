package msg;

import chess.Room;
import entity.RoomPojo;
import net.MyServer;
import entity.User;
/**
 * 向服务器发送点击房间报文类
 * @author john
 *
 */
public class ClientClickRoomMsg extends BaseMsg{
	private int roomid;
	private User user;
	private boolean isleft;
	
	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isIsleft() {
		return isleft;
	}

	public void setIsleft(boolean isleft) {
		this.isleft = isleft;
	}
   
	public ClientClickRoomMsg(int roomid, User user, boolean isleft) {
		super();
		this.roomid = roomid;
		this.user = user;
		this.isleft = isleft;
	}

	public void doBiz() {
		RoomPojo room=MyServer.getMyServer().getRooms().get(roomid);
		System.out.println("报文类里的ID: "+room.getRid());
		System.out.println("STATUS : "+room.getStatus());
		if(room.getStatus()==RoomPojo.IDLE){
			room.setStatus(room.WAIT);
			if(isleft){
				room.setLeftPlayer(user);
			}else{
				room.setRightPlayer(user);
			}
			//添加进入房间代码
			//此处
			ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(room,isleft);
			System.out.println("发送房间报文"+msg1);
			MyServer.getMyServer().sendMsgToClient(msg1, this.client);
			//
			ServerRoomListMsg msg=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
			MyServer.getMyServer().sendMsgToAll(msg);
			System.out.println();
			/*System.out.println("==========================");
			System.out.println("测试"+room.getRid());
			System.out.println("测试"+room.getStatus());
			System.out.println("测试"+room.getLeftPlayer());
			System.out.println("测试"+room.getRightPlayer());
			System.out.println("==========================");*/
			return;
		}
		if (room.getStatus() == RoomPojo.WAIT) {
			if (isleft) {
				if (room.getLeftPlayer() != null) {
					if (room.getLeftPlayer().equals(user)) {
						room.setLeftPlayer(null);
					    room.setStatus(RoomPojo.IDLE);
						ServerRoomListMsg msg = new ServerRoomListMsg(MyServer.getMyServer().getRooms());
						MyServer.getMyServer().sendMsgToAll(msg);
						return;
					}
					return;
				}
			/*	System.out.println("测试"+room.getRid());
				System.out.println("测试"+room.getStatus());
				System.out.println("测试"+room.getLeftPlayer());
				System.out.println("测试"+room.getRightPlayer());*/
				if(room.getRightPlayer().equals(user)){
					return;
				}
				
				room.setStatus(RoomPojo.PLAYING);
				room.setLeftPlayer(user);
				ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(room,isleft);
				MyServer.getMyServer().sendMsgToClient(msg1, this.client);
				ServerRoomListMsg msg = new ServerRoomListMsg(MyServer.getMyServer().getRooms());
				MyServer.getMyServer().sendMsgToAll(msg);
				return;
			} else {
				if (room.getRightPlayer() != null) {
					if (room.getRightPlayer().equals(user)) {
						room.setRightPlayer(null);
						room.setStatus(RoomPojo.IDLE);
						ServerRoomListMsg msg = new ServerRoomListMsg(
								MyServer.getMyServer().getRooms());
						MyServer.getMyServer().sendMsgToAll(msg);
						return;
					}
					return;
				}
				if(room.getLeftPlayer().equals(user)){
					return;
				}
				room.setStatus(RoomPojo.PLAYING);
				room.setRightPlayer(user);
				ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(room,isleft);
				MyServer.getMyServer().sendMsgToClient(msg1, this.client);
				ServerRoomListMsg msg = new ServerRoomListMsg(MyServer
						.getMyServer().getRooms());
				MyServer.getMyServer().sendMsgToAll(msg);
				return;
			}
		}
		if (room.getStatus() == RoomPojo.PLAYING) {
			return;
		}
	}
	

}
