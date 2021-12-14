import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
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

        String polymer = sc.nextLine();
        sc.nextLine();

        HashMap<String, Character> insertions = new HashMap<>();
        while(sc.hasNextLine()) {
        	String[] rule = sc.nextLine().split(" -> ");
        	insertions.put(rule[0], rule[1].charAt(0));
        }

        HashMap<Character, Long> totalCount = new HashMap<>();
        HashMap<String, Long> count = new HashMap<>();
        for (int i = 0; i < polymer.length() - 1; i++) {
        	if (!totalCount.containsKey(polymer.charAt(i))) totalCount.put(polymer.charAt(i), 0l);
        	
        	String pair = polymer.charAt(i) + "" + polymer.charAt(i + 1);
        	count.put(pair, count.containsKey(pair) ? count.get(pair) + 1l : 1l);
        	totalCount.put(polymer.charAt(i), totalCount.get(polymer.charAt(i)) + 1);
        }
        
        if (!totalCount.containsKey(polymer.charAt(polymer.length() - 1))) totalCount.put(polymer.charAt(polymer.length() - 1), 0l);
        totalCount.put(polymer.charAt(polymer.length() - 1), totalCount.get(polymer.charAt(polymer.length() - 1)) + 1);

        int iteration = 0;
        while (iteration < 40) {        	
        	HashMap<String, Long> nextCount = new HashMap<>();
        	for (Entry<String, Character> e : insertions.entrySet()) {
        		String pair = e.getKey();

        		if (count.containsKey(pair) && count.get(pair) > 0) {
        			char ch = e.getValue();
        			
        			if (!totalCount.containsKey(ch)) totalCount.put(ch, 0l);
        			totalCount.put(ch, totalCount.get(ch) + count.get(pair));
        			
        			String firstKey = pair.charAt(0) + "" + ch;
        			String secondKey = ch + "" + pair.charAt(1);

        			if (!nextCount.containsKey(firstKey)) nextCount.put(firstKey, 0l);
        			if (!nextCount.containsKey(secondKey)) nextCount.put(secondKey, 0l);

        			long oldPairCount = nextCount.containsKey(pair) ? nextCount.get(pair) : 0;
        			nextCount.put(pair, oldPairCount - count.get(pair));
        			nextCount.put(firstKey, nextCount.get(firstKey) + count.get(pair));
        			nextCount.put(secondKey, nextCount.get(secondKey) + count.get(pair));
        		}
        	}

        	for (Entry<String, Long> e : nextCount.entrySet()) {
        		String key = e.getKey();
        		long delta = e.getValue();

        		if (!count.containsKey(key)) count.put(key, 0l);
        		count.put(key, count.get(key) + delta);
        	}

        	iteration++;
        }

        Long maxValue = Long.MIN_VALUE;
        Long minValue = Long.MAX_VALUE;
        char maxCh = ' ';
        char minCh = ' ';
        for (Entry<Character, Long> e : totalCount.entrySet()) {
        	if (e.getValue() > maxValue) {
        		maxValue = e.getValue();
        		maxCh = e.getKey();
        	}
        	
        	if (e.getValue() < minValue) {
        		minValue = e.getValue();
        		minCh = e.getKey();
        	}
        }
        
        System.out.println(maxCh + " :: " + maxValue);
        System.out.println(minCh + " :: " + minValue);
        System.out.println("difference: " + (maxValue - minValue));
	}
}