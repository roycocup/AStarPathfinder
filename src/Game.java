
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javafx.util.Pair;
import processing.core.PApplet;

public class Game extends PApplet {

	HashMap<int[], Node> nodes;
	Vector<Node> openSet;
	Vector<Node> closedSet;
	
	Grid grid;
	int numRows;
	int numCols;
	
	public void settings(){
		size(600, 600);
	}
	
	public void setup(){
		grid = new Grid();
		
		int divider = 10;
		numRows = height / divider;
		numCols = width / divider;
		
		
		createNodes();
	}
	
	public void draw(){
		background(0);
		grid.draw();
		
	}
	
	void createNodes(){
		nodes = new HashMap<int[], Node>();
		
		for(int i=0; i < numRows; i++ ){
			for(int j=0; j<numCols; j++){
				Node n = new Node();
				n.col = j;
				n.row = i;
				nodes.put(new int[]{j,i}, n);
			}
		}
	}
	
	
	public static void main(String[] args) {
		PApplet.main("Game");
	}

}
