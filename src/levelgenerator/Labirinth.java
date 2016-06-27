package gui;

import java.awt.Point;
import java.util.Random;

public class Labirinth {
		private boolean[][] map;
	private int size;
	private Point startingPoint;
	
	public Labirinth(){
		
		this.floodFill(startingPoint, random);
		
	}
	

	
	Random random = new Random();
	
	private boolean onMap(Point p) {
		return p.x >= 0 && p.x < size && p.y >= 0 && p.y < size;
	}

	private void floodFill(Point point, Random random) {
		int zufall0, zufall1, zufall2, zufall3, zufall4,zufall5,zufall6;
		zufall0 = random.nextInt(7);
		
		zufall1 = zufall0;
		zufall2 = zufall0;
		zufall3 = zufall0;
		zufall4 = zufall0;
		zufall5 = zufall0;
		zufall6 = zufall0;
		while (zufall0 == zufall1)
		{
			zufall1 = random.nextInt(7);
		}
		while (zufall0 == zufall2 || zufall1 == zufall2)
		{
			zufall2 = random.nextInt(7);
		}
		while (zufall0 == zufall3 || zufall1 == zufall3 || zufall2 == zufall3)
		{
			zufall3 = random.nextInt(7);
		}
		while (zufall0 == zufall4 || zufall1 == zufall4 || zufall2 == zufall4 || zufall3 == zufall4)
		{
			zufall4 = random.nextInt(7);
		}
		while (zufall0 == zufall5 || zufall1 == zufall5 || zufall2 == zufall5 || zufall3 == zufall5 || zufall4 == zufall5)
		{
			zufall5 = random.nextInt(7);
		}
		while (zufall0 == zufall6 || zufall1 == zufall6 || zufall2 == zufall6 || zufall3 == zufall6 || zufall4 == zufall6 || zufall5 == zufall6)
		{
			zufall6 = random.nextInt(7);
		}
		System.out.println(zufall0);
		System.out.println(zufall1);
		System.out.println(zufall2);
		System.out.println(zufall3);
		System.out.println(zufall4);
		System.out.println(zufall5);
		System.out.println(zufall6);
		
		map[point.x][point.y] = true;

		
		for (int i = 0 ; i < 7 ; i++)
		{
			
			if (i == zufall1 && onMap(new Point (point.x, point.y-2)) && !map[point.x][point.y-2] && !map[point.x][point.y-1])
			{
				map[point.x][point.y-1] = true;
				floodFill(new Point (point.x, point.y-2), random);
			}
			if (i == zufall2 &&onMap(new Point (point.x, point.y+2)) && !map[point.x][point.y+2] && !map[point.x][point.y+1])
			{
				map[point.x][point.y+1] = true;
				floodFill(new Point (point.x, point.y+2), random);
			}
			if(i == zufall3 &&onMap(new Point (point.x-2, point.y))&& !map[point.x-2][point.y] && !map[point.x-1][point.y])
			{
				map[point.x-1][point.y] = true;
				floodFill(new Point (point.x-2, point.y), random);
			}
			if (i == zufall4 &&onMap(new Point (point.x+2, point.y)) && !map[point.x+2][point.y] && !map[point.x+1][point.y])
			{
				map[point.x+1][point.y] = true;
				floodFill(new Point (point.x+2, point.y), random);
			}
		}
	}
	
	public static void main (String[] args){
		Labirinth L= new Labirinth();
		
		
	} 
}
