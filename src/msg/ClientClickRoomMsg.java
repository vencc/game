package msg;

import chess.Room;
import entity.RoomPojo;
import net.MyClient;
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
		this.user = MyServer.getMyServer().findUser(user.getName());
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
		this.user = MyServer.getMyServer().findUser(user.getName());
		this.isleft = isleft;
	}

	public void doBiz() {
		RoomPojo room=MyServer.getMyServer().getRooms().get(roomid);
    System.out.println("进入房间前======"+room);
		if (room.getStatus() == RoomPojo.PLAYING) {
			return;
		}
		if(room.getStatus()==RoomPojo.IDLE){
			room.setStatus(room.WAIT);
			if(isleft){
				room.setLeftPlayer(user);
			}else{
				room.setRightPlayer(user);
			}
			//添加进入房间代码
			//此处
			ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(roomid,isleft,user);
			System.out.println("=========1111"+room);
			MyServer.getMyServer().sendMsgToClient(msg1, this.client);
			//
			ServerRoomListMsg msg=new ServerRoomListMsg(MyServer.getMyServer().getRooms());
			MyServer.getMyServer().sendMsgToAll(msg);
			ServerRoomPlayerMsg msg2=new ServerRoomPlayerMsg(room);
			MyServer.getMyServer().sendMsgToAll(msg2);
		}
		if (room.getStatus() == RoomPojo.WAIT) {
			if (isleft) {
				if (room.getLeftPlayer() != null) {
					return;
				}				
				room.setStatus(RoomPojo.PLAYING);
				room.setLeftPlayer(user);
				room.setRightPlayer(MyServer.getMyServer().findUser(room.getRightPlayer().getName()));
				ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(roomid,isleft,user);
				MyServer.getMyServer().sendMsgToClient(msg1, this.client);
				ServerRoomListMsg msg = new ServerRoomListMsg(MyServer.getMyServer().getRooms());
				MyServer.getMyServer().sendMsgToAll(msg);
				ServerRoomPlayerMsg msg2=new ServerRoomPlayerMsg(room);
				MyServer.getMyServer().sendMsgToAll(msg2);
			} else {
				if (room.getRightPlayer() != null) {
					return;
				}	
				room.setStatus(RoomPojo.PLAYING);
				room.setRightPlayer(user);
				room.setLeftPlayer(MyServer.getMyServer().findUser(room.getLeftPlayer().getName()));
				ServerEnterRoomMsg msg1=new ServerEnterRoomMsg(roomid,isleft,user);
				MyServer.getMyServer().sendMsgToClient(msg1, this.client);
				ServerRoomListMsg msg = new ServerRoomListMsg(MyServer
						.getMyServer().getRooms());
				MyServer.getMyServer().sendMsgToAll(msg);
				ServerRoomPlayerMsg msg2=new ServerRoomPlayerMsg(room);
				MyServer.getMyServer().sendMsgToAll(msg2);
			}
		}

	}
	

}
