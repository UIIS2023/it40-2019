package undo;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class UndoManager {

	private List<Command> commandsUndo= new ArrayList<Command>();
	private List<Command> commandsRedo= new ArrayList<Command>();
	
	public void addToUndoList(Command command) {
		commandsUndo.add(command);
		commandsRedo.removeAll(commandsRedo);
	}
	
	public Command executeUndo() {
		Command c=commandsUndo.get(commandsUndo .size()-1);
		commandsUndo.remove(c);
		commandsRedo.add(c);
		return c;
	}
	
	public Command executeRedo() {
		Command c=commandsRedo.get(commandsRedo.size()-1);
		commandsRedo.remove(c);
		commandsUndo.add(c);
		return c;
	}

	public int sizeUndo() {
		return commandsUndo.size();
	}
	public int sizeRedo() {
		return commandsRedo.size();
	}

	public void restart() {
		commandsRedo = new ArrayList<Command>();
		commandsUndo= new ArrayList<Command>();
		
	}
}
