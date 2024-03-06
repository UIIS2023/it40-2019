package commands;

import java.util.ArrayList;
import java.util.List;

import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class UnSelectionCommand implements Command {

	private Model model;
	private List<Shape> selectedShapes;
	private Shape shapeToUnselect = null;
	public void setShapeToUnselect(Shape s) {
		this.shapeToUnselect = s;
	}
	@Override
	public void execute() {
		
		selectedShapes =new ArrayList<Shape>();
		if (shapeToUnselect == null) {
			model.getShapes().forEach(s -> {
				if (s.isSelected()) {
					s.setSelected(false);
					selectedShapes.add(s);
				}
			});
		} else {
			shapeToUnselect.setSelected(false);
			selectedShapes.add(shapeToUnselect);
		}
	}

	@Override
	public void unexecute() {
		selectedShapes.forEach(s -> {
			s.setSelected(true);
		});
	}
	
	public UnSelectionCommand(Model mo) {
		model = mo;
	}
	
	@Override
	public String toString() {
		String str = "UNSELECTED ";
		if (shapeToUnselect != null) {
			str += "on index " + model.getIndex(shapeToUnselect);
		} else {
			for(Shape s : selectedShapes) {
				if (s instanceof Point) {
					str+= "POINT " + s.toString() + "@";
				} else 	if (s instanceof Line) {
					str+="LINE " + s.toString()+ "@";
				}  else 	if (s instanceof Rectangle) {
					str+="RECTANGLE " + s.toString()+ "@";
				}  else 	if (s instanceof Donut) {
					str+="DONUT " + s.toString()+ "@";
				}  else 	if (s instanceof Circle) {
					str+="CIRCLE " + s.toString()+ "@";
				}  else 	if (s instanceof HexagonAdapter) {
					str+= "HEXAGON " + s.toString()+ "@";
				}
			};
		}
		return str;
	}

}
