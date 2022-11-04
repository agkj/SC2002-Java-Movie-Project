package MovieGoer;

public abstract class AppInterface {
	    private AppInterface prevApp;

	    public AppInterface(AppInterface prevApp) {
	        this.prevApp = prevApp;
	    }

	    public void runInterface() {}

	    public AppInterface goBack() {
	        return this.prevApp;
	    }
}
