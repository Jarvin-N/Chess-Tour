package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code chessPiece} class represents different chess pieces used in the game of chess.
 * 
 *<p>
 * The main purpose of the class is to provide the possibles moves each type of chess piece.
 * Currently there is only 3 chess pieces that can be used which are Knight, King & Rook. 
 * 
 */

public class chessPiece {
	/**
	 * Fields that store the {@code chessPiece}'s type and location.
	 */
	private String pieceType;
	private Location pieceLoc;
	
	/**
	 * Initializes a {@code chessPiece} object to the location of {@code pieceLoc} and gives it the type
	 * of piece with {@code pieceType}
	 * 
	 * @param pieceType a string value containing the type of piece that will be used
	 * @param pieceLoc a location value that the piece is currently located at
	 */
	public chessPiece(String pieceType, Location pieceLoc) {
		this.pieceType = pieceType;
		this.pieceLoc = pieceLoc;
	}
	
	/**
	 * Returns the type of chess piece being used.
	 * 
	 * @return the type of chess piece being used
	 */
	public String pType() {
		return this.pieceType;
	}
	
	/**
	 * Returns the current location of the chess piece. 
	 * 
	 * @return the current location of the chess piece
	 */
	public Location Loc() {
		return this.pieceLoc;
	}
	
	/**
	 * Sets the location of the piece to the specified location.
	 * 
	 * @param loc the location to change to
	 */
	public void changeLoc(Location loc) {
		this.pieceLoc = loc;
	}
	
	/**
	 * Returns a list of possible moves that a piece can make from a location {@code pieceLoc} on
	 * a {@code board}.
	 * 
	 * @param pieceLoc contains the location that we are gaining possible moves from
	 * @param board holds the Board object that contains these locations
	 * @return a list of possible moves that a piece can make from a location
	 */
	public List<Location> possibleMoves(Location pieceLoc, Board board){
		List<Location> possibleMovesList = new ArrayList<Location>();
		// if the piece type is Knight, then use these moves
		String pieceType = this.pieceType;
		if(pieceType == "Knight" || pieceType == "knight") {
			int[][] moves = {
					{-2,1}, {-2,-1},
					{-1,2}, {-1,-2},
					{1,2}, {1,-2},
					{2,1}, {2,-1}
			};
			for(int[] i :moves) {
				Location newSpot = new Location(pieceLoc.x() + i[0], pieceLoc.y()+i[1]);
				if(board.isValid(newSpot)) {
					possibleMovesList.add(newSpot);
				}
			}
		}
		// if the piece type is king, then use these moves
		if(pieceType == "King" || pieceType == "king") {
			int[][] moves = {
					{0,1}, {0,-1},
					{1,0}, {-1,0},
					{1,1}, {1,-1},
					{-1,1}, {-1,-1}
					
			};
			for(int[] i:moves) {
				Location newSpot = new Location(pieceLoc.x() + i[0], pieceLoc.y()+i[1]);
				if(board.isValid(newSpot)) {
					possibleMovesList.add(newSpot);
				}
			}
		}
		// if the piece type is rook, then use these moves
		if(pieceType == "rook" || pieceType == "Rook") {
			int[][] moves = {
					{0,1}, {0,-1},
					{1,0}, {-1,0}
			};
			for(int[] i:moves) {
				Location newSpot = new Location(pieceLoc.x() + i[0], pieceLoc.y()+i[1]);
				if(board.isValid(newSpot)) {
					possibleMovesList.add(newSpot);
				}
			}
		}
		return possibleMovesList;
	}	
}
