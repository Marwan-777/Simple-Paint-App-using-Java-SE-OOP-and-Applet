package appFiles;

import java.applet.Applet;
import java.awt.Graphics;

import java.awt.Checkbox;
import java.awt.Button;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import java.awt.Color;
import java.util.ArrayList;


public class PaintBrush extends Applet{
	

	boolean started = false;			 // Started is true when first point in shape is drawn
	boolean dragged = false;			 // To make sure that it draws a shape correctly and not just clicking

	
	
	shapes.Shape currentShape;			 // Current shape
	Color currentColor = Color.BLACK;    // current color
	
	shapes.Shape temp = null;			 // Used in clearing all and in other cases
	
	boolean currentSolidState  = false ; // Whether current shape is solid or not
	boolean erasing = false;
	
	
	ArrayList<shapes.Shape> allShapes = new ArrayList<shapes.Shape>();
	
	
	public void init(){
		
		// Assigning Mouse listener 
		
		Listener ls = new Listener();
		this.addMouseMotionListener(ls);
		this.addMouseListener(ls);
		
		// Creating Buttons and adding them
		
		currentShape = new shapes.Line();
		Button lineButton = new Button("Line");
		Button rectButton = new Button("Rectangle");
		Button ovalButton = new Button("Oval");
		Button redButton = new Button("Red");
		Button greenButton = new Button("Green");
		Button blueButton = new Button("Blue");
		Button blackButton = new Button("Black");
		Checkbox solid = new Checkbox("Solid");
		Button eraserButton = new Button("Eraser");
		Button clearButton = new Button("Clear All");
		Button freeDrawButton = new Button("Free draw");
		add(lineButton);
		add(rectButton);
		add(ovalButton);
		add(freeDrawButton);
		add(redButton);
		add(greenButton);
		add(blueButton);
		add(blackButton);
		add(eraserButton);
		add(clearButton);
		add(solid);
		
		// Assigning Button listeners using Anonymous inner class
		
		lineButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentShape = new shapes.Line();
					currentShape.setSolid(currentSolidState);
					currentShape.setColor(currentColor);
					
				}
		
			}
		);
		rectButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentShape = new shapes.Rectangle();
					currentShape.setSolid(currentSolidState);
					currentShape.setColor(currentColor);
					
				}
		
			}
		);
		ovalButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentShape = new shapes.Oval();
					currentShape.setSolid(currentSolidState);
					currentShape.setColor(currentColor);
					
				}
		
			}
		);
		freeDrawButton.addActionListener(new ActionListener()		
			{
				public void actionPerformed(ActionEvent e){
					currentShape = new shapes.FreeDraw();
					currentShape.setColor(currentColor);
					erasing = false;
				}
		
			}
		);
		redButton.addActionListener(new ActionListener()		
			{
				public void actionPerformed(ActionEvent e){
					currentColor = Color.RED;
					currentShape.setColor(currentColor);			
				}
		
			}
		);
		greenButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentColor = Color.GREEN;
					currentShape.setColor(currentColor);
				}
		
			}
		);
		blueButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentColor = Color.BLUE;
					currentShape.setColor(currentColor);
				}
		
			}
		);
		blackButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentColor = Color.BLACK;
					currentShape.setColor(currentColor);
				}
		
			}
		);
		eraserButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					currentShape = new shapes.FreeDraw();
					erasing = true;
						
					
				}
		
			}
		);
		clearButton.addActionListener(new ActionListener()			
			{
				public void actionPerformed(ActionEvent e){
					allShapes.clear();
					
					// Using the temp object to store the current object state to restore it
					
					if(currentShape instanceof shapes.Line)
						temp = new shapes.Line(currentShape);
					if(currentShape instanceof shapes.Rectangle)
						temp = new shapes.Rectangle(currentShape);
					if(currentShape instanceof shapes.Oval)
						temp = new shapes.Oval(currentShape);		
					if(currentShape instanceof shapes.FreeDraw)
						temp = new shapes.FreeDraw();
					
					currentShape = null;
		
					repaint();
					
					
				}
		
			}
		);
		solid.addItemListener(new ItemListener()			
			{
				public void itemStateChanged(ItemEvent e){
					if(currentSolidState == false)
						currentSolidState = true;
					else
						currentSolidState = false;
					
					currentShape.setSolid(currentSolidState);
				}
			}
		);		
		
	}
	
	
	public void paint(Graphics g){
		shapes.Shape s;
		for(int i =0 ; i<allShapes.size(); i++){			 // Draw existing shapes
			s = allShapes.get(i);
			s.draw(g);										 // Using the overriden method to draw
		}
		if(currentShape != null){
			currentShape.draw(g);
		}
		else{
			if(temp instanceof shapes.Line)
				currentShape = new shapes.Line(temp);
			if(temp instanceof shapes.Rectangle)
				currentShape = new shapes.Rectangle(temp);
			if(temp instanceof shapes.Oval)
				currentShape = new shapes.Oval(temp);
			if(temp instanceof shapes.FreeDraw)
				currentShape = new shapes.FreeDraw(temp);
			
		}	
	}
	
	
	// Creating the Mouse Listener 
	
	
	
	class Listener extends MouseAdapter implements MouseMotionListener{
		int originX;
		int originY;
		int dim = 4;
		public void mouseMoved(MouseEvent e){}	    					     // Just ot override
		public void mouseDragged(MouseEvent e){
			if(started && currentShape instanceof shapes.FreeDraw){
				if(erasing){
					currentShape.setEraser();
					currentShape.addPoint(e.getX(), e.getY(), Color.WHITE);
				}
				else{
					currentShape.addPoint(e.getX(),e.getY(),currentColor);
				}
				dragged = true;
				repaint();
			}
			if(started && currentShape instanceof shapes.Line){				// Painting of the line
				currentShape.setX2(e.getX());
				currentShape.setY2(e.getY());
				dragged = true;
				repaint();
			}
			
			if(started && 
			  (currentShape instanceof shapes.Rectangle || currentShape instanceof shapes.Oval )
				&& !(currentShape instanceof shapes.FreeDraw) ){	
				// Painting of the Rectangle or Oval
				
				// Conditions to handle drawing the shape correctly 
			
				if(originX < e.getX() && originY > e.getY()){			// Mouse is up right
					currentShape.setX1(originX);
					currentShape.setY1(e.getY());
				}
				if(originX < e.getX() && originY < e.getY()){			// Mouse is down right
					currentShape.setX1(originX);
					currentShape.setY1(originY);
				}
				if(originX > e.getX() && originY < e.getY()){			// Mouse is down left
					currentShape.setX1(e.getX());
					currentShape.setY1(originY);					
				}
				if(originX > e.getX() && originY > e.getY()){			// Mouse is up left
					currentShape.setX1(e.getX());
					currentShape.setY1(e.getY());
				}
				currentShape.setX2( Math.abs(originX - e.getX()) );
				currentShape.setY2( Math.abs(originY - e.getY()) );
				
				dragged = true;
				repaint();
			}
		}
		public void mousePressed(MouseEvent e){
			if(currentShape instanceof shapes.Rectangle
				|| currentShape instanceof shapes.Oval){
				
				// storing the point that the shape pivots aroud 
					
				originX = e.getX();
				originY = e.getY();
			}
			currentShape.setX1(e.getX());
			currentShape.setY1(e.getY());
			started = true;	
		}
		
		
		
		public void mouseReleased(MouseEvent e){
			if(started && !dragged)						// Mouse is clicked and not dragged	
				started = false;			
			if(started && dragged){						// Mouse is pressed and dragged then shape is painted successfully
			
				// Add the shape to the allShapes ArrayList
				// Using copy constructor of each class 
				// To deep copy the object
				
				if(currentShape instanceof shapes.Line)
					allShapes.add(new shapes.Line(currentShape));			
				
				if(currentShape instanceof shapes.Rectangle)
					allShapes.add(new shapes.Rectangle(currentShape));	
				if(currentShape instanceof shapes.Oval)
					allShapes.add(new shapes.Oval(currentShape));	
				if(currentShape instanceof shapes.FreeDraw)
					allShapes.add(new shapes.FreeDraw(currentShape));
				
				
				// reset for the next shape
				started = false;
				dragged = false;
			
			}	
		}
	}
	
}





