import java.io.File;
import java.util.Scanner;

public class PartA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        int X = 0;
        int Y = 0;
        
        while(sc.hasNext()) {
        	String direction = sc.next();
        	int amount = sc.nextInt();
        	
        	switch(direction.toLowerCase()) {
        		case "forward":
        			X += amount;
        			break;
        			
        		case "down":
        			Y += amount;
        			break;
        			
        		case "up":
        			Y -= amount;
        			break;
        	}
        	sc.nextLine();
        }
        
        System.out.println("position " + (X * Y));
	}
}