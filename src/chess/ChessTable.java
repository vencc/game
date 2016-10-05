package chess;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.MyClient;
import msg.ClientGameOver;
import msg.ClientMovePieces;
import net.MyServer;
import util.AudioPlayer;
import util.ChessImpl;
import util.IChess;

/**
 * 功能：棋盘面板 作者:林珊珊
 */

public class ChessTable extends JPanel {

  private Executor pool = Executors.newFixedThreadPool(2); // 2个线程容量的线程池
  private RobotThread robotThread = new RobotThread(this, chessimpl); // 机器人线程
  private HumanThread humanThread = new HumanThread(this, chessimpl); // 人类线程
  private AudioPlayer audioPlayer=new AudioPlayer("resource/audio/down.wav",false);
  private AudioPlayer audioStopPlayer=new AudioPlayer("resource/audio/stop.wav",false);
  private ChessTable chessTable = this;
  private boolean lock = false; // 同步锁
  private int humanX; // 鼠标点击的坐标
  private int humanY; // 鼠标点击的坐标
  public int model;
  private Room room;

  public static final int chess_BLACK = 2;
  public static final int chess_WHITE = 1;
  public static final int chess_EMPTY = 0;
  public static boolean isReady = false;
  public int Moves;// 本局比赛已下的总步数
  private int[][] mark = new int[15][15];
  public static IChess chessimpl = new ChessImpl();

  public static IChess getChessimpl() {
    return chessimpl;
  }
  public void setMark(int x,int y){
    mark[x][y]=1;
  }

  /*
     * 制作棋盘的宽高;
     */
  public static final int BOARD_WIDTH = 515;
  /*
   * 计算棋盘表格坐标(单元格宽高相等)
   */
  private int[] draw = new int[15];

  /**
   * 功能：记录落子情况 其中0表示无子，1表示白棋，2表示黑子
   */
  public static int[][] map = new int[15][15];

  public ChessTable(Room room) {

    this.room = room;
    model = 0;
    Moves = 0;
    System.out.println("这个房间是网络对战模式");
    this.setBounds(0, 0, BOARD_WIDTH, BOARD_WIDTH);
    this.addMouseListener(new MouseHandler());
  }

  public ChessTable(Room room,int flag) {// 人机
    super(null);
    Moves = 0;
    model = 1;
    this.room=room;
    chessimpl.ResetGame();

    this.setBounds(0, 0, BOARD_WIDTH, BOARD_WIDTH);
    pool.execute(humanThread); // 开启人类线程
    pool.execute(robotThread); // 开启机器人线程
  }

