import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class PartA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<Character, Character> closeMap = new HashMap<>();
		closeMap.put('(', ')');
		closeMap.put('[', ']');
		closeMap.put('{', '}');
		closeMap.put('<', '>');
		
		HashMap<Character, Integer> scoreMap = new HashMap<>();
		scoreMap.put(')', 3);
		scoreMap.put(']', 57);
		scoreMap.put('}', 1197);
		scoreMap.put('>', 25137);
		
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        int points = 0;
        LinkedList<Character> chunks = new LinkedList<>(); 
        while(sc.hasNextLine()) {
        	String line = sc.nextLine();
        	
        	boolean lineCorrupted = false;
        	for(int i = 0; i < line.length() && !lineCorrupted; i++) {
        		char ch = line.charAt(i);
        		
        		if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
        			chunks.add(ch);
        		} else {
        			if (ch == closeMap.get(chunks.peekLast())) chunks.removeLast();
        			else {
        				lineCorrupted = true;
        				points += scoreMap.get(ch);
        			}
        		}
        	}
        }

        System.out.println("accumulated score: " + points);
	}
}