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
    
        long[] state = new long[10];
        
        String initialState = sc.nextLine();
        for (String day : initialState.split(",")) state[Integer.parseInt(day)]++;
        
        int days = 0;
        while(days++ < 80) {
        	for (int i = 0; i < state.length; i++) {
        		if (i == 0) {
        			state[7] += state[i];
        			state[9] += state[i];
        			state[0] = 0;
        		} else {
        			state[i - 1] += state[i];
        			state[i] = 0;
        		}
        	}
        }
        
        long total = 0;
        for (int i = 0; i < state.length; i++) total += state[i];
        
        System.out.println("total fish: " + total);
	}
}