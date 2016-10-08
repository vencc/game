package msg;

import entity.User;
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
		roompojo.setLeftReady(false);
		roompojo.setRightReady(false);
		MyServer.getMyServer().getRooms().set(roomid,roompojo);
		if(isleft){
			MyServer.getMyServer().updateWinNum(roompojo.getLeftPlayer());
			roompojo.setLeftPlayer(MyServer.getMyServer().findUser(roompojo.getLeftPlayer().getName()));
			MyServer.getMyServer().updateLoseNum(roompojo.getRightPlayer());
			roompojo.setRightPlayer(MyServer.getMyServer().findUser(roompojo.getRightPlayer().getName()));
			User user=MyServer.getMyServer().findUser(roompojo.getLeftPlayer().getName());
			ServerWinMsg msg=new ServerWinMsg(user,MyServer.getMyServer().findUser(roompojo.getRightPlayer().getName()));
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
			ServerDefeatmsg msg2=new ServerDefeatmsg(MyServer.getMyServer().findUser(roompojo.getRightPlayer().getName()),user);
			MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getRightPlayer());
		}
		else{
			MyServer.getMyServer().updateWinNum(roompojo.getRightPlayer());
			MyServer.getMyServer().updateLoseNum(roompojo.getLeftPlayer());
			roompojo.setLeftPlayer(MyServer.getMyServer().findUser(roompojo.getLeftPlayer().getName()));
			roompojo.setRightPlayer(MyServer.getMyServer().findUser(roompojo.getRightPlayer().getName()));
			User user=MyServer.getMyServer().findUser(roompojo.getLeftPlayer().getName());
			ServerWinMsg msg=new ServerWinMsg(MyServer.getMyServer().findUser(roompojo.getRightPlayer().getName()),user);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
			ServerDefeatmsg msg2=new ServerDefeatmsg(user,MyServer.getMyServer().findUser(roompojo.getRightPlayer().getName()));
			MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getLeftPlayer());
		}
	}
}



