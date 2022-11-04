package MovieGoer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Admin.AppInterface;
import Admin.MovieListingApp;
import Entities.Movie;
import Util.Serializer;

public class MovieGoerTop5 extends MovieListingApp {
	
	
	public MovieGoerTop5(AppInterface prevApp) {
		super(prevApp);
		// TODO Auto-generated constructor stub
	}
	
	public void load() {
        // Try to read all movie .dat files in movie directory
        path = new File(System.getProperty("user.dir") + "\\data\\movies");

        // Store all movie .dat files
        movieFiles = path.listFiles();
    }

	
	public void movieSort() {
		
		try {
            // Read all available Movies
			Map<String, Double> mapOfMovies = new HashMap<>();
            if(movieFiles != null) {
            	
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    mapOfMovies.put(curr.getTitle(), curr.getOverallRating());
                    

                    
                }
            }
            List<Map.Entry<String, Double>> list = new ArrayList<>(mapOfMovies.entrySet());
            
            
            Collections.sort(list,new Comparator<Map.Entry<String, Double>>() {
            	
            	
            	
            	public int compare (Map.Entry<String, Double> e1, Map.Entry<String,Double> e2) {
            		
            		return Double.compare(e1.getValue(), e2.getValue());
            	}
            	
            	
			});
            
            
            System.out.println("*********Here are the top 5 movies!*********");
            for(Map.Entry<String, Double> e:list) {
            	System.out.println(e.getKey() +" Rating: "+ e.getValue());
            }
            System.out.println("*********Here are the top 5 movies!*********");
            
           
            
            

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
//		try {
//            // Read all available Movies
//            if(movieFiles != null) {
//            	
//                for(int i=0; i < movieFiles.length; i++) {
//                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
//                    System.out.println((i+1) + ") " + curr.getTitle());
//                    System.out.println("  Overall Ratings: " + curr.getOverallRating());
//                   
//                    
//                }
//            }
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//		
//		
//		
//		
//		
//		
//		
//	}
	
	

}
