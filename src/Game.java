
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


	int numRows;
	int numCols;
	int w = 10;

	public void settings(){
		size(600, 600);
	}

	public void setup(){


		numRows = height / w;
		numCols = width / w;

		createNodes();
	}

	public void draw(){
		background(0);
		drawGrid();
	}

	void createNodes(){
		nodes = new HashMap<int[], Node>();

		for(int i=0; i < numRows; i++ ){
			for(int j=0; j < numCols; j++){
				Node n = new Node(this);
				n.col = j;
				n.row = i;
				nodes.put(new int[]{j,i}, n);
			}
		}
	}

	void drawGrid(){
		stroke(255);
		for(int i=0; i < numRows; i++ ){
			for(int j=0; j < numCols; j++){
				line(i*w, j*w, w, w);
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main("Game");
	}

}
