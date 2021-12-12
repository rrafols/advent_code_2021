import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class PartB {
	private static HashMap<String, Cave> caveByName = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        try {
            sc = new Scanner(new File("bin/i.txt"));
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
                
        while(sc.hasNextLine()) {
        	String[] connection = sc.nextLine().split("-");
        	if (!caveByName.containsKey(connection[0])) caveByName.put(connection[0], new Cave(connection[0]));
        	if (!caveByName.containsKey(connection[1])) caveByName.put(connection[1], new Cave(connection[1]));
        	
        	caveByName.get(connection[0]).connections.add(caveByName.get(connection[1]));
        	caveByName.get(connection[1]).connections.add(caveByName.get(connection[0]));
        }

        int count = 0;
        LinkedList<String> pending = new LinkedList<>();
        pending.add("start");
        
        while(!pending.isEmpty()) {
        	String path = pending.pollFirst();
        	String[] cavesInPath = path.split(",");
        	String lastCave = cavesInPath[cavesInPath.length - 1];
        	
        	if (lastCave.equals("end")) {
        		System.out.println("valid path " + path);
        		count++;
        	} else {
	        	for(Cave conn : caveByName.get(lastCave).getPendingConnections(cavesInPath)) pending.add(path + "," + conn.name);
        	}
        }
        
        
        System.out.println("total paths: " + count);
	}
	
	static class Cave {
		String name;
		LinkedList<Cave> connections;
		
		Cave(String name) {
			this.name = name;
			connections = new LinkedList<>();
		}
		
		LinkedList<Cave> getPendingConnections(String[] paths) {
			LinkedList<Cave> out = new LinkedList<>();
			
			HashSet<String> visited = new HashSet<>();
			visited.add("start");
			
			HashSet<String> smallCave = new HashSet<>();
			boolean visitedTwice = false;
			for(String path : paths) {
				if (path.toLowerCase().equals(path)) {
					if (smallCave.contains(path)) visitedTwice = true;
					smallCave.add(path);
				}
			}

			if (visitedTwice) visited.addAll(smallCave);
			for (Cave conn : connections) if (!visited.contains(conn.name)) out.add(conn);
			
			return out;
		}
	}
}