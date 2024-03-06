package commands.modify;

import commands.Command;
import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Point;

public class ModifyHexagon implements Command {

	private Model model;
	private HexagonAdapter target;
	private HexagonAdapter source;
	private HexagonAdapter holder;
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) { 		      
			if (model.getShapes().get(i).isSelected()) {
				source = (HexagonAdapter)model.getShapes().get(i);
			}
		}
		
		holder = (HexagonAdapter)source.clone();
		source.setX(target.getX());
		source.setY(target.getY());
		source.setRadius(target.getRadius());
		source.setInnerColor(target.getInnerColor());
		source.setColor(target.getColor());
	}

	@Override
	public void unexecute() {
		source.setX(holder.getX());
		source.setY(holder.getY());
		source.setRadius(holder.getRadius());
		source.setInnerColor(holder.getInnerColor());
		source.setColor(holder.getColor());
	}

	public ModifyHexagon(Model mo, HexagonAdapter t) {
		target=t;
		model=mo;
	}
	
	@Override
	public String toString() {
		return "MODIFY-HEXAGON " + source.toString() + " " + target.toString();
	}
}
