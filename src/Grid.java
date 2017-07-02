import processing.core.PVector;

public class Grid {
	Game g;
	PVector[] pos; 
	
	public Grid(Game g){
		this.g = g;
	}
	
	public void update(){}
	
	public void draw(){
		g.rect(pos.x, pos.y, 10, 10);
	}
}
