package com.ysh.domain;

import java.awt.Color;

import com.ysh.interfaces.OnStrikeListener;

/**
 * ������
 * 
 * @author DouDou
 *
 */
public class Diamonds {
	/**
	 * ����õ���ɫ���飬�ȴ���������ʱ�������
	 */
	private Color[] colors = new Color[] { new Color(209, 73, 78), new Color(62, 188, 202), new Color(148, 41, 33),
			new Color(170, 138, 87), new Color(197, 31, 31), new Color(113, 175, 164), new Color(69, 137, 148),
			new Color(18, 53, 85), new Color(64, 116, 52) };

	private Color color;// �������ɫ��Ĭ��Ϊ��ɫ

	public int x;// �������Ͻǵ�x����
	public int y;// �������Ͻǵ�y����
	public int length;// ����ı߳�

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Diamonds() {

	}

	public Diamonds(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.color = colors[(int) (Math.random() * 9)];

	}

	OnStrikeListener l;// ��ȡ˭ʵ��������ӿ�
	Ball ball;// ��ȡ�����еĵ���

	public void OnStrikeListener(OnStrikeListener l, Ball ball) {
		this.l = l;
		this.ball = ball;
		// this.ball.setX(this.ball.getX() + this.ball.getD() / 2);
		// this.ball.setY(this.ball.getY() - this.ball.getD() / 2 + 30);
		isStike();
	}

	// �ж��Ƿ�����ײ
	public void isStike() {

		new Thread() {

			public void run() {
				while (true) {

					int bj = ball.getD() / 2;
					// Բ��55
					Dot d = new Dot(ball.getX() + bj, ball.getY() + bj);

					Dot topDot = new Dot(ball.getX() + (ball.getD() / 2), ball.getY());
					Dot btmDot = new Dot(ball.getX() + (ball.getD() / 2), ball.getY() + ball.getD());
					Dot leftDot = new Dot(ball.getX(), ball.getY() + (ball.getD() / 2));
					Dot rightDot = new Dot(ball.getX() + ball.getD(), ball.getY() + (ball.getD() / 2));

					// if (d.y <= y + length && d.y >= y) {
					// if (d.x >= x && d.x <= x + length) {
					// System.out.println("��ײ");
					// l.onStrke(Diamonds.this);
					// return;
					// }
					// }

					if (topDot.y <= y + length && topDot.y >= y) {
						if (topDot.x >= x && topDot.x <= x + length) {
							System.out.println("��ײ");
							// ball.setX(ball.getX());
							l.onStrke(Diamonds.this);//����ʵ�ֽӿڵ�onStrke������������ײ
							return;
						}
					}

					if (btmDot.y <= y + length && topDot.y >= y) {
						if (btmDot.x >= x && btmDot.x <= x + length) {
							System.out.println("��ײ");
							l.onStrke(Diamonds.this);
							return;
						}
					}
					if (leftDot.y <= y + length && leftDot.y >= y) {
						if (leftDot.x >= x && leftDot.x <= x + length) {
							System.out.println("��ײ");
							l.onStrke(Diamonds.this);
							return;
						}
					}
					if (rightDot.y <= y + length && rightDot.y >= y) {
						if (rightDot.x >= x && rightDot.x <= x + length) {
							System.out.println("��ײ");
							l.onStrke(Diamonds.this);
							return;
						}
					}

					// if(d.y-length <= 0 && d.x-length<=0){
					// System.out.println("��ײ");
					// l.onStrke(Diamonds.this);
					// return;
					// }

					try {
						Thread.sleep(1000 / 24);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};

		}.start();

	}

	public Diamonds(Color color, int x, int y, int length) {
		super();
		this.color = color;
		this.x = x;
		this.y = y;
		this.length = length;
	}

}
