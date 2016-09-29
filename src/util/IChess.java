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
   * @param type  1:白棋   2:黑棋
   */
  boolean add(int x,int y,int type);

  /**
   * 功能: 悔棋时删除棋盘上的棋子
   * 作者: 黄欢欢   时间: 2016-09-20
   * @param x  横坐标
   * @param y  纵坐标·
   * @param type  1:白棋   2:黑棋
   */
  void delete(int type);

  /**
   * 功能: 判断是否连成五子
   * 作者: 黄欢欢   时间: 2016-09-20
   * @param x  横坐标
   * @param y  纵坐标
   * @param type  1:白棋   2:黑棋
   */
  boolean compare(int x,int y,int type);
  /** 功能：初始化棋盘，默认棋牌每个位置初始值为：0；
   */
  void ResetGame();
  /**  功能：判断五子棋人机电脑默认为白方，制定白方下棋规则，给定白子位置并以返回白子坐标
   * 作者：章培舜  时间：2016-09-23
   * @param x 横坐标
   * @param y 纵坐标
   * @return
   */
  int [] ComTurn(int x,int y);
}
