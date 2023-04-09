

package shapes;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

public class FreeDraw extends Oval {
	
	int dim = 4;
	
	public void setEraser(){										// Change the size for the eraser
		dim = 8;
	}
	private ArrayList <Shape> drawing = new ArrayList<Shape>();
	
	public void setVec(ArrayList<Shape> s){ drawing = s ;}			
	public ArrayList<Shape> getVec(){ return drawing ;}
	
	Shape o;
	public void draw(Graphics g){
		for (int i =0 ; i<drawing.size() ; i++){
			o = drawing.get(i);
			o.draw(g);
		}
	}

	public void addPoint(int x, int y , Color c){
		
		drawing.add(new shapes.Oval( x-dim/2, y - dim/2, dim , dim , c , true));
		
	}
	public FreeDraw (Shape p){				// Copy constructor
		this.setVec(p.getVec());
		
	}
	public FreeDraw(){}
	
}