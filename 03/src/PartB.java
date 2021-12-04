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
        
        ArrayList<String> numberList = new ArrayList<>();
        while(sc.hasNext()) {
        	numberList.add(sc.next());
        	sc.nextLine();
        }
        
        String oxygenRating = parseBitCriteria(numberList, true);
        String co2scrubberRating = parseBitCriteria(numberList, false);
        
        int lifeSupport = Integer.parseUnsignedInt(oxygenRating.toString(), 2) * Integer.parseUnsignedInt(co2scrubberRating.toString(), 2);
        
        
        System.out.println("Oxygen rating: " + oxygenRating);
        System.out.println("CO2 scrubber rating: " + co2scrubberRating);
        System.out.println("Life support rating: " + lifeSupport);
	}

	private static String parseBitCriteria(ArrayList<String> numberList, boolean mostCommon) {
		int index = 0;
		ArrayList<String> current = numberList;
        while(current.size() > 1) {
        	ArrayList<String> processed = new ArrayList<>();
        	
        	int[] bits = getBitCount(current);
        	int charToMatch = bits[index] >= 0 ? mostCommon ? '1': '0' : mostCommon ? '0' : '1';
        	
        	for (String number: current) {
        		if (number.charAt(index) == charToMatch) processed.add(number);
        	}
        	
        	index++;
        	current = processed;
        }
		return current.get(0);
	}

	private static int[] getBitCount(ArrayList<String> numberList) {
		int[] bits = null;
		
		for (String number : numberList) {
			if (bits == null) bits = new int[number.length()];
			for (int i = 0; i < number.length(); i++) {
				int bit = number.charAt(i) == '1' ? 1 : -1;
				bits[i] += bit;
			}
		}
		
		return bits;
	}
}