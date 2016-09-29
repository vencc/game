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
	private boolean backChess;
	private int [][] chess;


	public int[][] getChess() {
		return chess;
	}


	public void setChess(int[][] chess) {
		this.chess = chess;
	}
	


	public boolean isBackChess() {
		return backChess;
	}


	public void setBackChess(boolean backChess) {
		this.backChess = backChess;
	}


	public ServerMovePieces(int[][] chess) {
		super();
		this.chess = chess;
	}

	

	public ServerMovePieces( int[][] chess,boolean backChess) {
		super();
		this.backChess = backChess;
		this.chess = chess;
	}


	public void doBiz() {
		MyClient.getMyClient().getRoom().getChessPanel().receiveChess(chess,backChess);
	}

}
