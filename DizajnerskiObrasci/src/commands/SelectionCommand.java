package commands;

import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class SelectionCommand implements Command {

	private Model model;
	private Shape selectedShape;
	private int index;
	@Override
	public void execute() {
		selectedShape.setSelected(true);
		index = model.getIndex(selectedShape);
	}

	@Override
	public void unexecute() {
		selectedShape.setSelected(false);
		
	}

	public SelectionCommand(Model mo, Shape s) {
		this.model=mo;
		this.selectedShape = s;
	}
	
	@Override
	public String toString() {
		if (selectedShape instanceof Point) {
			return "SELECTED-POINT " + selectedShape.toString() + " index&" + index;
		} else 	if (selectedShape instanceof Line) {
			return "SELECTED-LINE " + selectedShape.toString() + " index&" + index;
		}  else 	if (selectedShape instanceof Rectangle) {
			return "SELECTED-RECTANGLE " + selectedShape.toString() + " index&" + index;
		}  else 	if (selectedShape instanceof Donut) {
			return "SELECTED-DONUT " + selectedShape.toString() + " index&" + index;
		}  else 	if (selectedShape instanceof Circle) {
			return "SELECTED-CIRCLE " + selectedShape.toString() + " index&" + index;
		}  else 	if (selectedShape instanceof HexagonAdapter) {
			return "SELECTED-HEXAGON " + selectedShape.toString() + " index&" + index;
		}
		return "";
	}
}
