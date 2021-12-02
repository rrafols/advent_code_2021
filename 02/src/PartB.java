import java.io.File;
import java.util.Scanner;

public class PartB {
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
        int aim = 0;
        
        while(sc.hasNext()) {
        	String direction = sc.next();
        	int amount = sc.nextInt();
        	
        	switch(direction.toLowerCase()) {
        		case "forward":
        			X += amount;
        			Y += aim * amount;
        			break;
        			
        		case "down":
        			aim += amount;
        			break;
        			
        		case "up":
        			aim -= amount;
        			break;
        	}
        	
        	sc.nextLine();
        }
        
        System.out.println("position " + (X * Y));
	}
}