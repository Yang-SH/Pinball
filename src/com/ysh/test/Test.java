package com.ysh.test;

import java.awt.Component;

import javax.swing.JFrame;

import com.ysh.domain.Baffle;
import com.ysh.domain.Ball;

public class Test {

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		jf.setBounds(700, 300, 400, 450);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		Ball b = new Ball(15, 100);
//		Component add = jf.add(b);
//
//		Baffle bfe = new Baffle(15, 400, 100, 5);
//		jf.add(bfe);

		jf.setVisible(true);

	}

}
