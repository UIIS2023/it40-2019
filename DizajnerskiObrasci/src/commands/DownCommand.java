package commands;

import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class DownCommand implements Command {

	int index;
	Model model;
	Shape shape;
	public DownCommand(Model model) {
		this.model=model;
	}
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				index=i;
		         model.changePositions(i-1, i);
		         shape = model.getShapes().get(i);
		         break;
			}
		}
		
	}

	@Override
	public void unexecute() {
		model.changePositions(index-1, index);
	}
	
	@Override
	public String toString() {
		if (shape instanceof Point) {
			return "MOVED-DOWN-POINT " + shape.toString()+ " index&" + index;
		} else 	if (shape instanceof Line) {
			return "MOVED-DOWN-LINE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Rectangle) {
			return "MOVED-DOWN-RECTANGLE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Donut) {
			return "MOVED-DOWN-DONUT " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof Circle) {
			return "MOVED-DOWN-CIRCLE " + shape.toString()+ " index&" + index;
		}  else 	if (shape instanceof HexagonAdapter) {
			return "MOVED-DOWN-HEXAGON " + shape.toString()+ " index&" + index;
		}
		
		return "";
	}

}
