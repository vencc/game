package msg;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import net.MyServer;
import entity.RoomPojo;
/** 
 * 用户将正常落子的棋谱传递给服务器
 * @author john
 *
 */
public class ClientMovePieces extends BaseMsg{
    private RoomPojo roompojo;
    private boolean isleft;
    private List<Ellipse2D> list=new ArrayList<>();
    
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

	public List<Ellipse2D> getList() {
		return list;
	}

	public void setList(List<Ellipse2D> list) {
		this.list = list;
	}
	

	public ClientMovePieces(RoomPojo roompojo, boolean isleft,
			List<Ellipse2D> list) {
		super();
		this.roompojo = roompojo;
		this.isleft = isleft;
		this.list = list;
	}

	public void doBiz() {
		if(isleft){
			ServerMovePieces msg=new ServerMovePieces(list);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getRightPlayer());
		}else{
			ServerMovePieces msg=new ServerMovePieces(list);
			MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
		}
	}

}
