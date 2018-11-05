package com.ysh.domain;

import java.awt.Color;

/**
 * ������
 * 
 * @author DouDou
 *
 */
public class Prop {

	private int[] ws = new int[] { 2, 4, 6, -2,-4 };// ÿ��������õ����ӳ���

	private Color color;// ���ߵ���ɫ

	private Dot dot;// ���ߵ����

	private int addWidth;// ���ӳ���

	private PropThread pt;

	public Dot getDot() {
		return dot;
	}

	public void setDot(Dot dot) {
		this.dot = dot;
	}

	public int getAddWidth() {
		return addWidth;
	}

	public void setAddWidth(int addWidth) {
		this.addWidth = addWidth;
	}

	public Prop(Dot dot, Color color) {
		super();
		this.color = color;
		this.dot = dot;
		this.addWidth = ws[(int) (Math.random() * 5)];

		//�������̸߳�ֵ
		this.pt = new PropThread(this);
		//�õ��߶�����
		pt.start();

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Prop() {
		super();
	}

}
