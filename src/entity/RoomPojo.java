package entity;

import java.io.Serializable;
/**
 * 房间实体类
 * @author john
 *
 */

public class RoomPojo implements Serializable{
	public static final int IDLE=0;//房间无人
	public static final int WAIT=1;//一人在等待
	public static final int PLAYING=0;//游戏进行
	private int rid;//房间编号
	private User leftPlayer;//房间内左边玩家
	private User rightPlayer;//房间内右边玩家
	private int status;//房间的状态
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public User getLeftPlayer() {
		return leftPlayer;
	}
	public void setLeftPlayer(User leftPlayer) {
		this.leftPlayer = leftPlayer;
	}
	public User getRightPlayer() {
		return rightPlayer;
	}
	public void setRightPlayer(User rightPlayer) {
		this.rightPlayer = rightPlayer;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public RoomPojo(int rid, User leftPlayer, User rightPlayer, int status) {
		super();
		this.rid = rid;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		this.status = status;
	}
	
	
	

}
