package Admin;

import Util.AppHelper;
import Util.FileReader;

import java.util.Scanner;

/**
 * [Admin Module] Top-Five App to list top 5 movie listings based on filter (i.e., ticket sales, review ratings).
 */
public class TopFiveApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir") + "\\data\\admin_control\\";
    public TopFiveApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE MOVIE_GOER VIEWING OPTIONS -------\n");
        System.out.print("Current setting in MovieGoer: ");
        //look at the admin control
        StringBuffer optionView = FileReader.copyFile(root + "control.txt");
        String[] optionStrings = optionView.toString().split("\n");
        int set_control_ticket = 0;
        int set_control_rating = 0;

        for(int i=0; i < optionStrings.length; i++) {
            String[] currLine = optionStrings[i].split(",");
            String option = currLine[0];
            String optionBoolean = currLine[1];

            if(option.equals("Ticket Sales") && optionBoolean.equals("true")){
                set_control_ticket = 1;

            }else if(option.equals("Reviewers' Rating") && optionBoolean.equals("true")){
                set_control_rating = 1;
            }
        }

        if( set_control_ticket == 1 && set_control_rating == 1 ){
            System.out.println("View by Ticket Sales and Overall Reviewers' Rating");
        } else if (set_control_ticket == 1 && set_control_rating == 0 ){
            System.out.println("View by Ticket Sales ONLY");
        } else if (set_control_ticket == 0 && set_control_rating == 1){
            System.out.println("View by Overall Reviewers' Rating ONLY");
        }
        System.out.println();
        System.out.println("MovieGoer viewing options:");
        System.out.println("1) View by Ticket Sales ONLY");
        System.out.println("2) View by Overall Reviewers' Rating ONLY");
        System.out.println("3) View by Ticket Sales and Overall Reviewers' Rating");
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
                System.out.println("SYSTEM SETTING - TICKET SALES & REVIEWERS' RATING VIEW SUCCESSFULLY");
                goBack().runInterface();
            default:
                break;
        }//todo if the person enter invalid number
    }
}
