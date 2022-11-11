package Admin;

import Util.AppHelper;
import Util.FileReader;

import java.util.Scanner;

public class ViewtopfiveApp extends AppHelper{
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir") + "\\data\\admin_control\\";
    public ViewtopfiveApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE MOVIE_GOER VIEWING OPTIONS -------\n");

        System.out.println("1) View by Ticket Sales");
        System.out.println("2) View by Overall Reviewers' Rating");
        System.out.println("3) View by Ticket Sales or View by Overall Reviewers' Rating");
        System.out.println("\n0) Return to Previous Menu");

        System.out.println("----------------------------------------");
        System.out.println("Select an option: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                FileReader.replaceLine(root + "control.txt", "Ticket Sales", "Ticket Sales," + "true", false);
                FileReader.replaceLine(root + "control.txt", "Reviewers' Rating", "Reviewers' Rating," + "false", false);
                System.out.println("SYSTEM SETTING - TICKET SALES VIEW SUCCESSFULLY");
                goBack().runInterface();
                break;
            case 2:
                FileReader.replaceLine(root + "control.txt", "Ticket Sales", "Ticket Sales," + "false", false);
                FileReader.replaceLine(root + "control.txt", "Reviewers' Rating", "Reviewers' Rating," + "true", false);
                System.out.println("SYSTEM SETTING - REVIEWERS' RATING VIEW SUCCESSFULLY");
                goBack().runInterface();
                break;
            case 3:
                FileReader.replaceLine(root + "control.txt", "Ticket Sales", "Ticket Sales," + "true", false);
                FileReader.replaceLine(root + "control.txt", "Reviewers' Rating", "Reviewers' Rating," + "true", false);
                System.out.println("SYSTEM SETTING - REVIEWERS' RATING VIEW SUCCESSFULLY");
                goBack().runInterface();
            default:
                break;
        }//todo if the person enter invalid number
    }
}
