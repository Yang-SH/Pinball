package com.ysh.domain;

import java.awt.Color;

/**
 * 挡板类
 * 
 * @author DouDou
 *
 */
public class Baffle {

	private Color color = Color.BLACK;// 挡板的颜色，默认为黑色

	private int x = 30;// 挡板的x坐标
	private int y = 30;// 挡板的y坐标
	private int height;// 挡板的高
	private int width;// 挡板的宽

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
		System.out.println("移动");
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
