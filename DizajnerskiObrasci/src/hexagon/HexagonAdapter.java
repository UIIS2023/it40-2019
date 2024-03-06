package hexagon;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import old_project.geometry.Circle;
import old_project.geometry.Point;
import old_project.geometry.SurfaceShape;

public class HexagonAdapter extends SurfaceShape {
	
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		this.hexagon = new Hexagon(0, 0, 0);
	}

	public HexagonAdapter(Point point, int newRadius, boolean selected, Color edgeColor, Color innerColor) {
		this.hexagon = new Hexagon(point.getX(), point.getY(), newRadius);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(edgeColor);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(this.hexagon.getX() + byX);
		this.hexagon.setY(this.hexagon.getY() + byY);
	}

	@Override
	public int compareTo(Object o) {
		
		if (o instanceof HexagonAdapter) {
			HexagonAdapter toCompare = (HexagonAdapter)o;
			return this.hexagon.getR() - toCompare.getHexagon().getR();
		}
		
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {	
		return this.hexagon.doesContain(x, y);
		
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
		
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public void fill(Graphics g) {
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public void setRadius(int radius) {
		hexagon.setR(radius);
	}
	
	public int getRadius() {
		return hexagon.getR();
	}
	
	@Override
	public void setInnerColor(Color color) {
		hexagon.setAreaColor(color);
	}
	
	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}
	
	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public boolean contains(Point p) {
		return hexagon.doesContain(p.getX(), p.getY());
	}

	@Override
	public String toString() {
	return	this.hexagon.getX() + "," + this.hexagon.getY() + "," + this.hexagon.getR() + "," + this.hexagon.getBorderColor().getRGB() + "," + this.hexagon.getAreaColor().getRGB(); 
	}
	
	@Override
	public Object clone()  {
		HexagonAdapter temp=new HexagonAdapter();
		temp.setX(this.getX());
		temp.setY(this.getY());
		temp.setRadius(this.getRadius());
		temp.setInnerColor(this.getInnerColor());
		temp.setColor(this.getColor());
		return temp;
	}

}
