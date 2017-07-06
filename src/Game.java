
import java.util.HashMap;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PVector;

public class Game extends PApplet {

	Node start; 
	Node goal; 
	
	HashMap<int[], Node> nodes;
	Vector<Node> openSet = new Vector<Node>();
	Vector<Node> closedSet = new Vector<Node>();


	int numRows;
	int numCols;
	int w = 10;

	public void settings(){
		size(600, 600);
	}

	public void setup(){
		numRows = height / w;
		numCols = width / w;
		
		goal = new Node(this, numCols, numRows);
		start = new Node(this, 0, 0);
		
		//createNodes();
		openSet.add(start);
	}

	public void draw(){
		background(0);
		drawGrid();
		run();
	}
	
	int getLowestF(){
		
		float bestF = Float.POSITIVE_INFINITY;
		int selectedIndex = -1;
		for(int i = 0; i < openSet.size(); i++){
			
			if (openSet.get(i).f < bestF){
				bestF = openSet.get(i).f;
				selectedIndex = i;
			}
			
		}
		return selectedIndex;
	}
	
	Boolean isOpenSetEmpty(){
		if (openSet.size() < 1){
			return true;
		}
		return false;
	}
	
	void paintSquares(){
		for(Node n : openSet){
        	fill(0,255,0);
        	rect(n.pos.x, n.pos.y, w, w);
        }
        for(Node n : closedSet){
        	fill(255,0,0);
        	rect(n.pos.x, n.pos.y, w, w);
        }
	}
	
	Boolean isInOpenSet(Node n){
		if(openSet.contains(n))
			return true;
		return false;
	}
	
	Boolean isInClosedSet(Node n){
		if(closedSet.contains(n))
			return true;
		return false;
	}
	
	Boolean run(){
		if (isOpenSetEmpty())
			return false;
		
		int index = getLowestF();
		Node current = openSet.get(index);
		
		if (current.equals(goal)){
			println("finished!");
			// should reconstruct and return the path sequence from all the cameFroms
			return false;
		}
		
		openSet.remove(index);
        closedSet.add(current);

        for(int i = 0; i < current.neighbours.size(); i++){
        	
        	Node n = new Node(this, (int) current.neighbours.get(i).x, (int) current.neighbours.get(i).y);

        	if (isInClosedSet(n)){
        		continue;
        	}
        	
        	if(!isInOpenSet(n)){
        		openSet.add(n);
        	}
        	
        	float gScore = current.g + 1; 
        	if (gScore >= n.g ){
        		continue;
        	}
        	
        	n.cameFrom = current;
        	n.g = gScore;
        	n.h = PVector.dist(n.pos, goal.pos);
        	n.f = gScore + n.h;
        	
        	paintSquares();
        }
        
        return false;
 
	}

	void createNodes(){
		nodes = new HashMap<int[], Node>();

		for(int i=0; i < numRows; i++ ){
			for(int j=0; j < numCols; j++){
				Node n = new Node(this, j, i);
				nodes.put(new int[]{j,i}, n);
			}
		}
	}

	void drawGrid(){
		stroke(255);
		for(int i=0; i < numRows; i++ ){
			line(i*w, 0, i*w, height);
			for(int j=0; j < numCols; j++){
				line(0, j*w, width, j*w);
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main("Game");
	}

}
