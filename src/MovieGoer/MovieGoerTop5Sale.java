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

public class MovieGoerTop5Sale extends MovieListingApp{
    
    public MovieGoerTop5Sale(AppInterface prevApp) {
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
			Map<String, Integer> mapOfMovies = new HashMap<>();
            if(movieFiles != null) {
            	
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    mapOfMovies.put(curr.getTitle(), curr.getTicketsSold());
                    

                    
                }
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(mapOfMovies.entrySet());
            
            
            Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {
            	
            	
            	
            	public int compare (Map.Entry<String, Integer> e1, Map.Entry<String,Integer> e2) {
            		
            		return Integer.compare(e1.getValue(), e2.getValue());
            	}
            	
            	
			});
            
            
            System.out.println("*********Here are the top 5 movies by ticket sales!*********");
            for(Map.Entry<String, Integer> e:list) {
            	System.out.println(e.getKey() +" Tickets sold: "+ e.getValue());
            }
            System.out.println("*********************************************");
            
           
            
            

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
	}
}