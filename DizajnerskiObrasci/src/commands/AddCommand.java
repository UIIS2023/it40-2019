package commands;

import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class AddCommand implements Command {

	Shape shape;
	Model model;
	public AddCommand(Shape shape,Model model) {
		this.shape = shape;
		this.model =model;
	}
	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
	}
	
	@Override
	public String toString() {
		if (shape instanceof Point) {
			return "DRAW-POINT " + shape.toString();
		} else 	if (shape instanceof Line) {
			return "DRAW-LINE " + shape.toString();
		}  else 	if (shape instanceof Rectangle) {
			return "DRAW-RECTANGLE " + shape.toString();
		}  else 	if (shape instanceof Donut) {
			return "DRAW-DONUT " + shape.toString();
		}  else 	if (shape instanceof Circle) {
			return "DRAW-CIRCLE " + shape.toString();
		}  else 	if (shape instanceof HexagonAdapter) {
			return "DRAW-HEXAGON " + shape.toString();
		}
		
		return "";
	}

}
