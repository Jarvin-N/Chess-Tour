package chess;

import princeton.introcs.StdDraw;

public class TourViewer2 {

	/**
	 * Draws a right angle triangle shape chess board of the specified size.
	 * 
	 * <p>
	 * Students will need to modify this method to draw irregular boards
	 * if their solutions allows for irregular boards. 
	 * 
	 * @param width the number of squares in the width of the board
	 * @param height the number of squares in the height of the board
	 */
	private static void drawBoard(int width, int height) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		int max = Math.max(width, height);
		StdDraw.setScale(0.5, max + 0.5);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j <= i; j++) {
				if ((i + j) % 2 == 0) {
					StdDraw.setPenColor(StdDraw.YELLOW);
				} else {
					StdDraw.setPenColor(StdDraw.BLUE);
				}
				StdDraw.filledSquare(i + 1, j + 1, 0.5);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int Width = 15;
		int Height = 15;
		drawBoard(Width, Height);
		StdDraw.setPenColor(StdDraw.BLACK);
		
		Location start = new Location(1,1);
		String chessPiece = "King";
		String boardType = "rightTriangle";
		Tour t = new Tour(chessPiece, Width, Height, boardType);
		
		t.startTour(start);
		Location curr = start;
		int i = 0;
		while (t.hasNext()) {
			Location next = t.next();
			System.out.println(i + " : moving from " + curr + " to " + next);
			StdDraw.line(curr.x(), curr.y(), next.x(), next.y());
			StdDraw.filledCircle(next.x(), next.y(), 0.1);
			curr = new Location(next);
			// uncomment the next line to slow down the viewer; 500 is the pause time in milliseconds
			//Thread.sleep(2000);
			i++;
		}
		
	}
}
