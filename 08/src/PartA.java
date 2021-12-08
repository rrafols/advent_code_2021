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
        
        int count = 0;
        while(sc.hasNextLine()) {
        	String line = sc.nextLine();
        	
        	String[]  digits = line.split("\\|")[1].trim().split(" ");
        	for(String digit : digits) {
        		if(digit.length() == 2 || digit.length() == 3 || digit.length() == 4 || digit.length() == 7) count++;
        	}
        }
    
        System.out.println("count digits: " + count);
	}
}