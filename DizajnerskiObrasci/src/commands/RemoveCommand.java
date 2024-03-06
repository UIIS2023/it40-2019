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

public class RemoveCommand implements Command {

	private Model model;
	List<Shape> oldState;
	@Override
	public void execute() {
		oldState = new ArrayList<Shape>();
		model.getShapes().forEach(s -> {
			oldState.add(s);
		});
		
		oldState.forEach(s -> {
			if (s.isSelected()) {
				model.remove(s);
			}
		});
		
	}

	@Override
	public void unexecute() {
		model.changeList(oldState);
	}

	public RemoveCommand (Model model) {
		this.model =model;
	}
	
	@Override
	public String toString() {
		String str = "REMOVED ";
		
		for(Shape s : oldState) {
			if (s.isSelected()) {
				if (s instanceof Point) {
					str+= "POINT " + s.toString();
				} else 	if (s instanceof Line) {
					str+="LINE " + s.toString();
				}  else 	if (s instanceof Rectangle) {
					str+="RECTANGLE " + s.toString();
				}  else 	if (s instanceof Donut) {
					str+="DONUT " + s.toString();
				}  else 	if (s instanceof Circle) {
					str+="CIRCLE " + s.toString();
				}  else 	if (s instanceof HexagonAdapter) {
					str+= "HEXAGON " + s.toString();
				}
			}
		};
		return str;
	}
}
