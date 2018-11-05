package com.ysh.domain;

import com.ysh.frame.PinballFrame;

/**
 * 绘图线程类
 * 
 * @author DouDou
 *
 */
public class PaintThread extends Thread {

	PinballFrame pf;// 弹球窗口

	public static int x = 2;// 球在x 轴的偏移量
	public static int y = 3;// 球在y 轴的偏移量

	private boolean suspend = false;// 线程暂停标识

	private String control = ""; // 只是需要一个对象而已，这个对象没有实际意义

	/**
	 * 设置当前线程暂停/ 等待
	 * 
	 * @param suspend
	 */
	public void setSuspend(boolean suspend) {
		if (!suspend) {
			synchronized (control) {
				System.out.println("启动");
				control.notifyAll();
			}
		}
		this.suspend = suspend;
	}

	/**
	 * 返回是否暂停
	 * 
	 * @return
	 */
	public boolean isSuspend() {
		return this.suspend;
	}

	/**
	 * 绘图线程类的构造方法
	 * 
	 * @param pf
	 */
	public PaintThread(PinballFrame pf) {
		this.pf = pf;
	}

	@Override
	public void run() {

		while (true) {

			synchronized (control) {
				if (suspend) {
					// 将线程挂起
					try {
						control.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

			// 判断球是否碰撞到边框
			if (pf.ball.getX() <= 0 || pf.ball.getX() >= (pf.getWidth() - pf.ball.getD())) {
				x = (-x);
			}

			if (pf.ball.getY() <= 34) {

				y = (-y);
			}

			// pf.baffle.getY() - pf.ball.getD()
			if (pf.ball.getY() >= (pf.baffle.getY() - pf.ball.getD())) {

				if (pf.ball.getX() > (pf.baffle.getX()) && pf.ball.getX() < pf.baffle.getX() + pf.baffle.getWidth()) {
					y = (-y);
				} else {
					pf.isGameOver = true;
					// 设置暂停
					setSuspend(true);
				}
//				 y = (-y);
			}

			// 移动球
			pf.ball.move(x, y);

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 重绘窗口
			pf.repaint();

		}

	}

}
