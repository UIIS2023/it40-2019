package commands;

import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class Top implements Command {

	Model model;
	Shape shape;
	int index;
	public Top(Model model) {
		this.model= model;
	}
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				index=i;
				Shape s = model.getShapes().get(i);
		         model.remove(model.getShapes().get(i));
		         model.addShape(s);
		         shape=s;
		         break;
			}
		}
		
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.getShapes().add(index, shape);
	}
	
	@Override
	public String toString() {
		if (shape instanceof Point) {
			return "MOVED-TOP-POINT " + shape.toString()+ " index&" + index;
		} else 	if (shape instanceof Line) {
			return "MOVED-TOP-LINE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Rectangle) {
			return "MOVED-TOP-RECTANGLE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Donut) {
			return "MOVED-TOP-DONUT " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Circle) {
			return "MOVED-TOP-CIRCLE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof HexagonAdapter) {
			return "MOVED-TOP-HEXAGON " + shape.toString()+ " index&" + index;
		}
		
		return "";
	}

	
}
