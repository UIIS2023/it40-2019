package commands.modify;

import commands.Command;
import mvc.Model;
import old_project.geometry.Donut;
import old_project.geometry.Point;

public class ModifyDonut implements Command {
	private Model model;
	private Donut target;
	private Donut source;
	private Donut holder;
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) { 		      
			if (model.getShapes().get(i).isSelected()) {
				source = (Donut)model.getShapes().get(i);
			}
		}
		
		holder = (Donut)source.clone();
		source.setCenter(target.getCenter());
		try {
			source.setRadius(target.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		source.setInnerRadius(target.getInnerRadius());
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
		source.setInnerRadius(holder.getInnerRadius());
		source.setInnerColor(holder.getInnerColor());
		source.setColor(holder.getColor());
	}

	public ModifyDonut(Model mo, Donut t) {
		target=t;
		model=mo;
	}
	
	@Override
	public String toString() {
		return "MODIFY-DONUT " + source.toString() + " " + target.toString();
	}

}
