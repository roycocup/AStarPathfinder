import java.util.ArrayList;

import processing.core.PVector;

public class Node {
	Game gr;
	int col, row; 
	PVector pos; 
	Node cameFrom;
	ArrayList<PVector> neighbours; 
	
	
	// the cost of getting from the start node to this node.
	float g = Float.POSITIVE_INFINITY; 
	// heuristic cost
	float h = Float.POSITIVE_INFINITY; 
	// cost from start to this node + heuristic
	float f = Float.POSITIVE_INFINITY;
	
	public Node(Game gr, int col, int row){
		this.gr = gr;
		this.col = col; 
		this.row = row;
		this.pos = new PVector(this.col,this.row);
		setG();
		setH();
		setF();
		findNeighbours();
	}
	
	void setG(){
		if (cameFrom == null){
			g = 0;
		} else {
			g = 1;
		}
	}
	
	void setF(){
		f = g + h; 
	}
	
	void setH(){
		if (gr.goal != null){
			h = pos.dist(gr.goal.pos);
		} else {
			h = 0;
		}
		
		
	}
	
	void findNeighbours(){
		neighbours = new ArrayList<PVector>();
		
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(col == 0) if (i == -1) continue;
				if(row == 0) if (j == -1) continue;
				if(col == gr.numCols) if (i == 1) continue;
				if(row == gr.numRows) if (j == 1) continue;
				
				neighbours.add(new PVector(i,j));
			}
		}
		
		
	}
	
	public void update(){
		
	}
	
	public void draw(){
		
	}
}
