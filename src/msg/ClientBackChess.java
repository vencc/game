package msg;

import net.MyServer;
import entity.RoomPojo;
/**
 * 想悔棋者向服务端发送的报文类
 * @author john
 *
 */
public class ClientBackChess extends BaseMsg{
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
	


	public ClientBackChess(RoomPojo roompojo, boolean isleft) {
		super();
		this.roompojo = roompojo;
		this.isleft = isleft;
	}


	public void doBiz() {
         if(isleft){
        	 ServerBackChess msg=new ServerBackChess();
        	 MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
         }
         else{
        	 ServerBackChess msg=new ServerBackChess();
        	 MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
         }
	}

}
