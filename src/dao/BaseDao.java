package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能: 数据库公共类，工具类
* @author 王小明  时间: 2016-09-27
 */
public class BaseDao {
	// 1.四个静态常量的参数
	public static final String url = "jdbc:mysql://localhost:3306/game";
	public static final String username = "root";
	public static final String password = "123456";
	public static final String classname = "com.mysql.jdbc.Driver";

	// 2.加载驱动并获取连接
	public static Connection getConnection() {
		try {
			Class.forName(classname);
			Connection connection = DriverManager.getConnection(url, username,
					password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 3.创建statement或preparedStatement并执行sql语句
	// insert update create delete --> executeUpdate
	// select ---> executeQuery --> ResultSet --> 遍历
	// select * from student where sid = ？ and sname = ？
	public static ResultSet doQuery(String sql, String[] paras) {
		// 创建psm --> sql
		Connection connection = getConnection();
		if (connection == null)
			return null;
		// 执行操作
		ResultSet rs = null;
		PreparedStatement psm = null;
		try {
			psm = connection.prepareStatement(sql);
			if (paras != null) {
				// 设置动态参数 --> ?
				int index = 1;
				for (String str : paras) {
					psm.setString(index++, str);
				}
			}
			rs = psm.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//查找用户信息，返回整个用户信息
		public static ResultSet doQuery(String sql) {
			// 创建psm --> sql
			Connection connection = getConnection();
			if (connection == null)
				return null;
			// 执行操作
			ResultSet rs = null;
			PreparedStatement psm = null;
			try {
				psm = connection.prepareStatement(sql);
				rs = psm.executeQuery();
				return rs;
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	
	//更新  不需要ResultSet
	public static int doUpdate(String sql, String[] paras) {
		// 创建psm --> sql
		Connection connection = getConnection();
		if (connection == null)
			return 0;
		// 执行操作
		PreparedStatement psm = null;
		try {
			psm = connection.prepareStatement(sql);
			if (paras != null) {
				// 设置动态参数 --> ?
				int index = 1;
				for (String str : paras) {
					psm.setString(index++, str);
				}
			}
			int result = psm.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			doClose(connection,psm,null);
		}
	}

	public static void doClose(Connection conn, Statement psm,
			ResultSet rs) {
		// 关闭各种资源
		try {
			if (rs != null)
				rs.close();
			if (psm != null)
				psm.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean doInsert(String sql,String[] paras){
		Connection connection = getConnection();
		if (connection == null)
			return false;
		// 执行操作
		PreparedStatement psm = null;
		try {
			psm = connection.prepareStatement(sql);
			if (paras != null) {
				// 设置动态参数 --> ?
				int index = 1;
				for (String str : paras) {
					psm.setString(index++, str);
				}
			}
			boolean result = psm.execute();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			doClose(connection,psm,null);
		}
	}
}
