package com.ysh.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.ysh.domain.Baffle;
import com.ysh.domain.Ball;
import com.ysh.domain.Diamonds;
import com.ysh.domain.Dot;
import com.ysh.domain.PaintThread;
import com.ysh.domain.Prop;
import com.ysh.interfaces.OnStrikeListener;

/**
 * 弹球的窗体
 * 
 * @author DouDou
 *
 */
public class PinballFrame extends Frame implements OnStrikeListener {

	public Ball ball;// 球

	public Baffle baffle;// 挡板

	public ArrayList<Diamonds> dlist;// 方块的集合

	public PaintThread pT;// 绘图线程

	public boolean isGameOver = false;// 游戏是否结束

	private boolean isDropOut = false;;// 是否掉落道具

	private boolean isClearance = false;// 是否通关

	Prop p;// 道具

	public PinballFrame() throws HeadlessException {
		super();
	}

	public PinballFrame(Ball ball, Baffle baffle) throws HeadlessException {
		super();

		this.pT = new PaintThread(this);

		this.ball = ball;
		this.baffle = baffle;
		this.dlist = new ArrayList<>();

		this.setBounds(700, 300, 550, 500);
		this.setResizable(false);

		createDiamonds();

		java.net.URL imgURL = Frame.class.getResource("/img/title.png");
		ImageIcon imgIcon = new ImageIcon(imgURL);
		Image img = imgIcon.getImage();
		setIconImage(img);

		this.setVisible(true);

		// 创建鼠标的监听即开启鼠标的监听
		this.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				int xx = e.getX();

				try {
					Thread.sleep(5);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				baffle.move(xx);

				repaint();
			}
		});

		// 设置关闭监听
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		pT.start();// 绘图线程启动

	}

	/**
	 * 设置鼠标监听
	 */
	private void setMouseListener() {
		// 创建鼠标的监听即开启鼠标的监听
		this.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				int xx = e.getX();

				try {
					Thread.sleep(5);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				baffle.move(xx);

				repaint();
			}
		});
	}

	private Image iBuffer;
	private Graphics gBuffer;

	/**
	 * 重载paint(Graphics g)函数
	 */
	@Override
	public void paint(Graphics g) {
		System.out.println("paint");

		if (isGameOver) {
			shwoGameOver(g);
		} else if (isClearance) {
			shwoClearance(g);
		} else {
			g.setColor(ball.getColor());
			// 画圆
			g.fillOval(ball.getX(), ball.getY(), ball.getD(), ball.getD());

			// 画挡板
			g.setColor(baffle.getColor());
			if (baffle.getX() >= getWidth()) {
				baffle.setX(getWidth() - baffle.getWidth());
			}
			g.fillRect(baffle.getX(), baffle.getY(), baffle.getWidth(), baffle.getHeight());

			if (dlist != null) {

				// 画方块
				for (Diamonds d : dlist) {
					g.setColor(d.getColor());
					g.fillRect(d.x, d.y, d.length, d.length);
				}
			}

			// 画道具
			if (isDropOut) {
				if (p != null) {
					g.setColor(p.getColor());
					g.fillRect(p.getDot().x, p.getDot().y, 15, 30);
				}

			}

		}

		// g.setColor(Color.white);
		//
		// if (getWidth() - baffle.getX() >= 100) {
		//
		// g.fillRect(0, 350, baffle.getX(), 5);
		// }
		//
		// g.fillRect(baffle.getX() + 100, 350, getWidth() - (baffle.getX() +
		// 100), 5);
	}

	/**
	 * 画出游戏通关的提示
	 * 
	 * @param g
	 */
	private void shwoClearance(Graphics g) {
		// TODO Auto-generated method stub
		Font font = new Font("华文彩云", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(new Color(62, 188, 202));
		g.drawString("游戏已通关", getWidth() / 3, getHeight() - 300);
		g.drawString("感谢游玩本游戏", getWidth() / 4 + 20, getHeight() / 2 + 100);
	}

	/**
	 * 画出游戏结束的提示
	 * 
	 * @param g
	 */
	private void shwoGameOver(Graphics g) {

		Font font = new Font("微软雅黑", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(new Color(197, 31, 31));
		g.drawString("GAME OVER", getWidth() / 3, getHeight() / 2);
		Font font2 = new Font("微软雅黑", Font.BOLD, 20);
		g.setColor(new Color(113, 175, 164));
		g.setFont(font2);
		g.drawString("点击重新开始", getWidth() / 3, getHeight() / 2 - 100);

		// 将球的位置重置
//		ball = new Ball(250, 250, 26);
		ball.setX(250);
		ball.setY(250);

		// 构建一个新的集合
		dlist = new ArrayList<>();

		// 让监听为空，不监听鼠标移动
		// this.addMouseMotionListener(new MouseAdapter() {
		// });

		// 设置弹球窗体鼠标点击的监听
		this.addMouseListener(new MouseAdapter() {

			/**
			 * 单机事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				// 创建一定的数量的方块
				createDiamonds();

				// 把鼠标点击的监听设置为空
				PinballFrame.this.addMouseListener(null);

				// 重新设置鼠标监听
//				setMouseListener();

				// 重新开始游戏
				isGameOver = false;

				pT.setSuspend(false);

			}

		});

	}

	/**
	 * 创造出一定数量的方法
	 */
	private void createDiamonds() {
		for (int i = 0, j = 5; i < 12; i++) {
			Diamonds d;
			if (i % 2 == 0) {
				d = new Diamonds(j, 72, 40);
			} else {
				d = new Diamonds(j, 32, 40);
			}
			d.OnStrikeListener(PinballFrame.this, ball);
			dlist.add(d);
			j = j + 41;
		}
	}

	/**
	 * 这里使用双缓冲计算，解决屏幕闪烁的问题
	 */
	@Override
	public void update(Graphics g) {

		if (iBuffer == null) {
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}

		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);

		paint(gBuffer);

		g.drawImage(iBuffer, 0, 0, this);
	}

	/**
	 * 弹球窗口实现了碰撞监听接口
	 */
	@Override
	public void onStrke(Diamonds diamonds) {
		// 碰撞到了将球反弹
		pT.y = (-pT.y);
		// pT.x = (-pT.x);

		// 获取颜色
		Color color = diamonds.getColor();
		ball.setColor(color);
		baffle.setColor(color);

		int x2 = diamonds.getX();
		int y2 = diamonds.getY();

		// 掉落道具
		isDropOut = true;

		p = new Prop(new Dot(x2, y2), color);

		// 因为掉落道具，所以给挡板加长度
		baffle.setWidth(baffle.getWidth() + p.getAddWidth());

		// 删除碰撞到方块
		if (dlist != null) {

			dlist.remove(diamonds);
		}

		// 如果方块没有了，就是游戏通关了
		if (dlist.size() == 0) {
			isClearance = true;
			pT.setSuspend(true);
		}

		// 将碰撞到方块置空
		diamonds = null;

		// 重绘窗口
		repaint();

	}

	// public static PinballFrame frame;
	// public static ArrayList<Diamonds> list;
	// public static Ball ball1;
	//
	// static int x = 2;
	// static int y = 2;

	// public static void main(String[] args) throws InterruptedException {
	//
	// // 球
	// ball1 = new Ball(300, 150, 30);
	//
	// // 挡板
	// Baffle baffle1 = new Baffle(15, 400, 5, 150);
	//
	// //方块集合
	// list = new ArrayList<>();
	//
	// //新建一个弹球的窗口
	// frame = new PinballFrame(ball1, baffle1, list);
	//
	// //循环产生10个方块
	// Diamonds d;
	// for (int i = 0, j = 0; i < 10; i++) {
	// d = new Diamonds(j, 32, 40);
	// d.OnStrikeListener(frame, ball1);
	// list.add(d);
	// j = j + 41;
	// }
	//
	// frame.setBounds(700, 300, 500, 450);
	//
	// frame.setVisible(true);
	//
	// frame.addWindowListener(new WindowAdapter() {
	//
	// @Override
	// public void windowClosing(WindowEvent e) {
	// System.exit(1);
	// }
	// });
	//
	// // 创建鼠标的监听即开启鼠标的监听
	// frame.addMouseMotionListener(new MouseAdapter() {
	//
	// @Override
	// public void mouseMoved(MouseEvent e) {
	// super.mouseMoved(e);
	// int xx = e.getX();
	//
	// try {
	// Thread.sleep(5);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// baffle1.move(xx);
	//
	// frame.repaint();
	// }
	// });
	//
	// // 用线程让球动起来
	// new Thread() {
	// @Override
	// public void run() {
	// super.run();
	//
	// while (true) {
	// // 判断球是否碰撞到边框
	//
	// if (ball1.getX() <= 0 || ball1.getX() >= frame.getWidth()) {
	// x = (-x);
	// }
	//
	// if (ball1.getY() <= 34) {
	//
	// y = (-y);
	// }
	//
	// if (ball1.getY() >= frame.getHeight() - 32) {
	// // if (ball1.getX() > (baffle1.getX() + 100) ||
	// // ball1.getX() < baffle1.getX()) {
	// // // JOptionPane.showMessageDialog(null, "失败", "提示",
	// // // JOptionPane.CANCEL_OPTION);
	// // } else {
	// // y = (-y);
	// // }
	// y = (-y);
	// }
	//
	// // 移动球
	// ball1.move(x, y);
	//
	// System.out.println(ball1.getX() + "\t" + ball1.getY());
	//
	// //休眠
	// try {
	// Thread.sleep(5);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// // 重绘窗口
	// frame.repaint();
	// }
	// }
	// }.start();
	//
	// }

}
