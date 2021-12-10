import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class PartB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Long> scores = new ArrayList<>();
		HashMap<Character, Character> closeMap = new HashMap<>();
		closeMap.put('(', ')');
		closeMap.put('[', ']');
		closeMap.put('{', '}');
		closeMap.put('<', '>');
		
		HashMap<Character, Integer> scoreMap = new HashMap<>();
		scoreMap.put(')', 1);
		scoreMap.put(']', 2);
		scoreMap.put('}', 3);
		scoreMap.put('>', 4);
		
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        while(sc.hasNextLine()) {
        	LinkedList<Character> chunks = new LinkedList<>(); 
        	String line = sc.nextLine();
        	boolean lineCorrupted = false;
        	
        	for(int i = 0; i < line.length() && !lineCorrupted; i++) {
        		char ch = line.charAt(i);
        		
        		if (ch == '(' || ch == '[' || ch == '{' || ch == '<') chunks.add(ch);
        		else {
	    			if (ch == closeMap.get(chunks.peekLast())) chunks.removeLast();
	    			else lineCorrupted = true;
        		}
        	}
        	
        	if (!lineCorrupted) {
        		long lineScore = 0;
        		while(!chunks.isEmpty()) lineScore = lineScore * 5 + scoreMap.get(closeMap.get(chunks.pollLast()));
        		scores.add(lineScore);
        	}
        }

        Collections.sort(scores);
        System.out.println("mid score: " + scores.get(scores.size()/2));
	}
}