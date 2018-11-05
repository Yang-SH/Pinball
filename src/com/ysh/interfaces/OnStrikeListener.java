package com.ysh.interfaces;

import com.ysh.domain.Diamonds;

/**
 * 这个一个回调接口
 * 
 * @author DouDou
 *
 */
public interface OnStrikeListener {

	/**
	 * 发生撞击的回调方法
	 */
	public void onStrke(Diamonds diamonds);

}
