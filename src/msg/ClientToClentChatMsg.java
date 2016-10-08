package msg;

import entity.RoomPojo;
import net.MyServer;

public class ClientToClentChatMsg extends BaseMsg{
	private int roomid;
	private boolean isleft;
	private String str;
	public int getRoomid() {
		return roomid;
	}
	public void setRooid(int rooid) {
		this.roomid = rooid;
	}
	public boolean isIsleft() {
		return isleft;
	}
	public void setIsleft(boolean isleft) {
		this.isleft = isleft;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public ClientToClentChatMsg(int roomid, boolean isleft, String str) {
		super();
		this.roomid = roomid;
		this.isleft = isleft;
		this.str = str;
	}
	@Override
	public void doBiz() {
		RoomPojo roompojo=MyServer.getMyServer().getRooms().get(roomid);
		if(isleft){
			str=roompojo.getLeftPlayer().getName()+":"+str;
			ServerToClientChatMsg msg=new ServerToClientChatMsg(str);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());			
		}else{
			str=roompojo.getRightPlayer().getName()+":"+str;
			ServerToClientChatMsg msg=new ServerToClientChatMsg(str);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
		}
	}
	
	
	

}
