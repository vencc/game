package msg;

import net.MyClient;
import entity.RoomPojo;
/**
 * 进入房间报文类
 * @author john
 *
 */
public class ServerEnterRoomMsg extends BaseMsg{
	private RoomPojo roompojo;
	private boolean isleft;

	public boolean isIsleft() {
		return isleft;
	}

	public void setIsleft(boolean isleft) {
		this.isleft = isleft;
	}

	public RoomPojo getRoompojo() {
		return roompojo;
	}

	public void setRoompojo(RoomPojo roompojo) {
		this.roompojo = roompojo;
	}

	public ServerEnterRoomMsg(RoomPojo roompojo, boolean isleft) {
		super();
		this.roompojo = roompojo;
		this.isleft = isleft;
	}

	public void doBiz() {
	   // MyClient.getMyClient().getRoomlist().toRoom(roompojo,isleft);
	}
	

}
