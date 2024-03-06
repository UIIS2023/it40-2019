package commands.modify;

import commands.Command;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Point;

public class ModifyCircle implements Command {
	private Model model;
	private Circle target;
	private Circle source;
	private Circle holder;
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) { 		      
			if (model.getShapes().get(i).isSelected()) {
				source = (Circle)model.getShapes().get(i);
			}
		}
		
		holder = (Circle)source.clone();
		System.out.println(holder.getColor());
		System.out.println(source.getColor());
		source.setCenter(target.getCenter());
		try {
			source.setRadius(target.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		source.setInnerColor(target.getInnerColor());
		source.setColor(target.getColor());
	}

	@Override
	public void unexecute() {
		source.setCenter(holder.getCenter());
		try {
			source.setRadius(holder.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(holder.getColor());
		source.setInnerColor(holder.getInnerColor());
		source.setColor(holder.getColor());
	}

	public ModifyCircle(Model mo, Circle t) {
		target=t;
		model=mo;
	}
	
	@Override
	public String toString() {
		return "MODIFY-CIRCLE " + source.toString() + " " + target.toString();
	}
}
