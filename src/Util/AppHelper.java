package Util;

/**
 * Abstract helper class for various modules to inherit to display their UI interface and allow returning to the previous 'page' the user was on.
 */
public abstract class AppHelper {

	private AppHelper prevApp;

	public AppHelper(AppHelper prevApp) {
		this.prevApp = prevApp;
	}

	public abstract void runInterface();

	public AppHelper goBack() {
		return this.prevApp;
	}


}
