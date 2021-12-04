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
        
        int[] bits = null;
        while(sc.hasNext()) {
        	String number = sc.next();
        	if (bits == null) bits = new int[number.length()];
        	
			for (int i = 0; i < number.length(); i++) {
				int bit = number.charAt(i) == '1' ? 1 : -1;
				bits[i] += bit;
			}
			
        	sc.nextLine();
        }
        
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < bits.length; i++) {
        	gamma.append(bits[i] >= 0 ? '1' : '0');
        	epsilon.append(bits[i] < 0 ? '1' : '0');
        }
        
        int powerConsumption = Integer.parseUnsignedInt(gamma.toString(), 2) * Integer.parseUnsignedInt(epsilon.toString(), 2);
        
        System.out.println("epsilon:"  + epsilon);
        System.out.println("gamma:"  + gamma);
        System.out.println("powerConsumption: " + powerConsumption);
	}
}
