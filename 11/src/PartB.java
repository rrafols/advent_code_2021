import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PartB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] map;
		
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        ArrayList<String> lines = new ArrayList<>();
        while(sc.hasNextLine()) lines.add(sc.nextLine());
        
        int width = lines.get(0).length();
        int height = lines.size();
        
        map = new int[width * height];
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < width; j++) {
        		map[i * width + j] = lines.get(i).charAt(j) - '0';
        	}
        }
        
        int flashes = 0;
        int step = 0;
        while (true) {
           	
        	System.out.println("step " + step);
        	for (int i = 0; i < height; i++) {
            	for (int j = 0; j < width; j++) {
            		System.out.print(map[i*width+j]);
            	}
            	System.out.println();
            }
        	System.out.println();
        	
        	for (int i = 0; i < map.length; i++) map[i]++;
        	
        	HashSet<Integer> flashed = new HashSet<>();
        	boolean flash = true;
        	while(flash) {
        		flash = false;
	        	for (int i = 0; i < height; i++) {
	            	for (int j = 0; j < width; j++) {
	            		int offset = i * width + j;
	            	
	            		if (map[offset] > 9) {
	            			flash = true;
	            			flashes++;
	            			flashed.add(offset);
	            			map[offset] = 0;
	            			
	            			if (i > 0) {
	            				if (!flashed.contains(offset - width)) map[offset - width]++;
	            				if (!flashed.contains(offset - width - 1) && j > 0) map[offset - width - 1]++;
	            				if (!flashed.contains(offset - width + 1) && j < width - 1) map[offset - width + 1]++;
	            			}
	            			
	            			if (!flashed.contains(offset - 1) && j > 0) map[offset - 1]++;
            				if (!flashed.contains(offset + 1) && j < width - 1) map[offset + 1]++;
	            			
	            			if (i < height - 1) {
	            				if (!flashed.contains(offset + width)) map[offset + width]++;
	            				if (!flashed.contains(offset + width - 1) && j > 0) map[offset + width - 1]++;
	            				if (!flashed.contains(offset + width + 1) && j < width - 1) map[offset + width + 1]++;
	            			}
	            		}
	            	}
	        	}
        	}
        	
        	boolean allFlash = true;
        	for (int i = 0; i < map.length && allFlash; i++) allFlash = map[i] == 0;
        	
        	if (allFlash) {
        		System.out.println("all flashed at step " + (step + 1));
        		System.exit(1);
        	}
        	step++;
        }
	}
}