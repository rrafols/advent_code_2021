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
        
        int lastValue = -1;
        int increased = 0;
        while(sc.hasNext()) {
        	int k = sc.nextInt();
        	
        	if (lastValue != -1 && k > lastValue) increased++;
        	lastValue = k;
        }
        
        System.out.println("increased: " + increased);
	}
}