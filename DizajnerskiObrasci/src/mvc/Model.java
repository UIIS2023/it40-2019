package mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import old_project.geometry.Shape;

public class Model {
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void addShape(Shape s) {
		shapes.add(s);
	}
	public Shape selectedShape() {
		Iterator<Shape> iterator = shapes.iterator();
		while (iterator.hasNext()) {
			Shape s=iterator.next();
			if (s.isSelected()) {
				return s; 
			}
		}
		
		return null;
	}
	public int getIndex(Shape s) {
		return this.shapes.indexOf(s);
	}
	public void remove(Shape shape) {
		this.shapes.remove(shape);
		
	}
	public void changePositions(int index1, int index2) {
		Collections.swap(this.shapes, index1, index2);
	}
	public void changeList(List<Shape> shapes) {
		this.shapes = (ArrayList<Shape>)shapes;
		
	}
}
