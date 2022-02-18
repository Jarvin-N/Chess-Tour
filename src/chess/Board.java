package chess;

import java.util.ArrayList;
import java.util.List;

import princeton.introcs.StdDraw;

/**
 * The {@code Board} class creates a Board that matches specified input values such as
 * {@code width}, {@code height}, {@code boardType}.
 * 
 * <p>
 * The main purpose of {@code Board} is to create valid location spots to match the boardType, 
 * and keep track of unvisited locations through the use of Lists.
 * 
 * <p>
 * Currently there is 3 different board that you can do a tour on consisting of square/rectangular,
 * triangular, and irregular.
 *
 */
public class Board {
	/**
	 * Fields that store the {@code Board}'s visited locations, width, height and type of board.
	 */
	private List<Location> unvisitedSpots;
	private int width;
	private int height;
	private String boardType;
	
	/**
	 * Initialize a {@code Board} Object to have the width of {@code width} and height of
	 * {@code height}. Also defines what type of board it will be with {@code boardType}.
	 * 
	 * @param width an integer that holds the width of the board
	 * @param height an integer that holds the height of the board
	 * @param boardType a string which contains the type of board that will be used
	 */
	public Board(int width, int height, String boardType) {
		this.width = width;
		this.height = height;
		this.boardType = boardType;
	}
	
	/**
	 * Sets the board's locations to represent a square board. Also makes all locations
	 * unvisited.
	 */
	public void squareBoard(){
		unvisitedSpots = new ArrayList<Location>();
		for(int i = 1; i < width + 1; i++) {
			for(int z = 1; z < height + 1; z++) {
				Location space = new Location(i,z);
				this.unvisitedSpots.add(space);
			}
		}
	}
	
	/**
	 * Sets the board's locations to represent a right triangle board. Also makes all locations
	 * unvisited.
	 */
	public void rightTriangleBoard() {
		unvisitedSpots = new ArrayList<Location>();
		for(int i = 1; i < width+1; i++) {
			for(int z = 1; z <= i; z++) {
				Location space = new Location(i,z);
				this.unvisitedSpots.add(space);
			}
		}
	}
	
	/**
	 * Sets the board's locations to represent an irregular board. Also makes all locations
	 * unvisited.
	 */
	public void irregularBoard() {
		unvisitedSpots = new ArrayList<Location>();
		for (int i = width; i > 0; i--) {
			for (int j = i; j < height; j++) {
				Location space = new Location(i, j);
				this.unvisitedSpots.add(space);
			}
		}
		for (int i = width; i > 0; i--) {
			for (int j = width; j > height/2; j--) {
				Location space = new Location(i, j);
				this.unvisitedSpots.add(space);
			}
		}
	}
	
	/**
	 * Returns a list of all the unvisited spots on the board.
	 * 
	 * @return a list of all the unvisited spots on the board
	 */
	public List<Location> unvisitedSpots(){
		return this.unvisitedSpots;
	}
	
	/**
	 * Removes a specified location {@code loc} from the list of unvisited spots to signify 
	 * that it has been visited.
	 * 
	 * @param loc the location that has been visited
	 */
	public void visitedLocation(Location loc) {
		for(int i = 0; i < this.unvisitedSpots.size(); i++) {
			if(this.unvisitedSpots.get(i).equals(loc)) {
				this.unvisitedSpots.remove(i);
			}
		}
	}
	
	/**
	 * Returns {@code true} if the specified location {@code spot} is a valid location 
	 * on the {@code Board} and if it is unvisited otherwise returns {@code false}.
	 * 
	 * @param spot the location that is being tested for validity
	 * @return {@code true} if the specified location is valid on the {@code Board} 
	 * 		    and if it is unvisited otherwise returns {@code false}
	 */
	public boolean isValid(Location spot) {
		// check whether it is inside the board
		if (spot.x()<1 || spot.x() > this.width || spot.y() < 1 || spot.y() > this.height) {
			return false;
		}
		// check whether it has not been visited
		for(int i = 0; i < this.unvisitedSpots.size(); i++) {
			if(this.unvisitedSpots.get(i).equals(spot)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the value of {@code boardType}.
	 * 
	 * @return the value of {@code boardType}
	 */
	public String boardType() {
		return this.boardType;
	}	
}
