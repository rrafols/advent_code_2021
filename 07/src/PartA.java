import java.io.File;
import java.util.ArrayList;
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
    
        ArrayList<Integer> crabs = new ArrayList<>();
        
        String initialState = sc.nextLine();
        for (String position : initialState.split(",")) crabs.add(Integer.parseInt(position));
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int position : crabs) {
        	if (position < min) min = position;
        	if (position > max) max = position;
        }
        	
        int minFuel = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int sum = 0;
            for (int position : crabs) sum += getFuel(Math.abs(position - i));
            if (sum < minFuel) minFuel = sum;
        }
        
        System.out.println("min fuel: " + minFuel);
	}
	
	public static int getFuel(int k) {
		return k * (k + 1) / 2;
	}
}