package com.ysh.domain;

import java.awt.Color;

import com.ysh.interfaces.OnStrikeListener;

/**
 * 球类
 * 
 * @author DouDou
 *
 */
public class Ball {

	private Color color = Color.red;// 球的颜色，默认为黑色

	private int x = 30;// 球的x坐标
	private int y = 30;// 球的y坐标
	private int d = 20;// 球的直径

	public Ball(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	// 对属性的get和set方法
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

	public Ball(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void OnStrikeListener(OnStrikeListener l, Ball ball) {
//		this.l = l;
//		this.ball = ball;
		isStike();
	}

	private void isStike() {
		
	}
	
	
	public void bounce(){
		
	}

}
