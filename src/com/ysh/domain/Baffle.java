package com.ysh.domain;

import java.awt.Color;

/**
 * ������
 * 
 * @author DouDou
 *
 */
public class Baffle {

	private Color color = Color.BLACK;// �������ɫ��Ĭ��Ϊ��ɫ

	private int x = 30;// �����x����
	private int y = 30;// �����y����
	private int height;// ����ĸ�
	private int width;// ����Ŀ�

	public Baffle() {
		super();
	}

	public Baffle(Color color, int x) {
		super();
		this.color = color;
		this.x = x;
	}

	public Baffle(int x) {
		super();
		this.x = x;
	}

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

	public void move() {
		System.out.println("�ƶ�");
	}

	public void move(int xx) {
		this.x = xx - getWidth()/2;
	}

	public Baffle(Color color, int x, int y, int height, int width) {
		super();
		this.color = color;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Baffle(int x, int y, int height, int width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
