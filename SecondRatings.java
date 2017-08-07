
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies("data/" + moviefile);
        myRaters = fr.loadRaters("data/" +ratingsfile);        
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
    	double average = 0;
    	double sum = 0;
    	int countRaters = 0;
    	for(EfficientRater r: myRaters) {
    		if(r.hasRating(id)) {
    			countRaters++;  
    			sum += r.getRating(id);
    		}
    	}
    	if(countRaters >= minimalRaters)
    		average = sum / countRaters;
    	return average;
    }
    
     public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	ArrayList<Rating> ratings = new ArrayList<Rating>();
    	for(Movie m: myMovies) {
    		double averageRating = getAverageByID(m.getID(),minimalRaters);
    		if(averageRating!=0){
    			Rating r = new Rating(m.getID(), averageRating);
    			ratings.add(r);
    		}
    	}
    	return ratings;
    }
    
     public String getTitle(String id) {
    	for(Movie m: myMovies) {
    		if(m.getID().equals(id))
    			return m.getTitle();
    					
    	}
    	return "The movie ID " + id + " is not found.";
    }
    
    public String getID(String title) {
    	for(Movie m: myMovies) {
    		if(m.getTitle().equals(title))
    			return m.getID();
    	}
    	return "NO SUCH TITLE.";
    }

}
