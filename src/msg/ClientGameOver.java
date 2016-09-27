package msg;

import net.MyServer;
import entity.RoomPojo;
/**
 * 客户端判定赢棋之后，向服务器发送的报文类
 * @author john
 *
 */
public class ClientGameOver extends BaseMsg{
    private RoomPojo roompojo;
	private boolean isleft;
	
	public RoomPojo getRoompojo() {
		return roompojo;
	}

	public void setRoompojo(RoomPojo roompojo) {
		this.roompojo = roompojo;
	}

	public boolean isIsleft() {
		return isleft;
	}

	public void setIsleft(boolean isleft) {
		this.isleft = isleft;
	}
	

	public ClientGameOver(RoomPojo roompojo, boolean isleft) {
		super();
		this.roompojo = roompojo;
		this.isleft = isleft;
	}

	public void doBiz() {
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
