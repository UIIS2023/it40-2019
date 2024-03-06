package old_project.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.UIManager;

public class Donut extends Circle {

	private int innerRadius;
	
	public Donut() {
		
	} 
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius); //poziv metoda iz superClass
		this.innerRadius = innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius); 
		setSelected(selected);
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected); 
		setColor(color);
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color); 
		setInnerColor(innerColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	@Override
	public void fill(Graphics g) {
	}
	@Override
	public void draw(Graphics g) {
		Ellipse2D outerCircle =new Ellipse2D.Double(
				this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(),
				this.getRadius()*2, 
				this.getRadius()*2);
		
		Ellipse2D innerCircle=new Ellipse2D.Double(
				this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius()*2,
				this.getInnerRadius()*2);
		
		Area donut=new Area(outerCircle);
		donut.subtract(new Area(innerCircle));
		
		Graphics2D graphics2D =(Graphics2D)g;
		graphics2D.setColor(getInnerColor());
		graphics2D.fill(donut);
		graphics2D.setColor(getColor());
		graphics2D.draw(donut);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius()- 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			
		}
	}
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI; 
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut temp = (Donut) obj;
			if(this.getCenter().equals(temp.getCenter()) && this.getRadius() == temp.getRadius() && this.innerRadius == temp.innerRadius)
				//super.equals(temp)
				return true;
			else
				return false;
		} else
			return false;
	}
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	@Override
	public String toString() {
		return super.toString() + "," + innerRadius;
	}
	@Override
	public Object clone() {
		Donut temp=new Donut();
		temp.setCenter(this.getCenter());
		try {
			temp.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setInnerRadius(this.getInnerRadius());
		temp.setInnerColor(this.getInnerColor());
		temp.setColor(this.getColor());
		return temp;
	}
	
}