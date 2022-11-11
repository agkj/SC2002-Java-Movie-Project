package Util;

public abstract class AppHelper { //this is not really an interface

	private AppHelper prevApp;

	public AppHelper(AppHelper prevApp) {
		this.prevApp = prevApp;
	}

	public void runInterface() {}

	public AppHelper goBack() {
		return this.prevApp;
	}


}
