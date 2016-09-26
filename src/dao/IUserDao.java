package dao;

import entity.User;

/**
 * 功能: User类数据库连接接口
 * @author 黄欢欢  时间: 2016-09-26
 */
public interface IUserDao {
  /**
   * 功能: 根据姓名查找该玩家信息
   * @param userName 玩家姓名
   * @return 返回实体类供他人调用
   */
  User findUser(String userName);

  /**
   * 功能: 设置玩家的头像
   * @param FileName  头像路径
   */
  void updateUserImag(String FileName);

  /**
   * 功能: 修改玩家姓名
   * @param userName 玩家姓名
   */
  void updateUserName(String userName);
  /**
   * 功能: 删除玩家
   * @param userName 玩家姓名
   */
  void deleteUser(String userName);
}
