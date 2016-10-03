package dao;

import java.util.List;

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
  void updateUserImag(String FileName,String uname);

  /**
   * 功能: 删除玩家
   * @param userName 玩家姓名
   */
  void deleteUser(String userName);

  /**
   * 功能: 查找所有玩家信息，返回数组
   * @return 返回实体类供他人调用
   */
	List<User> findAll();
  
  /**
   * 功能: 根据姓名查找该玩家胜利局数
   * @param userName 玩家姓名
   * @return 胜利局数
   */
  int findWinNum(String userName);

  /**
   * 功能: 更新胜利局数
   * @param new_winNum 当前胜利局数
   */
  void update(int new_winNum,String name);

  /**
   * 功能: 创建新玩家
   * @param user
   */
  void insertUser(User user);
  /**
   * 功能: 更新失败局数
   * @param loseNum 当前失败局数
   * @param name 姓名
   */
  void updateLoseNum(int loseNum,String name);

}
