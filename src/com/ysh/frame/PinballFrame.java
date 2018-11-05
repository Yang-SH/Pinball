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
 * ����Ĵ���
 * 
 * @author DouDou
 *
 */
public class PinballFrame extends Frame implements OnStrikeListener {

	public Ball ball;// ��

	public Baffle baffle;// ����

	public ArrayList<Diamonds> dlist;// ����ļ���

	public PaintThread pT;// ��ͼ�߳�

	public boolean isGameOver = false;// ��Ϸ�Ƿ����

	private boolean isDropOut = false;;// �Ƿ�������

	private boolean isClearance = false;// �Ƿ�ͨ��

	Prop p;// ����

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

		// �������ļ������������ļ���
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

		// ���ùرռ���
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		pT.start();// ��ͼ�߳�����

	}

	/**
	 * ����������
	 */
	private void setMouseListener() {
		// �������ļ������������ļ���
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
	 * ����paint(Graphics g)����
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
			// ��Բ
			g.fillOval(ball.getX(), ball.getY(), ball.getD(), ball.getD());

			// ������
			g.setColor(baffle.getColor());
			if (baffle.getX() >= getWidth()) {
				baffle.setX(getWidth() - baffle.getWidth());
			}
			g.fillRect(baffle.getX(), baffle.getY(), baffle.getWidth(), baffle.getHeight());

			if (dlist != null) {

				// ������
				for (Diamonds d : dlist) {
					g.setColor(d.getColor());
					g.fillRect(d.x, d.y, d.length, d.length);
				}
			}

			// ������
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
	 * ������Ϸͨ�ص���ʾ
	 * 
	 * @param g
	 */
	private void shwoClearance(Graphics g) {
		// TODO Auto-generated method stub
		Font font = new Font("���Ĳ���", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(new Color(62, 188, 202));
		g.drawString("��Ϸ��ͨ��", getWidth() / 3, getHeight() - 300);
		g.drawString("��л���汾��Ϸ", getWidth() / 4 + 20, getHeight() / 2 + 100);
	}

	/**
	 * ������Ϸ��������ʾ
	 * 
	 * @param g
	 */
	private void shwoGameOver(Graphics g) {

		Font font = new Font("΢���ź�", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(new Color(197, 31, 31));
		g.drawString("GAME OVER", getWidth() / 3, getHeight() / 2);
		Font font2 = new Font("΢���ź�", Font.BOLD, 20);
		g.setColor(new Color(113, 175, 164));
		g.setFont(font2);
		g.drawString("������¿�ʼ", getWidth() / 3, getHeight() / 2 - 100);

		// �����λ������
//		ball = new Ball(250, 250, 26);
		ball.setX(250);
		ball.setY(250);

		// ����һ���µļ���
		dlist = new ArrayList<>();

		// �ü���Ϊ�գ�����������ƶ�
		// this.addMouseMotionListener(new MouseAdapter() {
		// });

		// ���õ�����������ļ���
		this.addMouseListener(new MouseAdapter() {

			/**
			 * �����¼�
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				// ����һ���������ķ���
				createDiamonds();

				// ��������ļ�������Ϊ��
				PinballFrame.this.addMouseListener(null);

				// ��������������
//				setMouseListener();

				// ���¿�ʼ��Ϸ
				isGameOver = false;

				pT.setSuspend(false);

			}

		});

	}

	/**
	 * �����һ�������ķ���
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
	 * ����ʹ��˫������㣬�����Ļ��˸������
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
	 * ���򴰿�ʵ������ײ�����ӿ�
	 */
	@Override
	public void onStrke(Diamonds diamonds) {
		// ��ײ���˽��򷴵�
		pT.y = (-pT.y);
		// pT.x = (-pT.x);

		// ��ȡ��ɫ
		Color color = diamonds.getColor();
		ball.setColor(color);
		baffle.setColor(color);

		int x2 = diamonds.getX();
		int y2 = diamonds.getY();

		// �������
		isDropOut = true;

		p = new Prop(new Dot(x2, y2), color);

		// ��Ϊ������ߣ����Ը�����ӳ���
		baffle.setWidth(baffle.getWidth() + p.getAddWidth());

		// ɾ����ײ������
		if (dlist != null) {

			dlist.remove(diamonds);
		}

		// �������û���ˣ�������Ϸͨ����
		if (dlist.size() == 0) {
			isClearance = true;
			pT.setSuspend(true);
		}

		// ����ײ�������ÿ�
		diamonds = null;

		// �ػ洰��
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
	// // ��
	// ball1 = new Ball(300, 150, 30);
	//
	// // ����
	// Baffle baffle1 = new Baffle(15, 400, 5, 150);
	//
	// //���鼯��
	// list = new ArrayList<>();
	//
	// //�½�һ������Ĵ���
	// frame = new PinballFrame(ball1, baffle1, list);
	//
	// //ѭ������10������
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
	// // �������ļ������������ļ���
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
	// // ���߳���������
	// new Thread() {
	// @Override
	// public void run() {
	// super.run();
	//
	// while (true) {
	// // �ж����Ƿ���ײ���߿�
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
	// // // JOptionPane.showMessageDialog(null, "ʧ��", "��ʾ",
	// // // JOptionPane.CANCEL_OPTION);
	// // } else {
	// // y = (-y);
	// // }
	// y = (-y);
	// }
	//
	// // �ƶ���
	// ball1.move(x, y);
	//
	// System.out.println(ball1.getX() + "\t" + ball1.getY());
	//
	// //����
	// try {
	// Thread.sleep(5);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// // �ػ洰��
	// frame.repaint();
	// }
	// }
	// }.start();
	//
	// }

}
