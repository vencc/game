package msg;

import net.MyServer;
import entity.RoomPojo;

/**
 * 玩家 准备 报文类
 * 
 * @author john
 * 
 */
public class ClientBeReady extends BaseMsg {
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
	

	public ClientBeReady(RoomPojo roompojo, boolean isleft) {
		super();
		this.roompojo = roompojo;
		this.isleft = isleft;
	}

	public void doBiz() {
		/*if(isleft){
			if(roompojo.isRightReady()){
				// 报文还没写完整
				ServerStartGame msg=new ServerStartGame();
				MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
				ServerStartGame msg2=new ServerStartGame();
				MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getRightPlayer());
				return;
			}
			if(roompojo.isLeftReady()){
				roompojo.setLeftReady(false);
				return;
			}
			roompojo.setLeftReady(true);
		}
		else{
			if(roompojo.isLeftReady()){
				// 报文还没写完整
				ServerStartGame msg=new ServerStartGame();
				MyServer.getMyServer().sendMsgToClient(msg, roompojo.getLeftPlayer());
				ServerStartGame msg2=new ServerStartGame();
				MyServer.getMyServer().sendMsgToClient(msg2, roompojo.getRightPlayer());
				return;
			}
			if(roompojo.isRightReady()){
				roompojo.setLeftReady(false);
				return;
			}
			roompojo.setRightReady(true);
		}*/

	}

}
