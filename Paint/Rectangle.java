

package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Rectangle extends Shape{
	
	// end points
	private int width;
	private int height;
    private	boolean isSolid = false;
	
	public void setSolid(boolean b){
		isSolid = b;
	}
	public boolean getSolid(){ return isSolid ;}
	public void setVec(ArrayList<Shape> s){}								// Just to override
	public ArrayList<Shape> getVec(){ return new ArrayList<Shape>();}       // Just to override
	public void setEraser(){};												// Just to override
	
	
	public void addPoint(int x, int y , Color c){}							// Just to override
	public int getX2(){ return width ;}			    // Override
	public int getY2(){ return height ;}			// Override
	
	public void draw(Graphics g){		    		// Override
		g.setColor(super.getColor());
		if(isSolid)
			g.fillRect(super.getX1(),super.getY1(),width,height);
		else
			g.drawRect(super.getX1(),super.getY1(),width,height);
	}
	
	public void setX2(int x ){ width = x ;}			// Override
	public void setY2(int y ){ height = y ;}		// Override	
	
	// Constructors
	
	public Rectangle(int x1, int y1, int x2, int y2,Color c, boolean b) {
		super(x1,y1,c);
		width = x2;
		height = y2;
		isSolid = b;
	}
	
	public Rectangle(Shape r ){						// Copy constructor
	
		this(r.getX1(),r.getY1(),r.getX2(),r.getY2(),r.getColor(),r.getSolid());
	}
	
	public Rectangle(){}
}