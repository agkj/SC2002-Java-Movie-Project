package Admin;

public abstract class AppInterface { //this is not really an interface

	private AppInterface prevApp;

	public AppInterface(AppInterface prevApp) {
		this.prevApp = prevApp;
	}

	public void runInterface() {}

	public AppInterface goBack() {
		return this.prevApp;
	}


}
