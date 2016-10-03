package msg;

import entity.User;
import net.MyClient;
import entity.RoomPojo;
/**
 * 进入房间报文类
 * @author john
 *
 */
public class ServerEnterRoomMsg extends BaseMsg{
	private int roomid;
	private boolean isleft;
	private User user;


	public int getRoomid() {
		return roomid;
	}


	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}


	public boolean isIsleft() {
		return isleft;
	}


	public void setIsleft(boolean isleft) {
		this.isleft = isleft;
	}


	public ServerEnterRoomMsg(int roomid, boolean isleft, User user) {
		super();
		this.roomid = roomid;
		this.user=user;
		this.isleft = isleft;
	}


	public void doBiz() {
	    MyClient.getMyClient().getRoomlist().toRoom(roomid,isleft,user);
	}
	

}
