package msg;

import net.MyServer;
import entity.RoomPojo;
/**
 * 客户端判定赢棋之后，向服务器发送的报文类
 * @author john
 *
 */
public class ClientGameOver extends BaseMsg{
    private int roomid;
	private boolean isleft;
	
	

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
	



	public ClientGameOver(int roomid, boolean isleft) {
		super();
		this.roomid = roomid;
		this.isleft = isleft;
	}



	public void doBiz() {
		RoomPojo roompojo=MyServer.getMyServer().getRooms().get(roomid);
		if(isleft){
			ServerWinMsg msg=new ServerWinMsg();
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
			ServerDefeatmsg msg2=new ServerDefeatmsg();
			MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getRightPlayer());
		}
		else{
			ServerWinMsg msg=new ServerWinMsg();
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
			ServerDefeatmsg msg2=new ServerDefeatmsg();
			MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getLeftPlayer());
		}
	}
}
