package commands.modify;

import commands.Command;
import mvc.Model;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;

public class ModifyRectangle implements Command {
	private Model model;
	private Rectangle target;
	private Rectangle source;
	private Rectangle holder;
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) { 		      
			if (model.getShapes().get(i).isSelected()) {
				source = (Rectangle)model.getShapes().get(i);
			}
		}
		
		holder = (Rectangle)source.clone();
		source.setUpperLeftPoint(target.getUpperLeftPoint());
		source.setColor(target.getColor());
		source.setInnerColor(target.getInnerColor());
		source.setHeight(target.getHeight());
		source.setWidth(target.getWidth());
	}

	@Override
	public void unexecute() {
		source.setUpperLeftPoint(holder.getUpperLeftPoint());
		source.setColor(holder.getColor());
		source.setInnerColor(holder.getInnerColor());
		source.setHeight(holder.getHeight());
		source.setWidth(holder.getWidth());
	}

	public ModifyRectangle(Model mo, Rectangle t) {
		target=t;
		model=mo;
	}
	
	@Override
	public String toString() {
		return "MODIFY-RECTANGLE " + source.toString() + " " + target.toString();
	}
}
