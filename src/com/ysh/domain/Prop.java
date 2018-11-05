package com.ysh.domain;

import java.awt.Color;

/**
 * 道具类
 * 
 * @author DouDou
 *
 */
public class Prop {

	private int[] ws = new int[] { 2, 4, 6, -2,-4 };// 每次随机调用的增加长度

	private Color color;// 道具掉颜色

	private Dot dot;// 道具掉落点

	private int addWidth;// 增加长度

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

		//给道具线程赋值
		this.pt = new PropThread(this);
		//让道具动起来
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
