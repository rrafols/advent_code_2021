import java.io.File;
import java.util.ArrayList;
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
        
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while(sc.hasNext()) {
        	nums.add(sc.nextInt());
        }
        
        int increased = 0;
        for (int i = 0; i < nums.size() - 3; i++) {
        	// only compare last number of 2nd window with first number from 1st window (the other 2 numbers are the same)
        	if (nums.get(i+3) > nums.get(i)) increased++;
        }
        
        System.out.println("increased: " + increased);
	}
}