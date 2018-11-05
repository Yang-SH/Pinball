package com.ysh.domain;

public class PropThread extends Thread {

	private Prop p;

	private volatile boolean stopFlag = false;

	public PropThread(Prop p) {
		// TODO Auto-generated constructor stub
		this.p = p;
	}

	public boolean isStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(boolean stopFlag) {
		this.stopFlag = stopFlag;
	}

	@Override
	public void run() {
		super.run();

		while (!stopFlag) {
			if (p.getDot().y > 500) {
				interrupt();
			}
			p.getDot().y += 2;
			try {
				sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				break;
			}
		}

	}

}
