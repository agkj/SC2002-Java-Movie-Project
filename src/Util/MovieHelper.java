package Util;

import Entities.Movie;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Helper class to display top 5 movie listings based on Ticket Sales or Review Ratings
 */

public class MovieHelper {
    File path;
    File[] movieFiles;

    // Constructor
    public MovieHelper() {
        // Try to read all movie .dat files in movie directory
        path = new File(System.getProperty("user.dir") + "\\data\\movies");

        // Store all movie .dat files
        movieFiles = path.listFiles();
    }

    /**
     * Get Top 5 Movie Listings
     * @param type Indicates the filter type to list top 5 movies (i.e., by ticket sales, by review ratings).
     */
    // Get Top 5 by Ticket Sales
    public void getTopListings(int type) {
        try {
            // Read all available Movies

        	int setter = 0;
            Map<String, Double> mapOfMovies = new HashMap<>();
            if(movieFiles != null) {

                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());

                    if(type == 1) {
                        // By ticket sales
                        mapOfMovies.put(curr.getTitle(), curr.getTotalSales());
                    } else if (type == 2) {
                        // By review ratings
                    	if(curr.getReviews().size()>1) {
                    		mapOfMovies.put(curr.getTitle(), curr.getOverallRating());
                    		setter = 1;
                    	}
                    	if(curr.getReviews().size()==0) {
                    		setter = 0;
                    	}
                    	
                        
                    }

                }
            }
            List<Map.Entry<String, Double>> list = new ArrayList<>(mapOfMovies.entrySet());

            Collections.sort(list,new Comparator<Map.Entry<String, Double>>() {
                public int compare (Map.Entry<String, Double> e2, Map.Entry<String,Double> e1) {

                    return Double.compare(e1.getValue(), e2.getValue());
                }
            });

            switch(type) {
                case 1:
                	System.out.println("|--------------------------------------------------------|");
                    System.out.println("| \t\t TOP 5 MOVIES BY TICKET SALES \t\t |");
                    System.out.println("|--------------------------------------------------------|");
                    /*
                     * for(Map.Entry<String, Double> e:list) { //TODO the top 5 only
                        System.out.println(e.getKey() + " - Total Sales: $" + e.getValue());     // Movie Title - Total Sales: $xx
                    }
                    */
                    int a = 1;
                    for(Map.Entry<String, Double> e:list) { //TODO the top 5 only
                        System.out.println(e.getKey() + " - Total Sales: $" + e.getValue());	// Movie Title - Total Sales: $xx
                        a++;
                        if(a > 5) break;
                        
                    }

                    break;
                case 2:
                	System.out.println("|--------------------------------------------------------|");
                    System.out.println("|\t\t TOP 5 MOVIES BY REVIEW RATINGS \t\t\t |");
                    System.out.println("|--------------------------------------------------------|");
                    /*
                    for(Map.Entry<String, Double> e:list) {//TODO the top 5 only
                        System.out.println(e.getKey() + " - Rating: " + e.getValue());
                    }
                    */
                    
                    if(setter ==0) {
                    	//System.out.println("NA");
                    }
                    
                    
                    
                    int b = 1;
                    for(Map.Entry<String, Double> e:list) {//TODO the top 5 only
                        System.out.println(e.getKey() + " - Rating: " + e.getValue());
                        b++;
                        if(b > 5) break;
                    }
                    break;
                default:
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
