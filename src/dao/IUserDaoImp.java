package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entity.User;

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
				u=new User(r.getString(1),r.getString(2),r.getInt(3));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;	
	}
	 /**
	   * 功能: 设置玩家的头像
	   * @param FileName  头像路径
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
				u=new User(rs.getString("name"),rs.getString("fileName"),rs.getInt("winNum"));
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
	   * @param winNum 战绩
	   */
	@Override
	public
	  void update(int new_winNum,String name) {
		sql="update user set winNum=? where name=?";
		String[] paras={new_winNum+"",name};
		b.doUpdate(sql, paras);
	}
	
}
	
  