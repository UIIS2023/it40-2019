package commands;

import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class Bottom implements Command {

	Model model;
	int index;
	Shape shape;
	public Bottom(Model model) {
		this.model = model;
	}
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				index=i;
		         model.changePositions(0, i);
		         shape = model.getShapes().get(i);
		         break;
			}
		}
	}

	@Override
	public void unexecute() {
		model.changePositions(0, index);
	}
	
	@Override
	public String toString() {
		if (shape instanceof Point) {
			return "MOVED-BOTTOM-POINT " + shape.toString()+ " index&" + index;
		} else 	if (shape instanceof Line) {
			return "MOVED-BOTTOM-LINE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Rectangle) {
			return "MOVED-BOTTOM-RECTANGLE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Donut) {
			return "MOVED-BOTTOM-DONUT " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Circle) {
			return "MOVED-BOTTOM-CIRCLE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof HexagonAdapter) {
			return "MOVED-BOTTOM-HEXAGON " + shape.toString()+ " index&" + index;
		}
		
		return "";
	}

}
