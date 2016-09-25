package chess;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ChessImpl;
import entity.Chess;

public class Room extends JFrame {
	public static final int chess_BLACK = 2;
	public static final int chess_WHITE = 1;
	public static final int chess_EMPTY = 0;
	// private RoomList roomList;
	private static ChessTable ChessTablePanel;
	private JButton But_ready;
	private static ChessImpl chessimpl;
	private JButton But_regret;

	public Room() {
		// this.roomList = roomList;
		chessimpl = new ChessImpl();
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel gamerInfo = new JPanel();
		getContentPane().add(gamerInfo, BorderLayout.WEST);

		JPanel gameRoom = new JPanel();
		gameRoom.setBackground(Color.WHITE);
		getContentPane().add(gameRoom, BorderLayout.CENTER);
		gameRoom.setLayout(new BorderLayout(0, 0));

		JPanel UIPanel = new JPanel();
		gameRoom.add(UIPanel, BorderLayout.SOUTH);
		gamerInfo.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel gamer1 = new JPanel();
		gamerInfo.add(gamer1);

		JPanel gamer2 = new JPanel();
		gamerInfo.add(gamer2);

		ChessTablePanel = new ChessTable();
		gameRoom.add(ChessTablePanel, BorderLayout.CENTER);

		UIPanel.setLayout(new BorderLayout(0, 0));

		But_ready = new JButton("准备");
		But_ready.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ChessTablePanel.isReady) {
					But_ready.setText("准备");
					ChessTablePanel.setReady(false);
					System.out.println(ChessTablePanel.isReady);
				} else {
					But_ready.setText("取消准备");
					ChessTablePanel.setReady(true);
					System.out.println(ChessTablePanel.isReady);
				}
			}
		});
		UIPanel.add(But_ready, BorderLayout.WEST);

		But_regret = new JButton("悔棋");
		But_regret.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ChessTablePanel.Moves > 0)
					ChessTablePanel.unpaintItem();
				else {
					System.out.println("当前已经没有棋子了");
				}
			}
		});
		UIPanel.add(But_regret, BorderLayout.CENTER);

		JButton But_start = new JButton("开始");
		UIPanel.add(But_start, BorderLayout.NORTH);

		JButton But_exit = new JButton("退出");
		But_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toRoomList();
			}
		});
		UIPanel.add(But_exit, BorderLayout.EAST);

		gamer1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblNewLabel = new JLabel("对手信息标签");
		gamer1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("个人信息标签");
		gamer2.add(lblNewLabel_1);

		JPanel logoPanel = new JPanel();
		gameRoom.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new BorderLayout(0, 0));

		JLabel lbllogo = new JLabel("五子棋LOGO");
		logoPanel.add(lbllogo, BorderLayout.NORTH);

		JPanel chatRoom = new JPanel();
		getContentPane().add(chatRoom, BorderLayout.EAST);
		chatRoom.setLayout(new BorderLayout(0, 0));

		JLabel label_1 = new JLabel("聊天室");
		chatRoom.add(label_1);
		init();
	}

	/**
	 * 功能：初始化房间、棋盘 作者：林珊珊
	 * */
	public void init() {

		this.setTitle("五子棋");
		this.setLocation(345, 100);
		this.setSize(1000, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// chessimpl.ResetGame();
		this.setVisible(true);
	}

	/**
	 * 功能：跳转至房间列表页面 作者：林珊珊
	 * */
	public void toRoomList() {
		// roomList.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * 输入：鼠标点击 功能：为棋盘设定鼠标监听器 输出：棋盘落子效果
	 * 
	 * @author 林珊珊
	 */
	public static class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {

			int x = event.getX();
			int y = event.getY();

			if (x > 85 && x < 535 && y > 85 && y < 535) {
				ChessTablePanel.paintItem(x, y);
			} else {
				System.out.println("请将棋子放进棋盘内");
			}
			/*
			 * if (map[i][j] == 0) { Ellipse2D ellipse = new Ellipse2D.Double();
			 * ellipse.setFrameFromCenter(centerX, centerY, centerX + 12,
			 * centerY + 12); map[i][j] = 1; System.out.println("(i,j)=" + i +
			 * "," + j); items.add(ellipse); if (Moves > 256) { // 格子放满未分胜负，平局 }
			 * else Moves++; System.out.println("move=" + Moves); } else {
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

			Room.ChessTablePanel.repaint();
			// 判断是否有胜负
		}
	}

	/**
	 * 功能：棋盘面板 作者:林珊珊
	 * */
	public static class ChessTable extends JPanel {
		public boolean isReady = false;
		public static int Moves;// 本局比赛已下的总步数
		public static ArrayList items = new ArrayList();
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

		public ChessTable() {
			super(null);

			this.setBounds(0, 0, BOARD_WIDTH, BOARD_WIDTH);
			// this.setBackground(new Color(255, 164, 85));
			this.addMouseListener(new MouseHandler());
		}

		/**
		 * 输入：监听器所获取的鼠标坐标 功能：为棋盘绘出棋子 输出：无
		 * 
		 * @author 林珊珊
		 * */
		static void paintItem(int x, int y) {// 落子
			int player = 0;
			int X = x / 30;
			int Y = y / 30;
			int centerX = X * 30 + 10;
			int centerY = Y * 30 + 10;
			int i = (x - 90) / 30;
			int j = (y - 90) / 30;
			if (i < 15) {
				chessimpl.add(i, j, 1);
				Ellipse2D ellipse = new Ellipse2D.Double();
				ellipse.setFrameFromCenter(centerX, centerY, centerX + 12,
						centerY + 12);
				 items.add(ellipse);
				if (Moves > 256) {
					// 格子放满未分胜负，平局
				} else
					Moves++;
				System.out.println("(i,j),move=(" + i + "," + j + ")," + Moves);
			} else {
				System.out.println("棋子没添加成功");
			}
		}

		/*
		 * int X = x / 30; int Y = y / 30; int centerX = X * 30 + 9; int centerY
		 * = Y * 30 + 9; int i = (x - 90) / 30; int j = (y - 90) / 30; if
		 * (map[i][j] == 0) { Ellipse2D ellipse = new Ellipse2D.Double();
		 * ellipse.setFrameFromCenter(centerX, centerY, centerX + 12, centerY +
		 * 12); map[i][j] = 1; System.out.println("(i,j)=" + i + "," + j);
		 * items.add(ellipse); if (Moves > 256) { // 格子放满未分胜负，平局 } else Moves++;
		 * System.out.println("move=" + Moves); } else {
		 * System.out.println("这里已经放过棋子了"); } } else {
		 * System.out.println("请将棋子放进棋盘内"); }
		 */

		/**
		 * 功能: 绘制棋盘表格图、重绘已下的棋子
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (isReady()) {
				g.drawImage(new ImageIcon("resource/imag/table_ready.png")
						.getImage(), 45, 45, 570, 570, this);
			} else {
				g.drawImage(
						new ImageIcon("resource/imag/table.png").getImage(),
						45, 45, 570, 570, this);
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.black);
			char ch = 'A';
			g.setFont(new Font("宋体", Font.BOLD, 12));

			/*
			 * 功能：加载单元格间距
			 */

			for (int i = 0, WIDTH = 100; i < draw.length; i++, WIDTH += 30) {
				draw[i] = WIDTH;
			}

			// * 画横线
			for (int i = 0, width = 100 + 20 * 21; i < draw.length; i++, ch++) {
				g.setColor(Color.black);
				g.drawLine(100, draw[i], width, draw[i]);
				g.setColor(Color.blue);
				g.drawString("" + ch, 90, draw[i] + 3);
			}
			// 画竖线
			for (int i = 0, width = 100 + 20 * 21; i < draw.length; i++) {
				g.setColor(Color.black);
				g.drawLine(draw[i], 100, draw[i], width);
				g.setColor(Color.blue);
				g.drawString("" + (i + 1), draw[i] - 3, 95);
			}
			//System.out.println("chessimpl.chess.length="+chessimpl.chess.length);
			for (int i = 0; i <items.size() ; i++) {
				// g2.setColor(Color.black);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.5f));
				Ellipse2D ellipse = (Ellipse2D) items.get(i);
				Ellipse2D ellipse2D = new Ellipse2D.Double();
				ellipse2D.setFrameFromCenter(ellipse.getCenterX() + 1,
						ellipse.getCenterY() - 1, ellipse.getCenterX() + 13,
						ellipse.getCenterY() + 11);

				// 白子
				GradientPaint gp1 = new GradientPaint(
						(float) ellipse.getMinX(), (float) ellipse.getMinY(),
						new Color(174, 173, 171), (float) ellipse.getMaxX(),
						(float) ellipse.getMaxY(), new Color(116, 115, 114));
				GradientPaint gp3 = new GradientPaint(
						(float) ellipse.getMinX(), (float) ellipse.getMinY(),
						Color.white, (float) ellipse.getMaxX(),
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
				if (chessimpl.chess[(int) ((ellipse.getCenterX() - 99) / 30)][(int) ((ellipse
						.getCenterY() - 99) / 30)] == 2) {
					int t1 = (int) ((ellipse.getCenterX() - 99) / 30);
					int t2 = (int) ((ellipse.getCenterY() - 99) / 30);
					// System.out.println("i="+t1+",j="+t2);
					g2.setPaint(gp2);
					g2.fill(ellipse);
					g2.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 1));
					g2.setPaint(gp4);
					g2.fill(ellipse2D);
				} else {// 白子
					g2.setPaint(gp1);
					g2.fill(ellipse);
					g2.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 1));
					g2.setPaint(gp3);
					g2.fill(ellipse2D);
				}

				g2.fill(ellipse2D);
				repaint();
			}
		}

		public boolean isReady() {
			return isReady;
		}

		public void setReady(boolean param) {
			this.isReady = param;
		}

		/**
		 * 输入：监听器所获取的鼠标坐标 功能：为棋盘作悔棋操作 输出：无
		 * 
		 * @author 林珊珊
		 * */
		public void unpaintItem() {// 悔棋传入玩家对象
			int player = 0;
			// if () {输入玩家是左/右玩家进行悔棋 且符合对方同意悔棋
			chessimpl.delete(Room.chess_BLACK);
			//if (chessimpl.chess.length != items.size()) {
				System.out.println("(chessimpl.chess.length,Moves)=("
						+ chessimpl.chess.length + "," + Moves + ")");
				items.remove(0);
				repaint();
			
			//}
			// } else {
			// System.out.println("悔棋异常");
		}

		public ChessImpl getChessimpl() {
			return chessimpl;
		}
	}

	public static void main(String[] args) {
		Room r = new Room();
	}
}