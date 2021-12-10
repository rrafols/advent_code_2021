import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class PartB {
	private static int[] map;
	private static int width;
    private static int height;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
            
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        ArrayList<String> lines = new ArrayList<>(); 
        while(sc.hasNextLine()) lines.add(sc.nextLine());
        
        width = lines.get(1).length();
        height = lines.size();
        map = new int[width * height];
        for (int i = 0; i < lines.size(); i++) {
        	for (int j = 0; j < lines.get(i).length(); j++) {
        		map[i * width + j] = lines.get(i).toCharArray()[j] - '0';
        	}
        }
        
        ArrayList<Integer> basins = new ArrayList<>();
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < width; j++) {
         		int value = map[i * width + j];
        		if (
        		  (j == 0          || (j > 0 && map[i * width + j - 1] > value))         &&
        		  (j == width - 1  || (j < width - 1 && map[i * width + j + 1] > value)) &&
        		  (i == 0          || (i > 0 && map[(i - 1) * width + j] > value))       &&
        		  (i == height - 1 || (i < height - 1 && map[(i + 1) * width + j] > value))) {
        			basins.add(calculateBasin(j, i, new HashSet<Integer>()));
        		}
        	}
        }
        
        Collections.sort(basins, Collections.reverseOrder());
        System.out.println("multiplied basin sizes:" + basins.get(0) * basins.get(1) * basins.get(2));
	}
	
	public static int calculateBasin(int x, int y, HashSet<Integer> visited) {
		int offset = y * width + x;
		int size = 1;
		int value = map[offset];
		
		if (value == 9 || visited.contains(offset)) return 0;
		visited.add(offset);

		if (x > 0          && map[y * width + x - 1] > value  ) size += calculateBasin(x - 1, y, visited);
		if (x < width - 1  && map[y * width + x + 1] > value  ) size += calculateBasin(x + 1, y, visited);
		if (y > 0          && map[(y - 1) * width + x] > value) size += calculateBasin(x, y - 1, visited);
		if (y < height - 1 && map[(y + 1) * width + x] > value) size += calculateBasin(x, y + 1, visited);
		
		return size;
	}
}