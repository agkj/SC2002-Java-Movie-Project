package Admin;

import Util.AppHelper;
import Util.FileReader;

import java.util.Scanner;

public class LoginApp extends AppHelper {

    StringBuffer userStrings = FileReader.copyFile(System.getProperty("user.dir") + "\\data\\admin\\users.txt");
    String[] users = userStrings.toString().split("\n");;

    Scanner sc = new Scanner(System.in);

    public LoginApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
      //  System.out.println("--------------");
        System.out.println("Enter Username: ");
        String inputUsername = sc.next();
        System.out.println("Enter Password: ");
        String inputPassword = sc.next();

        boolean usernameFlag = false;
        boolean passwordFlag = false;

        for(int i=0; i < users.length; i++) {
            String[] currUser = users[i].split(",");
            String username = currUser[0].trim();
            String password = currUser[1].trim();

            if(inputUsername.equals(username) && inputPassword.equals(password)) {
                usernameFlag = true;
                passwordFlag = true;
                break;
            }
        }

       // System.out.println("------------------------");

        if(usernameFlag && passwordFlag){
            AdminApp adminApp = new AdminApp(null);
            adminApp.runInterface();
        } else {
            System.out.println("Incorrect username or password, please try again");
        }
    }
}
