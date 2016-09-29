package msg;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import net.MyServer;
import entity.RoomPojo;
/** 
 * 用户将落子的棋谱传递给服务器
 * @author john
 *
 */
public class ClientMovePieces extends BaseMsg{
    private int roomid;
    private boolean isleft;
    private int[][] chess;
    private boolean backChess=false;
    
	

	public boolean isBackChess() {
		return backChess;
	}



	public void setBackChess(boolean backChess) {
		this.backChess = backChess;
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



	public int[][] getChess() {
		return chess;
	}



	public void setChess(int[][] chess) {
		this.chess = chess;
	}
	



	


	public ClientMovePieces(int roomid, boolean isleft, int[][] chess,
			boolean backChess) {
		super();
		this.roomid = roomid;
		this.isleft = isleft;
		this.chess = chess;
		this.backChess = backChess;
	}



	public void doBiz() {
		RoomPojo roompojo =MyServer.getMyServer().getRooms().get(roomid);
		if(isleft){
			ServerMovePieces msg=new ServerMovePieces(chess,backChess);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
		}else{
			ServerMovePieces msg=new ServerMovePieces(chess,backChess);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
		}
	}

}
