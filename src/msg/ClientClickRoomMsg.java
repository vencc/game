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
		if(room.getStatus()==RoomPojo.IDLE){
			room.setStatus(room.WAIT);
			if(isleft){
				room.setLeftPlayer(user);
			}else{
				room.setRightPlayer(user);
			}
			//添加进入房间代码
			//此处
			ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(roomid,isleft);
			System.out.println("=========1111"+room);
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
					return;
				}				
				room.setStatus(RoomPojo.PLAYING);
				room.setLeftPlayer(user);
				ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(roomid,isleft);
				MyServer.getMyServer().sendMsgToClient(msg1, this.client);
				ServerRoomListMsg msg = new ServerRoomListMsg(MyServer.getMyServer().getRooms());
				MyServer.getMyServer().sendMsgToAll(msg);
				return;
			} else {
				
				if (room.getRightPlayer() != null) {
					return;
				}	
				room.setStatus(RoomPojo.PLAYING);
				room.setRightPlayer(user);
				ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(roomid,isleft);
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
