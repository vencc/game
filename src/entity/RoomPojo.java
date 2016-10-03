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
	public static final int PLAYING=2;//游戏进行
	private int rid;//房间编号
	private User leftPlayer;//房间内左边玩家
	private User rightPlayer;//房间内右边玩家
	private int status;//房间的状态
	private boolean isLeftReady=false;//左边玩家是否准备
	private boolean isRightReady=false;//右边玩家是否准备
	private boolean isLeftPlay=false;//左边玩家是否可落子
	private boolean isRightPlay=false;//右边玩家是否可落子

	public boolean isLeftReady() {
		return isLeftReady;
	}
	public void setLeftReady(boolean isLeftReady) {
		this.isLeftReady = isLeftReady;
	}
	public boolean isRightReady() {
		return isRightReady;
	}
	public void setRightReady(boolean isRightReady) {
		this.isRightReady = isRightReady;
	}
	public boolean isLeftPlay() {
		return isLeftPlay;
	}
	public void setLeftPlay(boolean isLeftPlay) {
		this.isLeftPlay = isLeftPlay;
	}
	public boolean isRightPlay() {
		return isRightPlay;
	}
	public void setRightPlay(boolean isRightPlay) {
		this.isRightPlay = isRightPlay;
	}
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
	public String toString(){
		return "房间号: "+rid+"状态: "+status+", 左边玩家: "+leftPlayer+",是否准备"+isLeftReady()+", 右边玩家: "+rightPlayer;
	}
	
	
	

}
