package Util;

import Entities.Movie;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    // Get Top 5 by Ticket Sales
    public void getTopListings(int type) {
        try {
            // Read all available Movies
            Map<String, Double> mapOfMovies = new HashMap<>();
            if(movieFiles != null) {

                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());

                    if(type == 1) {
                        // By tickets sold
                        mapOfMovies.put(curr.getTitle(), curr.getTotalSales());
                    } else if (type == 2) {
                        // By review ratings
                        mapOfMovies.put(curr.getTitle(), curr.getOverallRating());
                    }

                }
            }
            List<Map.Entry<String, Double>> list = new ArrayList<>(mapOfMovies.entrySet());

            Collections.sort(list,new Comparator<Map.Entry<String, Double>>() {
                public int compare (Map.Entry<String, Double> e1, Map.Entry<String,Double> e2) {

                    return Double.compare(e1.getValue(), e2.getValue());
                }
            });

            switch(type) {
                case 1:
                    System.out.println("------- TOP 5 MOVIES BY TICKET SALES -------\n");
                    for(Map.Entry<String, Double> e:list) {
                        System.out.println(e.getKey() + " - Total Sales: $" + e.getValue());     // Movie Title - Total Sales: $xx
                    }

                    break;
                case 2:
                    System.out.println("------- TOP 5 MOVIES BY REVIEW RATINGS -------\n");
                    for(Map.Entry<String, Double> e:list) {
                        System.out.println(e.getKey() + " - Rating: " + e.getValue());
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
