import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class PartA {
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

        HashMap<String, String> insertions = new HashMap<>();
        while(sc.hasNextLine()) {
        	String[] rule = sc.nextLine().split(" -> ");
        	insertions.put(rule[0], rule[1]);
        }

        int iteration = 0;
        while (iteration < 10) {
        	StringBuilder sb = new StringBuilder();
        	for (int i = 0; i < polymer.length() - 1; i++) {
        		sb.append(polymer.charAt(i));
        		String pair = polymer.charAt(i) + "" + polymer.charAt(i + 1);
        		if (insertions.containsKey(pair)) {
        			sb.append(insertions.get(pair));
        		}
        	}
        	sb.append(polymer.charAt(polymer.length() - 1));
        	polymer = sb.toString();

        	HashMap<Character, Integer> count = new HashMap<>();
            for (int i = 0; i < polymer.length(); i++) {
            	char ch = polymer.charAt(i);
            	if (!count.containsKey(ch)) count.put(ch, 0);
            	count.put(ch, count.get(ch) + 1);

            }
        	iteration++;
        }

        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < polymer.length(); i++) {
        	char ch = polymer.charAt(i);
        	if (!count.containsKey(ch)) count.put(ch, 0);
        	count.put(ch, count.get(ch) + 1);
        }

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        char maxCh = ' ';
        char minCh = ' ';
        for (Entry<Character, Integer> e : count.entrySet()) {
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