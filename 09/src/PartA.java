import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PartA {
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
        
        int width = lines.get(1).length();
        int height = lines.size();
        map = new int[width * height];
        for (int i = 0; i < lines.size(); i++) {
        	for (int j = 0; j < lines.get(i).length(); j++) {
        		map[i * width + j] = lines.get(i).toCharArray()[j] - '0';
        	}
        }
        
        int accRisk = 0;
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < width; j++) {
        		int value = map[i * width + j];
        		
        		if (
        		  (j == 0          || (j > 0 && map[i * width + j - 1] > value))         &&
        		  (j == width - 1  || (j < width - 1 && map[i * width + j + 1] > value)) &&
        		  (i == 0          || (i > 0 && map[(i - 1) * width + j] > value))       &&
        		  (i == height - 1 || (i < height - 1 && map[(i + 1) * width + j] > value))) {
        			accRisk += value + 1;
        		}
        	}
        }
        
    
        System.out.println("accumulated risk: " + accRisk);
	}
}