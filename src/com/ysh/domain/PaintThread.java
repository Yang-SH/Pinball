package com.ysh.domain;

import com.ysh.frame.PinballFrame;

/**
 * ��ͼ�߳���
 * 
 * @author DouDou
 *
 */
public class PaintThread extends Thread {

	PinballFrame pf;// ���򴰿�

	public static int x = 2;// ����x ���ƫ����
	public static int y = 3;// ����y ���ƫ����

	private boolean suspend = false;// �߳���ͣ��ʶ

	private String control = ""; // ֻ����Ҫһ��������ѣ��������û��ʵ������

	/**
	 * ���õ�ǰ�߳���ͣ/ �ȴ�
	 * 
	 * @param suspend
	 */
	public void setSuspend(boolean suspend) {
		if (!suspend) {
			synchronized (control) {
				System.out.println("����");
				control.notifyAll();
			}
		}
		this.suspend = suspend;
	}

	/**
	 * �����Ƿ���ͣ
	 * 
	 * @return
	 */
	public boolean isSuspend() {
		return this.suspend;
	}

	/**
	 * ��ͼ�߳���Ĺ��췽��
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
					// ���̹߳���
					try {
						control.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}

			// �ж����Ƿ���ײ���߿�
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
					// ������ͣ
					setSuspend(true);
				}
//				 y = (-y);
			}

			// �ƶ���
			pf.ball.move(x, y);

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// �ػ洰��
			pf.repaint();

		}

	}

}
