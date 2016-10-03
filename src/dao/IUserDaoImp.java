package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.User;
/**
 * 功能: User类接口实现
* @author 王小明  时间: 2016-09-27
 */
public class IUserDaoImp implements IUserDao {
	String sql=null;
	BaseDao b=new BaseDao();
	 /**
	   * 功能: 根据姓名查找该玩家信息
	   * @param userName 玩家姓名
	   * @return 返回实体类供他人调用
	   */
	@Override
	public
	 User findUser(String userName){
		sql="select * from user where name=?";
		String paras[]={userName};
		ResultSet r=b.doQuery(sql, paras);
		User u=null;
		try {
			while(r.next())
			{
				u=new User(r.getString(1),r.getString(2),Integer.parseInt(r.getString(3)),Integer.parseInt(r.getString(4)),Integer.parseInt(r.getString(5)));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;	
	}
	/**
	   * 功能: 查找玩家信息
	   * @return 返回实体类供他人调用
	   */
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from userinfo";
		ResultSet rs=b.doQuery(sql);
		ArrayList list=new ArrayList();
		User ui=null;
	  	 try {
			while(rs.next())
			 {
		 ui=new User(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)));
         list.add(ui);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	 return list;
	}
	
	
	 /**
	   * 功能: 设置玩家的头像
	   * @param new_FileName  头像路径
	   */
	@Override
	public
	  void updateUserImag(String new_FileName,String uname) {
		sql="update user set fileName=?  where name=? ";
		String[] paras={new_FileName,uname};
		b.doUpdate(sql, paras);
	}

	
	 /**
	   * 功能:删除玩家
	   * @param userName 玩家姓名
	   */
	@Override
	public
	  void deleteUser(String userName) {
		sql="delete from user where name=?";
		String[] paras={userName};
		b.doUpdate(sql, paras);
		
	}

	/**
	   * 功能：查找战绩
	   * @param userName 玩家姓名
	   * @return 战绩
	   */
	@Override
	public int findWinNum(String userName) {
		sql="select * from user where name=?";
		String[] paras={userName};
		int	t=1;
		ResultSet rs=b.doQuery(sql, paras);
		User u=null;
		try {
			while(rs.next())
			{
				u=new User(rs.getString("name"),rs.getString("fileName"),Integer.parseInt(rs.getString("winNum")),Integer.parseInt(rs.getString("loseNum")),Integer.parseInt(rs.getString("tiedNum")));
			    t=u.getWinNum();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			t=0;
		}
	    return t;
	}

	 /**
	   * 功能：修改战绩
	   * @param new_winNum 战绩
	   */
	@Override
	public
	  void update(int new_winNum,String name) {
		sql="update user set winNum=? where name=?";
		String[] paras={new_winNum+"",name};
		b.doUpdate(sql, paras);
	}

	public void insertUser(User user){
		sql="insert into user(name,winNum,loseNum,tiedNum) values(?,?,?,?)";
    System.out.println("新增用户");
    String[] paras={user.getName(),0+"",0+"",0+""};
    b.doInsert(sql,paras);
	}
	public void updateLoseNum(int loseNum,String name){
		sql="update user set loseNum=? where name=?";
		String[] paras={loseNum+"",name};
		b.doUpdate(sql, paras);
	}
}
	
  