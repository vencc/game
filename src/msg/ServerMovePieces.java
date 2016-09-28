package msg;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import net.MyClient;
import net.MyServer;
/**
 * 服务器将落子后的棋谱传递给对面的客户端
 * @author john
 *
 */
public class ServerMovePieces extends BaseMsg{
	private int [][] chess;


	public int[][] getChess() {
		return chess;
	}


	public void setChess(int[][] chess) {
		this.chess = chess;
	}


	public ServerMovePieces(int[][] chess) {
		super();
		this.chess = chess;
	}


	public void doBiz() {
		//MyClient.getMyClient().getRoom().receiveChess(chess);
	}

}
