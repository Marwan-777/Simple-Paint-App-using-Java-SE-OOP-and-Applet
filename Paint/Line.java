
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Line extends Shape{
	
	// end points
	private int x2;
	private int y2;
	
	public void setVec(ArrayList<Shape> s){}							  // Just to override
	public ArrayList<Shape> getVec(){ return new ArrayList<Shape>();}     // Just to override
	public void setEraser(){};							                  // Just to override
	
	public boolean getSolid(){return false ;}							  // Just to override
	public void setSolid(boolean b){}									  // Just to override
	public void addPoint(int x, int y , Color c){} 						  // Just to override
	
	
	public int getX2(){ return x2 ;}			// Override
	public int getY2(){ return y2 ;}			// Override
	
	public void draw(Graphics g){				// Override
		g.setColor(super.getColor());
		g.drawLine(super.getX1(),super.getY1(),x2,y2);
	}
	
	public void setX2(int x ){ x2 = x ;}		// Override
	public void setY2(int y ){ y2 = y ;}		// Override	
	
	// Constructors
	
	public Line(int x1, int y1, int x2, int y2,Color c) {
		super(x1,y1,c);							// Parent parameterized constructor
		this.x2 = x2;
		this.y2 = y2;
	}
	public Line(Shape l){					    // Copy constructor
	
		this(l.getX1(),l.getY1(),l.getX2(),l.getY2(),l.getColor());
		
	}
	
	public Line(){}
}