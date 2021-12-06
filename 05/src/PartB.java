import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PartB {
	private static int[] board = new int[1000*1000];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
    
        for (int i = 0; i < board.length; i++) board[i] = 0;
        
        while(sc.hasNextLine()) {
        	String line = sc.nextLine();
        	String[] comp = line.split("\\s+->\\s+");
        	String[] coords1 = comp[0].split(",");
        	String[] coords2 = comp[1].split(","); 
        	
        	drawLine(Integer.parseInt(coords1[0]), Integer.parseInt(coords1[1]),
        			Integer.parseInt(coords2[0]), Integer.parseInt(coords2[1]));
        }
        drawTestBoard();
        
        System.out.println("total overlaps: " + countOverlaps());
	}
	
	public static void drawLine(int x1, int y1, int x2, int y2) {
		if (x1 == x2) {
			int miny = y2 > y1 ? y1 : y2;
			int maxy = y2 > y1 ? y2 : y1;
			for (int i = miny; i <= maxy; i++) board[i * 1000 + x1] += 1;
			return;
		}
		
		if (y1 == y2) {
			int minx = x2 > x1 ? x1 : x2;
			int maxx = x2 > x1 ? x2 : x1;
			for (int i = minx; i <= maxx; i++) board[y1 * 1000 + i] += 1;
			return;
		}
		
		int dx = x2 - x1;
		int dy = y2 - y1;
		
		if (Math.abs(dx) == Math.abs(dy)) {
			dx = dx > 0 ? 1 : -1;
			dy = dy > 0 ? 1 : -1;
			
			boolean drawingLine = true;
			while (drawingLine) {
				board[y1 * 1000 + x1] += 1;
				drawingLine = x1 != x2;
				x1 += dx;
				y1 += dy;
			}
		}
	}
	
	public static int countOverlaps() {
		int overlaps = 0;
		for (int i = 0; i < board.length; i++) if (board[i] > 1) overlaps++;
		return overlaps;
	}
	
	public static void drawTestBoard() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				System.out.print(board[i*1000 + j]);
			}
			System.out.println();
		}
	}
}
