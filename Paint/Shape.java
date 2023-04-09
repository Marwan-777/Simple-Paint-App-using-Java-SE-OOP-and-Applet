

package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Shape{
	private Color color = Color.BLACK;				// Default color is black
	
	//Starting points
	private int x1;
	private int y1;
	
	//Setters and getters
	
	public void setX1(int x){ x1 = x ;}
	public void setY1(int y){ y1 = y ;}
	public void setColor(Color c){ color = c ;}
	
	public int getX1(){ return x1 ;}
	public int getY1(){ return y1 ;}
	public Color getColor(){ return color ;}



	//Constructors
	
	public Shape(int x1, int y1, Color c){
		this.x1 = x1;
		this.y1 = y1;
		color = c;
	}
	public Shape(){}

	
	
	
	// those to be overriden in children classes
	
	public abstract int getX2();
	public abstract int getY2();
	public abstract void setX2(int x);
	public abstract void setY2(int y);
	public abstract boolean getSolid();
	public abstract void setSolid(boolean b);
	
	public abstract void draw(Graphics g);
	
	public abstract void setEraser();							// Just for FreeDraw class
	public abstract ArrayList<Shape> getVec();					// Just for FreeDraw class
	public abstract void setVec(ArrayList<Shape> s);			// Just for FreeDraw class
	public abstract void addPoint(int x, int y , Color c);		// Just for FreeDraw class
	
	
	
	
	
	
	
}
