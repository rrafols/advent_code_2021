import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
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
        
        String numberList = sc.nextLine();
        sc.nextLine();
        
        ArrayList<Board> boards = new ArrayList<>();
        while(sc.hasNext()) {
        	Board b = new Board();
        	for(int i = 0; i < 5; i++) b.addRow(sc.nextLine());
        	boards.add(b);
        	if(sc.hasNextLine()) sc.nextLine();
        }
        
        String[] numbers = numberList.split(",");
        for (int i = 0; i < numbers.length; i++) {
        	int num = Integer.parseInt(numbers[i]);
        	
        	ArrayList<Board> boardsToRemove = new ArrayList<>();
        	
        	for (Board b : boards) {
        		b.numberDrawn(num);
        		if (b.boardWon()) {
        			if (boards.size() - boardsToRemove.size() == 1) { 
	        			int remaining = b.remainingSum();
	        			System.out.println("last board to win - remaining & last drawn number: " + remaining + " " + num);
	        			System.out.println("result: " + (remaining * num));
	        			System.exit(1);
        			} else {
        				boardsToRemove.add(b);
        			}
        		}
        	}
        	
        	for (Board b : boardsToRemove) boards.remove(b);
        }
	}
		
	static class Board {
		private HashSet<Integer> remaining;
		private ArrayList<HashSet<Integer>> rows;
		private ArrayList<HashSet<Integer>> cols;
		
		public Board() {
			remaining = new HashSet<>();
			rows = new ArrayList<HashSet<Integer>>();
			cols = new ArrayList<HashSet<Integer>>();
			
			for (int i = 0; i < 5; i++) cols.add(new HashSet<Integer>());
		}
		
		public void addRow(String line) {
			String[] nums = line.trim().split("\\s+");
			HashSet<Integer> rowHash = new HashSet<>();
			for (int i = 0; i < nums.length; i++) {
				int k = Integer.parseInt(nums[i]);
				
				cols.get(i).add(k);
				rowHash.add(k);
				remaining.add(k);
			}
			
			rows.add(rowHash);
		}
		
		public void numberDrawn(int k) {
			remaining.remove(k);
			
			for(int i = 0; i < cols.size(); i++) cols.get(i).remove(k);
			for(int i = 0; i < rows.size(); i++) rows.get(i).remove(k);
		}
		
		public boolean boardWon() {
			for(int i = 0; i < cols.size(); i++) if (cols.get(i).size() == 0) return true;
			for(int i = 0; i < rows.size(); i++) if (rows.get(i).size() == 0) return true;
			return false;
		}
		
		public int remainingSum() {
			int sum = 0;
			
			for(int k : remaining) sum += k;
			
			return sum;
		}
	}
}
