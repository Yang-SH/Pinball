package com.ysh.start;

import java.util.ArrayList;

import com.ysh.domain.Baffle;
import com.ysh.domain.Ball;
import com.ysh.domain.Diamonds;
import com.ysh.frame.PinballFrame;

/**
 * ��Ϸ������
 * 
 * @author DouDou
 *
 */
public class Start {

	public static void main(String[] args) {

		// ��
		Ball ball1 = new Ball(250, 250, 26);

		// ����
		Baffle baffle1 = new Baffle(15, 480, 5, 100);

		// ���鼯��
//		ArrayList<Diamonds> list = new ArrayList<>();

		// �½�һ������Ĵ���
		PinballFrame frame = new PinballFrame(ball1, baffle1);

		// // ѭ������10������
		// Diamonds d;
		// for (int i = 0, j = 5; i < 12; i++) {
		// if (i % 3 == 0) {
		// d = new Diamonds(j, 72, 40);
		// } else {
		// d = new Diamonds(j, 32, 40);
		// }
		// d.OnStrikeListener(frame, ball1);
		// list.add(d);
		// j = j + 41;
		// }

		// frame.addWindowListener(new WindowAdapter() {
		//
		// @Override
		// public void windowClosing(WindowEvent e) {
		// System.exit(1);
		// }
		// });

		// �������ļ������������ļ���
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

		// // ���߳���������
		// new Thread() {
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
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
		// // ����
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

	}

}
