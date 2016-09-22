package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 功能: 五子棋实体类
 */
public class Chess extends JPanel {
	public static int Moves;// 本局比赛已下的总步数
	private ArrayList items = new ArrayList();
	/*
	 * 制作棋盘的宽高;
	 */
	public static final int BOARD_WIDTH = 515;
	/*
	 * 计算棋盘表格坐标(单元格宽高相等)
	 */
	private static int[] draw = new int[15];
	
	/**
	 * 功能：记录落子情况 其中0表示无子，1表示黑子，2表示白子
	 */
	public static int[][] map = new int[15][15];

	/*
	 * 功能：加载单元格间距
	 */
	static {
		for (int i = 0, WIDTH = 100; i < draw.length; i++, WIDTH += 30) {
			draw[i] = WIDTH;
		}
	}

	public Chess() {
		super(null);

		this.setBounds(0, 0, BOARD_WIDTH, BOARD_WIDTH);
		this.setBackground(new Color(255, 164, 85));
		this.addMouseListener(new MouseHandler());
	}

	/**
	 * 功能: 绘制棋盘表格图
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		char ch = 'A';
		g.setFont(new Font("宋体", Font.BOLD, 12));
		// 画横线
		for (int i = 0, width = 100 + 20 * 21; i < draw.length; i++, ch++) {
			g.setColor(Color.black);
			g.drawLine(100, draw[i], width, draw[i]);
			g.setColor(Color.blue);
			g.drawString("" + ch, 70, draw[i] + 3);
		}
		// 画竖线
		for (int i = 0, width = 100 + 20 * 21; i < draw.length; i++) {
			g.setColor(Color.black);
			g.drawLine(draw[i], 100, draw[i], width);
			g.setColor(Color.blue);
			g.drawString("" + (i + 1), draw[i] - 3, 75);
		}
		for (int i = 0; i < items.size(); i++) {
			g2.setColor(Color.black);
			g2.fill((Ellipse2D) items.get(i));
		}
	}

	/**
	 * 输入：鼠标点击 功能：为棋盘设定鼠标监听器 输出：棋盘落子效果
	 * 
	 * @author 林珊珊
	 */
	private class MouseHandler extends MouseAdapter { // 落子
		public void mousePressed(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			System.out.println("x:" + x + "y:" + y);

			paintItem(x, y);
			repaint();
		}

		/**
		 * 输入：监听器所获取的鼠标坐标 功能：为棋盘绘出棋子 输出：无
		 * 
		 * @author 林珊珊
		 * */
		void paintItem(int x, int y) {
			int player = 0;
			if (x > 85 && x < 535 && y > 85 && y < 535) {
				int X = x / 30;
				int Y = y / 30;
				int centerX = X * 30 + 9;
				int centerY = Y * 30 + 9;
				int i = (x - 90) / 30;
				int j = (y - 90) / 30;
				if (map[i][j] == 0) {//且符合
					Ellipse2D ellipse = new Ellipse2D.Double();
					ellipse.setFrameFromCenter(centerX, centerY, centerX + 12,
							centerY + 12);
					map[i][j] = 1;
					System.out.println("(i,j)=" + i + "," + j);
					Moves++;
					items.add(ellipse);
					if (Moves > 256) {
						// 格子放满未分胜负，平局
					} else
						Moves++;
				} else {
					System.out.println("这里已经放过棋子了");
				}
			} else {
				System.out.println("请将棋子放进棋盘内");
			}
		}
	}
}
