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
	private List<Ellipse2D> list=new ArrayList<>();
	
	public List<Ellipse2D> getList() {
		return list;
	}

	public void setList(List<Ellipse2D> list) {
		this.list = list;
	}


	public ServerMovePieces(List<Ellipse2D> list) {
		super();
		this.list = list;
	}
	public void doBiz() {
		//MyClient.getMyClient().getRoom().receiveChess();
	}

}
