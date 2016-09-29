package msg;

import entity.RoomPojo;
import net.MyServer;
/**
 * 被悔棋者向服务端发送是否愿意接收悔棋的报文类
 * @author john
 *
 */
public class ClientBackResult extends BaseMsg{
    private boolean result;
    private int roomid;
    private boolean isleft;
    
	

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

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

	

	public ClientBackResult(boolean result, int roomid, boolean isleft) {
		super();
		this.result = result;
		this.roomid = roomid;
		this.isleft = isleft;
	}

	public void doBiz() {
		RoomPojo roompojo=MyServer.getMyServer().getRooms().get(roomid);
         if(result){
        	if(isleft){
        		ServerBackSucceed msg=new ServerBackSucceed();
        		MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
        	}
        	else{
        		ServerBackSucceed msg=new ServerBackSucceed();
        		MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
        	}
         }
         else{
        	if(isleft){
        		 ServerBackFail msg=new  ServerBackFail();
     		    MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
     	    }
     	    else{
     	    	 ServerBackFail msg=new  ServerBackFail();
     		    MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
     	    } 	 
         }
	}

}
