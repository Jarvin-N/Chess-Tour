package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class {@code Tour} models a tour on a specified chess board using different chess pieces.
 * 
 * <p> 
 * The tour starts off at a specified location on the board and moves along the 
 * board following the move-set attached to a certain piece. The tour ends if there are no
 * more valid spaces the piece can move to.
 * 
 * <p>
 * Warnsdorff's rule is used to dictate which location the piece should move every time.
 * 
 */
public class Tour {
	/**
	 * Fields that store the tour's specific board and the piece being used.
	 */
	private Board board;
	private chessPiece piece;
	
	/**
	 * Initializes a tour depending on the passed parameters.
	 * 
	 * <p>
	 * The {@code board} field holds a new Board object which uses the inputed {@code Width}, 
	 * {@code Height}, and {@code boardType}, this is where the tour will take place. 
	 * The {@code piece} field holds a new chessPiece object that will dictate what spaces the 
	 * tour can take while being completed.
	 * 
	 * @param pieceType a string that holds the chess piece's type
	 * @param Width an int value containing the width of the board
	 * @param Height an int value containing the height of the board
	 * @param boardType a string that holds the type of board layout to be used
	 */
	public Tour(String pieceType, int Width, int Height, String boardType) {
		this.board = new Board(Width, Height, boardType);
		this.piece = new chessPiece(pieceType, null);
	}
	
	/**
	 * Begins a Tour on a new board at the provided location.
	 * 
	 * <p>
	 * Marks all the locations on a board unvisited depending on the type of board,
	 * and moves the location of piece to {@code loc}. Also marks {@code loc} as a visited
	 * location.
	 * 
	 * @param loc a location that contains the starting position of the tour
	 */
	public void startTour(Location loc) {
		// initialize the location of the chess piece
		this.piece.changeLoc(loc);
		// making all the locations of the board unvisited depending on board type
		if(this.board.boardType().equalsIgnoreCase("square")) {
			this.board.squareBoard();
		}
		if(this.board.boardType().equalsIgnoreCase("rectangle")) {
			this.board.squareBoard();
		}
		if(this.board.boardType().equalsIgnoreCase("rightTriangle")) {
			this.board.rightTriangleBoard();
		}
		if(this.board.boardType().equalsIgnoreCase("irregular")){
			this.board.irregularBoard();
		}
		// making the starting point a visited location
		this.board.visitedLocation(this.piece.Loc());
	}
	
	/**
	 * Returns {@code true} if there is another move in the tour, and {@code false} otherwise.
	 * 
	 * @return {@code true} if there is another move in the tour,
	 * 		   {@code false} if there is no more moves in the tour
	 */
	public boolean hasNext() {
		if(this.board.unvisitedSpots().isEmpty()) {
			return false;
		}
		Location pieceLocation = this.piece.Loc();
		List<Location> possibleSpaces = new ArrayList<Location>();
		possibleSpaces = this.piece.possibleMoves(pieceLocation, this.board);
		if(possibleSpaces.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
			
	}

	/**
	 * Returns a map that contains the number of possible moves a piece can make after moving to a 
	 * specific location. The key of the map being the number of possible moves and the value being
	 * the corresponding location.
	 * 
	 * <p>
	 * Following the process of the Warnsdroff's Rule first a list of unvisited locations that the 
	 * {@code piece} can go to in 1 move is found(L), then for each location in the list, the number
	 * of unvisited spaces that can be made from L are counted N. Then the N value is placed as the key 
	 * in the map with the corresponding location L as the value.
	 * 
	 * @return a map containing the number of possible moves a piece can make after moving to a 
	 * 			specific location
	 */
	public Map<Integer, Location> sizeCounter(){
		Location pieceLocation = this.piece.Loc();
		Map<Integer, Location> possibleSpacesMap = new HashMap<Integer, Location>();
		List<Location> possibleSpaces = new ArrayList<Location>();
		possibleSpaces = this.piece.possibleMoves(pieceLocation, this.board);
		for(int i = 0; i < possibleSpaces.size(); i++) {
			Location nextSpace = possibleSpaces.get(i);
			List<Location> possibleSpaces2 = new ArrayList<Location>();
			possibleSpaces2 = this.piece.possibleMoves(nextSpace, this.board);
			possibleSpacesMap.put(possibleSpaces2.size(), nextSpace);
		}
		System.out.println(possibleSpacesMap.toString());
		return possibleSpacesMap;
	}
	
	/**
	 * Returns the location of the next move of the tour and moves the {@code piece} to that
	 * location. Returns {@code null} if the {@code piece} cannot move to an unvisited location
	 * from its current location.
	 * 
	 * @return the location of the next move of the tour, {@code null} if there is no possible 
	 * 		   locations to move to from its current location.
	 */
	public Location next() {
		Map<Integer, Location> possibleSpacesMap = new HashMap<Integer, Location>();
		possibleSpacesMap = sizeCounter();
		if((hasNext() == false)) {
			return null;
		}
		else {
			int lowestKey = 8;
			for(Integer key : possibleSpacesMap.keySet()) {
				if(key <= lowestKey) {
					lowestKey = key;
				}
			}
			Location nextSpace = new Location(possibleSpacesMap.get(lowestKey));
			// remove nextSpace from unvisited spots
			for(int i=0; i < this.board.unvisitedSpots().size(); i++) {
				if(this.board.unvisitedSpots().get(i).equals(nextSpace)) {
					this.board.unvisitedSpots().remove(i);
				}
			}
			this.piece.changeLoc(nextSpace);
			return nextSpace;
		}
	}	
}
