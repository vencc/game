package msg;

import com.etc.entity.Room;
import com.etc.gui.ServerFrame;
import com.etc.msg.ServerRoomListMsg;

import net.MyServer;
import entity.RoomPojo;
import entity.User;

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
			//
			ServerRoomListMsg msg=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
			MyServer.getMyServer().sendMsgToAll(msg);
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
				}
				if(room.getRightPlayer().equals(user)){
					return;
				}
				
				room.setStatus(RoomPojo.PLAYING);
				room.setLeftPlayer(user);
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
				}
				if(room.getLeftPlayer().equals(user)){
					return;
				}
				room.setStatus(RoomPojo.PLAYING);
				room.setRightPlayer(user);
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
