package com.ysh.domain;

import java.awt.Color;

import com.ysh.interfaces.OnStrikeListener;

/**
 * ����
 * 
 * @author DouDou
 *
 */
public class Ball {

	private Color color = Color.red;// �����ɫ��Ĭ��Ϊ��ɫ

	private int x = 30;// ���x����
	private int y = 30;// ���y����
	private int d = 20;// ���ֱ��

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

	// �����Ե�get��set����
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
