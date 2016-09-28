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

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import util.ChessImpl;
import util.IChess;

/**
 * 功能：棋盘面板 作者:林珊珊
 * */

public class ChessTable extends JPanel {
		private int model;
		private Room room;
		public static final int chess_BLACK = 2;
		public static final int chess_WHITE = 1;
		public static final int chess_EMPTY = 0;
		public static boolean isReady = false;
		public static int Moves;// 本局比赛已下的总步数
		public static IChess chessimpl = new ChessImpl();
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
			super(null);
			this.room = room;
			model = 0;
			Moves = 0;
			this.setBounds(0, 0, BOARD_WIDTH, BOARD_WIDTH);
			this.addMouseListener(new MouseHandler());
		}

		public ChessTable() {// 人机
			super(null);
			Moves = 0;
			model = 1;
			chessimpl.ResetGame();

			this.setBounds(0, 0, BOARD_WIDTH, BOARD_WIDTH);
			this.addMouseListener(new MouseHandler());
		}

		/**
		 * 输入：鼠标点击 功能：为棋盘设定鼠标监听器 输出：棋盘落子效果
		 * 
		 * @author 林珊珊
		 */
		public class MouseHandler extends MouseAdapter {
			public void mousePressed(MouseEvent event) {

				int x = event.getX();
				int y = event.getY();

				if (x > 30 && x < 535 && y > 30 && y < 535) {
					paintItem(x, y);
					System.out.println("is here!");
					if (model != 1)
						room.repaint();
					repaint();
				} else {
					System.out.println("请将棋子放进棋盘内");
				}
				/*
				 * if (map[i][j] == 0) { Ellipse2D ellipse = new
				 * Ellipse2D.Double(); ellipse.setFrameFromCenter(centerX,
				 * centerY, centerX + 12, centerY + 12); map[i][j] = 1;
				 * System.out.println("(i,j)=" + i + "," + j);
				 * items.add(ellipse); if (Moves > 256) { // 格子放满未分胜负，平局 } else
				 * Moves++; System.out.println("move=" + Moves); } else {
				 * System.out.println("这里已经放过棋子了"); }
				 */

				System.out.println("x:" + x + "y:" + y);
				/*
				 * if(悔棋) unpaintItem(x, y);
				 */
				/*
				 * if(落子) paintItem(x, y);
				 */
				/*
				 * if(认输) 游戏结束
				 */
				/*
				 * if(退出) 回到房间列表
				 */

				// ChessTable.paintItem(x, y);

				// 判断是否有胜负
			}
		}

		/**
		 * 输入：监听器所获取的鼠标坐标 功能：为棋盘绘出棋子 输出：无
		 * 
		 * @author 林珊珊
		 * */
		void paintItem(int x, int y) {// 落子
			int[] index_copy = new int[2];
			int X = x / 34;
			int Y = y / 34;
			int centerX = X * 34 + 42;
			int centerY = Y * 34 + 42;
			int i = (x - 21) / 34;
			int j = (y - 21) / 34;
			if (i < 15 && j < 15) {
				if (model == 1) {// 人机
					System.out.println("调用算法接口处i,j=" + i + "," + j);
					index_copy=chessimpl.ComTurn(i, j);
					chessimpl.add(i, j, 2);
					if (index_copy != null) {
						chessimpl.add(index_copy[0], index_copy[1], 2);
					}
					Moves += 2;
				} else {
					// chessimpl.add(i, j, 1);
					System.out.println("！");
					Moves += 1;
				}
				// ReAddItems();// 与接口处的棋子对象重新接收
				if (Moves > 256) {
					// 格子放满未分胜负，平局
				} else {
					// Moves++;//修改至Impl里
				}
			} else {
				System.out.println("棋子没添加成功");
			}
		}

		/**
		 * 功能: 绘制棋盘表格图、重绘已下的棋子
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// if (room.getStatus() == 1) {
			// g.drawImage(new ImageIcon("resource/imag/table_ready.png")
			// .getImage(), 45, 45, 570, 570, this);
			// } else {
			g.drawImage(new ImageIcon("resource/imag/pan.png").getImage(), 0,
					0, 565, 565, this);
			// }
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.black);
			char ch = 'A';
			g.setFont(new Font("宋体", Font.BOLD, 12));

			/*
			 * 功能：加载单元格间距
			 */

			for (int i = 0, WIDTH = 42; i < draw.length; i++, WIDTH += 34) {
				draw[i] = WIDTH;
			}

			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (ChessImpl.chess[i][j] != 0) {
						int m, n;
						m = i * 34 + 42;
						n = j * 34 + 42;
						Ellipse2D ellipse = new Ellipse2D.Double();
						ellipse.setFrameFromCenter(m, n, m + 14, n + 14);
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
								RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setComposite(AlphaComposite.getInstance(
								AlphaComposite.SRC_OVER, 0.5f));

						// 白子
						GradientPaint gp1 = new GradientPaint(
								(float) ellipse.getMinX(),
								(float) ellipse.getMinY(), new Color(174, 173,
										171), (float) ellipse.getMaxX(),
								(float) ellipse.getMaxY(), new Color(116, 115,
										114));
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
						g2.fill(ellipse);
						repaint();
					}
				}
			}
		}

		/**
		 * 输入：监听器所获取的鼠标坐标 功能：为棋盘作悔棋操作 输出：无
		 * 
		 * @author 林珊珊
		 * */
		public void unpaintItem() {// 悔棋传入玩家对象
			// if (user.isleft) else{}{输入玩家是左/右玩家进行悔棋 且符合对方同意悔棋
			// 报错注释
			if (Moves == 0) {
				System.out.println("已经没有棋子了");
				return;
			}
			chessimpl.delete(2);
			Moves--;
			// ReAddItems();

			repaint();

		}
	}