  /**
   * 功能: 机器人下棋 作者: 黄欢欢 时间: 2016-09-28
   */
  public synchronized void robotChess() {
    System.out.println("机器线程开启");
    synchronized (chessTable) {
      while (true) {
        if (!lock) {
          try {
            wait();
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          try {
            Thread.sleep(700);
          } catch (Exception e) {
            e.printStackTrace();
          }
          int[] XY = chessimpl.ComTurn(humanX, humanY);
          mark[XY[0]][XY[1]] = 1;
          repaint();
          Moves++;
          lock = false;
          audioPlayer.run();
          if(Moves==225)
            room.pingju();
          else if(chessimpl.compare(XY[0],XY[1],1)){
            room.deafeat();
          }else
          chessTable.notifyAll();
        }
      }
    }
  }

  /**
   * 输入：鼠标点击 功能：为棋盘设定鼠标监听器 输出：棋盘落子效果
   *
   * @author 林珊珊
   */
  public class MouseHandler extends MouseAdapter {
    public void mousePressed(MouseEvent event) {

      synchronized (chessTable) {
        int x = event.getX();
        int y = event.getY();

        if (x > 30 && x < 535 && y > 30 && y < 535) {
          humanX = (x - 21) / 34;
          humanY = (y - 21) / 34;
          if (model == 1) {// 人机
            if (paintItem(humanX, humanY)) {
              room.backGame=true;
              Moves++;
              System.out.println("黑棋在这" + humanX + "," + humanY);
              System.out.println("is here!");
              mark[humanX][humanY] = 1;
              lock = true;
              repaint();
              audioPlayer.run();
              if(Moves==225)
                room.pingju();
              else if(chessimpl.compare(humanX,humanY,2)){
                room.win();
              }else
              chessTable.notifyAll();
            } else {
              audioStopPlayer.run();
            }
          } else {

            if(room.isCanplay()) {
              if (paintItem(humanX, humanY)) {
                room.backGame=true;
                Moves++;
                if (Moves == 225)
                  room.pingju();
                room.setCanplay(false);
                System.out.println("kjdhasjdakdhads+==========" + ChessImpl.chess[0][0]);
                ClientMovePieces msg = new ClientMovePieces(
                    room.getRid(), room.isleft, ChessImpl.chess, false, humanX, humanY);
                MyClient.getMyClient().sendMsg(msg);
                room.getChessPanel().setMark(humanX, humanY);
                room.repaint();
                audioPlayer.run();
                if(room.isleft) {
                  if (chessimpl.compare(humanX, humanY, 2)) {//黑棋赢了，发送游戏结束报文
                    ClientGameOver msg1 = new ClientGameOver(room.getRid(), room.isleft);
                    MyClient.getMyClient().sendMsg(msg1);
                  }
                }else{
                  if (chessimpl.compare(humanX, humanY, 1)) {//白棋赢了
                    ClientGameOver msg1 = new ClientGameOver(room.getRid(), room.isleft);
                    MyClient.getMyClient().sendMsg(msg1);
                  }
                }
              }
              else
                audioStopPlayer.run();
            }

          }
        } else {
          System.out.println("请将棋子放进棋盘内");
        }
        System.out.println("x:" + x + "y:" + y);
      }
    }
  }

  /**
   * 输入：监听器所获取的鼠标坐标 功能：为棋盘绘出棋子 输出：无
   *
   * @author 林珊珊
   */
  boolean paintItem(int i, int j) {// 落子
    boolean succeed = false;
    if (i < 15 && j < 15) {
      if (model == 1) {// 人机
        if (!chessimpl.add(i, j, 2)) {
          return false;// 棋子不能下在这个位置
        }
        return true;
      } else {// 网络对战
        if (room.isleft) {// 黑棋玩家
          Moves++;
          succeed = chessimpl.add(i, j, 2);


        } else {// 白棋玩家
          Moves++;
          succeed = chessimpl.add(i, j, 1);


        }
        return succeed;
        // System.out.println("未能明确棋子颜色");
      }
    } else {
      System.out.println("棋子没添加成功");
    }
    return false;
  }

  /**
   * 功能: 绘制棋盘表格图、重绘已下的棋子
   */
  @Override
  public void paintComponent(Graphics g) {
    g.drawImage(new ImageIcon("resource/imag/pan.png").getImage(), -8, -8,
        565, 565, this);
    // }
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.black);
    char ch = 'A';
    g.setFont(new Font("宋体", Font.BOLD, 12));

		/*
		 * 功能：加载单元格间距
		 */

    for (int i = 0, WIDTH = 35; i < draw.length; i++, WIDTH += 34) {
      draw[i] = WIDTH;
    }

    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        if (ChessImpl.chess[i][j] != 0) {
          int m, n;
          m = i * 34 + 35;
          n = j * 34 + 35;
          Ellipse2D ellipse = new Ellipse2D.Double();
          Ellipse2D ellipse2D = new Ellipse2D.Double();
          ellipse.setFrameFromCenter(m, n, m + 14, n + 14);
          ellipse2D.setFrameFromCenter(m + 1, n - 1, m + 15, n + 13);
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setComposite(AlphaComposite.getInstance(
              AlphaComposite.SRC_OVER, 0.5f));

          // 白子
          GradientPaint gp1 = new GradientPaint(
              (float) ellipse.getMinX(),
              (float) ellipse.getMinY(),
              new Color(174, 173, 171),
              (float) ellipse.getMaxX(),
              (float) ellipse.getMaxY(), new Color(116, 115, 114));
          GradientPaint gp3 = new GradientPaint(
              (float) ellipse.getMinX(),
              (float) ellipse.getMinY(), Color.white,
              (float) ellipse.getMaxX(),
              (float) ellipse.getMaxY(), Color.gray);
          // 黑子
          GradientPaint gp2 = new GradientPaint(
              (float) ellipse.getMinX() - 1,
              (float) ellipse.getMinY() - 1, Color.white,
              (float) ellipse.getCenterX() - 1,
              (float) ellipse.getCenterY() - 1, Color.gray);
          GradientPaint gp4 = new GradientPaint(
              (float) ellipse.getMinX() - 1,
              (float) ellipse.getMinY() - 1, Color.white,
              (float) ellipse.getCenterX() - 1,
              (float) ellipse.getCenterY() - 1, Color.black);
          // 黑子
          // System.out.println("m=" + m + "n=" + n);
          if (ChessImpl.chess[i][j] == 2) {
            // System.out.println("棋桌在"+m+","+n+"处画了一个黑子");
            g2.setPaint(gp2);
            g2.fill(ellipse);
            g2.setPaint(gp4);

          } else if (ChessImpl.chess[i][j] == 1) {// 白子
            // System.out.println("棋桌在"+m+","+n+"处画了一个白子");
            g2.setPaint(gp1);
            g2.fill(ellipse);
            g2.setPaint(gp3);

          } else {
            System.out.println("定位不准确，未获得棋子颜色");
          }
          g2.setComposite(AlphaComposite.getInstance(
              AlphaComposite.SRC_OVER, 1));
          g2.fill(ellipse2D);
          if (mark[i][j] == 1) {
            g2.setColor(Color.red);
            g2.drawLine(m - 3, n, m + 3, n);
            g2.drawLine(m, n - 3, m, n + 3);
            mark[i][j] = 0;
          }
        }
      }
    }
  }

  /**
   * 输入：监听器所获取的鼠标坐标 功能：为棋盘作悔棋操作 输出：无
   *
   * @author 林珊珊
   */
  public void unpaintItem() {
    if (model == 0) {//联机悔棋
      if (room.isleft) {
        chessimpl.delete(2);//黑方悔棋
      } else {
        chessimpl.delete(1);//白方悔棋
      }
      //Moves--;
    } else {
      chessimpl.delete(2);
    }
    repaint();
  }

  public void receiveChess(int[][] chess, boolean backChess,int x,int y) {
    if (!backChess) {
      room.setCanplay(true);
    }
    System.out.println("我是TRUE：" + room.isCanplay());
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 17; j++) {
        // mark[i][j]=chess[i][j];
        ChessImpl.chess[i][j] = chess[i][j];
      }
    }
    System.out.println(x+","+y);
    room.getChessPanel().setMark(x,y);
    repaint();
    audioPlayer.run();
  }
}
