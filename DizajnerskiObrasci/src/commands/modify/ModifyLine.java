package commands.modify;

import commands.Command;
import mvc.Model;
import old_project.geometry.Line;

public class ModifyLine implements Command {

	private Model model;
	private Line target;
	private Line source;
	private Line holder;
	@Override
	public void execute() {
		for (int i = 0; i < model.getShapes().size(); i++) { 		      
			if (model.getShapes().get(i).isSelected()) {
				source = (Line)model.getShapes().get(i);
			}
		}
		
		holder = (Line)source.clone();
		source.setStartPoint(target.getStartPoint());
		source.setEndPoint(target.getEndPoint());
		source.setColor(target.getColor());
	}

	@Override
	public void unexecute() {
		source.setStartPoint(holder.getStartPoint());
		source.setEndPoint(holder.getEndPoint());
		source.setColor(holder.getColor());
	}

	public ModifyLine(Model mo, Line t) {
		target=t;
		model=mo;
	}
	
	@Override
	public String toString() {
		return "MODIFY-LINE " + source.toString() + " " + target.toString();
	}

}
