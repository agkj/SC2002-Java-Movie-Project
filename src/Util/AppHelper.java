package Util;

/**
 * Abstract helper class for various modules to inherit to display their UI interface and allow returning to the previous 'page' the user was on.
 */
public abstract class AppHelper {

	private AppHelper prevApp;

	public AppHelper(AppHelper prevApp) {
		this.prevApp = prevApp;
	}

	/**
	 * Display UI Interface of the current application page
	 */
	public abstract void runInterface();

	/**
	 * Return the previous menu the user was on.
	 * @return Return the previous app 'interface'.
	 */
	public AppHelper goBack() {
		return this.prevApp;
	}


}
