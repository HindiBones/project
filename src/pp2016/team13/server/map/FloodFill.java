package pp2016.team13.server.map;

import java.awt.Point; 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 
 * @author Cigdem
 * @param
 */

public class FloodFill {
	private int size;
	public int[][] map;
	private static final int kDirUp = 0;
	private static final int kDirDown = 1;
	private static final int kDirRight = 2;
	private static final int kDirLeft = 3;
	private static final int kDirFirst = 0;
	private static final int kDirLast = 3;
	private static Point startingPoint = null;
	private static Point destinationPoint = null;
	private static Point currentPosition = null;
	
	//Level Inhalt : 0=Wand, 1 = Boden, 2 = Charakter, 3 = Monster, 4 = Heiltrank, 5=Schluessel, 6 = Tuer,7 = Schutztrank
	/**
	 * @author Cigdem
	 * 
	 */
	public static void main (String[] args){
		FloodFill Labyrinth = new FloodFill (10);
		for (int i=0 ; i<Labyrinth.size; i++)
		{
			for (int j = 0 ; j<Labyrinth.size ; j++){
				System.out.print(Labyrinth.map[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * @author Cigdem
	 * @param size 
	 */

	//Level Inhalt : 0=Wand, 1 = Boden, 2 = Charakter, 3 = Monster, 4 = Trank, 5=Schluessel, 6 = Tuer,7 = Trank2
	
	FloodFill(int size) {
		this.size = size;
		Random random = new Random();
		map = new int[size][size];
	
		floodFill(new Point(size / 2, size / 2), random);
		// finde freie Start- und Zielkoordinaten
		List<Point> free = new ArrayList<Point>();
		for(int x = 0; x < size; x++)
			for(int y = 0; y < size; y++)
				if(map[x][y]==0)
					free.add(new Point(x, y));

		if(free.size() == 0)
			throw new RuntimeException("There are not free fields in the labyrinth");
		
		startingPoint = free.get(random.nextInt(free.size()));
		destinationPoint = free.get(random.nextInt(free.size()));
		currentPosition = startingPoint;
	}
	/**
	 * @author Cigdem
	 * @return
	 */
	public int getMapSize() { return size; }
	public Point getStartingPoint() { return startingPoint; }
	public Point getDestinationPoint() { return destinationPoint; }
	public Point getCurrentPosition() { return currentPosition; }
	public int tileIsBlocked(int x, int y) { return map[x][y]; }
	
	private boolean onMap(Point p) {
		return p.x >= 0 && p.x < size && p.y >= 0 && p.y < size;
	}

	public Point addDir(Point p, int direction) {
		switch(direction) {
		case kDirUp:
			return new Point(p.x, p.y - 1);
		case kDirDown:
			return new Point(p.x, p.y + 1);
		case kDirRight:
			return new Point(p.x + 1, p.y);
		case kDirLeft:
			return new Point(p.x - 1, p.y);
		default:
			throw new IllegalArgumentException("Illegal direction");
		}
	}
	
	private void floodFill(Point point, Random random) {
		int zufall1, zufall2, zufall3, zufall4;
		zufall1 = random.nextInt(4);
		zufall2 = zufall1;
		zufall3 = zufall1;
		zufall4 = zufall1;
		while (zufall1 == zufall2)
		{
			zufall2 = random.nextInt(4);
		}
		while (zufall1 == zufall3 || zufall2 == zufall3)
		{
			zufall3 = random.nextInt(4);
		}
		while (zufall1 == zufall4 || zufall2 == zufall4 || zufall3 == zufall4)
		{
			zufall4 = random.nextInt(4);
		}
		map[point.x][point.y] = 1;

		
		for (int i = 0 ; i < 4 ; i++)
		{
			
			if (i == zufall1 && onMap(new Point (point.x, point.y-2)) && map[point.x][point.y-2]==0 && map[point.x][point.y-1]==0)
			{
				map[point.x][point.y-1] = 1;
				floodFill(new Point (point.x, point.y-2), random);
			}
			if (i == zufall2 &&onMap(new Point (point.x, point.y+2)) && map[point.x][point.y+2] == 0 && map[point.x][point.y+1] == 0)
			{
				map[point.x][point.y+1] = 1;
				floodFill(new Point (point.x, point.y+2), random);
			}
			if(i == zufall3 &&onMap(new Point (point.x-2, point.y))&& map[point.x-2][point.y] == 0 && map[point.x-1][point.y] == 0)
			{
				map[point.x-1][point.y] = 1;
				floodFill(new Point (point.x-2, point.y), random);
			}
			if (i == zufall4 &&onMap(new Point (point.x+2, point.y)) && map[point.x+2][point.y] == 0 && map[point.x+1][point.y] == 0)
			{
				map[point.x+1][point.y] = 1;
				floodFill(new Point (point.x+2, point.y), random);
			}
		}
		
	}
	
}
