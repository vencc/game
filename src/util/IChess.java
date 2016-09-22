package util;

/**
 * 功能: 五子棋规则接口
 * 作者: 黄欢欢   时间: 2016-09-20
 */
public interface IChess {
  /**
   * 功能: 在棋盘上添加棋子
   * 作者: 黄欢欢   时间: 2016-09-20
   * @param x  横坐标
   * @param y  纵坐标
   * @param type  0:白棋   1:黑棋
   */
  void add(int x,int y,int type);

  /**
   * 功能: 悔棋时删除棋盘上的棋子
   * 作者: 黄欢欢   时间: 2016-09-20
   * @param x  横坐标
   * @param y  纵坐标
   * @param type  0:白棋   1:黑棋
   */
  void delete(int x,int y,int type);

  /**
   * 功能: 判断是否连成五子
   * 作者: 黄欢欢   时间: 2016-09-20
   * @param x  横坐标
   * @param y  纵坐标
   * @param type  0:白棋   1:黑棋
   */
  boolean compare(int x,int y,int type);

}
