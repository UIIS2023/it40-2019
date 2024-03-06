package old_project.geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int x; 
	private int y;
	
	public Point(){
		
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, boolean selected) {
		this(x, y); //mora biti 1. linija koda
		setSelected(selected);
	}
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		this.setColor(color);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point pocetak = new Point(0, 0);
			return (int) (this.distance(pocetak.getX(), pocetak.getY()) - ((Point)o).distance(pocetak.getX(), pocetak.getY()));
		}
		return 0;
	}
	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}
	
	@Override //overridovana metoda nadklase Shape
	public void draw(Graphics g) {
		g.setColor(getColor()); //defaultna boja crna
		//umesto tacke crtamo dve linice koje se seku pod pravim uglov da bi se tacka videla
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x-3, this.y-3, 6, 6);
		}
	}
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
		//ako je selectovana tacka za <= 3 piksela udaljena od nase, vraca se true
	}
	public double distance(int x2, int y2) {
		int dx = this.x - x2;
		int dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point){
			Point temp = (Point) obj; //downcast, Object --> Point
			if(this.x == temp.x && this.y == temp.y)
				return true;
			else
				return false;
		} else
			return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override //overridovana je metoda nadklase Object
	public String toString() {
		//(x, y)
		return  x + "," + y  +","+ getColor().getRGB();
	}
	
	@Override
	public Object clone() {
		Point temp=new Point();
		temp.setX(this.getX());
		temp.setY(this.getY());
		temp.setColor(this.getColor());
		return temp;
	}

}
