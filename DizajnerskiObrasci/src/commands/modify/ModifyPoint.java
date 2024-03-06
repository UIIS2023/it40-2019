package commands.modify;

import commands.Command;
import mvc.Model;
import old_project.geometry.Point;

public class ModifyPoint implements Command {

	private Model model;
	private Point target;
	private Point source;
	private Point holder;
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) { 		      
			if (model.getShapes().get(i).isSelected()) {
				source = (Point)model.getShapes().get(i);
			}
		}
		
		holder = (Point)source.clone();
		source.setX(target.getX());
		source.setY(target.getY());
		source.setColor(target.getColor());
	}

	@Override
	public void unexecute() {
		source.setX(holder.getX());
		source.setY(holder.getY());
		source.setColor(holder.getColor());
	}

	public ModifyPoint(Model mo, Point t) {
		target=t;
		model=mo;
	}
	
	@Override
	public String toString() {
		return "MODIFY-POINT " + source.toString() + " " + target.toString();
	}
}
