import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        
        int sum = 0;
        while(sc.hasNextLine()) {
        	String line = sc.nextLine();
        	
        	HashMap<Character, Character> map = new HashMap<>();
        	
        	HashMap<String, Integer> numbers = new HashMap<>();
        	String[] numString= new String[10];
        	
        	ArrayList<String> l5 = new ArrayList<>();
        	ArrayList<String> l6 = new ArrayList<>();
        	
        	// parse signals
        	String[] signals = line.split("\\|")[0].trim().split(" ");
        	for(String signal : signals) {
        		signal = sortString(signal);
        		if (signal.length() == 2) { numbers.put(signal, 1); numString[1] = signal; }
        		if (signal.length() == 3) { numbers.put(signal, 7); numString[7] = signal; }
        		if (signal.length() == 4) { numbers.put(signal, 4); numString[4] = signal; }
        		if (signal.length() == 5) l5.add(signal);
        		if (signal.length() == 6) l6.add(signal);
        		if (signal.length() == 7) { numbers.put(signal, 8); numString[8] = signal; }
        	}
     	
        	for (int i = 0; i < l6.size(); i++) {
        		String pot6 = l6.get(i);
        		
        		if ((pot6.indexOf(numString[1].charAt(0)) != -1 && pot6.indexOf(numString[1].charAt(1)) == -1) ||
        			(pot6.indexOf(numString[1].charAt(0)) == -1 && pot6.indexOf(numString[1].charAt(1)) != -1)) {
        			numString[6] = pot6;
        			numbers.put(pot6, 6);
        			
        			if (pot6.indexOf(numString[1].charAt(0)) == -1) {
        				map.put('c', numString[1].charAt(0));
        				map.put('f', numString[1].charAt(1));
        			} else {
        				map.put('c', numString[1].charAt(1));
        				map.put('f', numString[1].charAt(0));
        			}
        		}
        	}
        	
        	for (int i = 0; i < l5.size(); i++) {
        		String pot5 = l5.get(i);
        		
        		if (pot5.indexOf(map.get('c')) == -1) {
        			numString[5] = pot5;
        			numbers.put(pot5, 5);
        			
        			for (int j = 0; j < numString[6].length(); j++) {
        				if (pot5.indexOf(numString[6].charAt(j)) == -1) {
        					map.put('e', numString[6].charAt(j));
        				}
        			}
        		}
        	}
        	
        	for (int i = 0; i < l5.size(); i++) {
        		String pot = l5.get(i);
        		
        		if (pot.indexOf(map.get('c')) != -1 && pot.indexOf(map.get('f')) != -1) {
        			numString[3] = pot;
        			numbers.put(pot, 3);
        		}
        		
        		if (pot.indexOf(map.get('c')) != -1 && pot.indexOf(map.get('e')) != -1) {
        			numString[2] = pot;
        			numbers.put(pot, 2);
        		}
        	}
        	
        	for (int i = 0; i < l6.size(); i++) {
        		String pot = l6.get(i);

        		if (pot.indexOf(map.get('e')) == -1) {
        			numString[9] = pot;
        			numbers.put(pot, 9);
        		}
        		
        		if (pot.indexOf(map.get('c')) != -1 && pot.indexOf(map.get('e')) != -1) {
        			numString[0] = pot;
        			numbers.put(pot, 0);
        		}
        	}
        	
        	StringBuilder sb = new StringBuilder();
        	String[] digits = line.split("\\|")[1].trim().split(" ");
        	for (String digit: digits) {
        		sb.append(numbers.get(sortString(digit)));
        	}
        	
        	sum += Integer.parseInt(sb.toString());
        }
    
        System.out.println("sum count digits: " + sum);
	}
	
	public static String sortString(String str) {
		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}
}