package mvc;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import commands.AddCommand;
import commands.Bottom;
import commands.Command;
import commands.DownCommand;
import commands.RemoveCommand;
import commands.SelectionCommand;
import commands.Top;
import commands.UnSelectionCommand;
import commands.UpCommand;
import commands.modify.ModifyCircle;
import commands.modify.ModifyDonut;
import commands.modify.ModifyHexagon;
import commands.modify.ModifyLine;
import commands.modify.ModifyPoint;
import commands.modify.ModifyRectangle;
import file.NextLogManager;
import file.SaveStrategy;
import file.SaveStrategyContext;
import file.impl.SaveLogStrategy;
import file.impl.SaveSerializedStrategy;
import hexagon.DlgHexagon;
import hexagon.HexagonAdapter;
import observer.Observable;
import observer.Observer;
import old_project.drawing.DlgCircle;
import old_project.drawing.DlgDonut;
import old_project.drawing.DlgLine;
import old_project.drawing.DlgPoint;
import old_project.drawing.DlgRect;
import old_project.drawing.FrmDrawing;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;
import undo.UndoManager;

public class Controller implements Observable {

	private List<Observer> observers = new ArrayList<Observer>();
	private Model model;
	private FrmDrawing frmDrawing;
	private Point firstClickedLine = null;
	private UndoManager undoManager= new UndoManager();
	private String logForObserver = "";
	private SaveStrategyContext context = new SaveStrategyContext();
	private NextLogManager nextLogManager = null;
	public void setModel(Model model) {
		this.model = model;
		nextLogManager = new NextLogManager(model);
	}
	public void setFrmDrawing(FrmDrawing frmDrawing) {
		this.frmDrawing = frmDrawing;
		addObserver(frmDrawing);
	}
	public void whiteSurfaceClicked() {
		if (frmDrawing.getTglbtnPoint().isSelected()) {
			drawPoint();
		} 
		if (frmDrawing.getTglbtnLine().isSelected()) {
			drawLine();
		} 
		if (frmDrawing.getTglbtnRectangle().isSelected()) {
			drawRectangle();
		} 
		if (frmDrawing.getTglbtnCircle().isSelected()) {
			drawCircle();
		} 
		if (frmDrawing.getTglbtnDonut().isSelected()) {
			drawDonut();
		}
		if (frmDrawing.getTglbtnHexagon().isSelected()) {
			drawHexagon();
		}
		if (frmDrawing.getTglbtnSelection().isSelected()) {
			select();
		}
		
		frmDrawing.repaint();
	}
	
	private void drawPoint() {
		Point p = frmDrawing.getClickedPoint();
		p.setColor(frmDrawing.getShapeColor());
		AddCommand command = new AddCommand(p, model);
		command.execute();
		this.undoManager.addToUndoList(command);
		logForObserver = command.toString();
		notifyObservers();
	}
	
	private void drawLine() {
		if (firstClickedLine == null) {
			firstClickedLine = frmDrawing.getClickedPoint();
		} else {
			Point p = frmDrawing.getClickedPoint();
			Line line = new Line(firstClickedLine, p);
			line.setColor(frmDrawing.getShapeColor());
			AddCommand command = new AddCommand(line, model);
			command.execute();
			this.undoManager.addToUndoList(command);
			logForObserver = command.toString();
			notifyObservers();
			firstClickedLine = null;
		}
	}
	
	private void drawRectangle() {
		int x = frmDrawing.getClickedPoint().getX();
		int y = frmDrawing.getClickedPoint().getY();
		DlgRect dlgR = new DlgRect();			
		dlgR.getTxtX().setText(String.valueOf(x));
		dlgR.getTxtY().setText(String.valueOf(y));
		dlgR.getTxtX().setEditable(false);
		dlgR.getTxtY().setEditable(false);
		dlgR.setColor(frmDrawing.getShapeColor());
		dlgR.setInnerColor(frmDrawing.getShapeInnerColor());
		dlgR.setVisible(true);
		if (dlgR.isOk()) {
			try {
				String width = dlgR.getTxtWidth().getText().toString();
				int intWidth = Integer.parseInt(width);
				String height = dlgR.getTxtHeight().getText().toString();
				int intHeight = Integer.parseInt(height);	
				Color c = dlgR.getColor();
				Color innerC = dlgR.getInnerColor();
				Rectangle r = new Rectangle(new Point(x, y), intWidth, intHeight, false, c, innerC);
				AddCommand command = new AddCommand(r, model);
				command.execute();
				this.undoManager.addToUndoList(command);
				logForObserver = command.toString();
				notifyObservers();
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "You need to enter numbers!");
			}
		}
	}
	
	private void drawCircle() {
		int x = frmDrawing.getClickedPoint().getX();
		int y = frmDrawing.getClickedPoint().getY();
		DlgCircle dlgC = new DlgCircle();
		dlgC.getTxtXc().setText(String.valueOf(x));
		dlgC.getTxtYc().setText(String.valueOf(y));
		dlgC.getTxtXc().setEditable(false);
		dlgC.getTxtYc().setEditable(false);
		dlgC.setColor(frmDrawing.getShapeColor());
		dlgC.setInnerColor(frmDrawing.getShapeInnerColor());
		dlgC.setVisible(true);
		
		if (dlgC.isOk()) {
			String radius = dlgC.getTxtR().getText().toString();
			int intRadius = Integer.parseInt(radius);	
			Color c = dlgC.getColor();
			Color innerC = dlgC.getInnerColor();
			Circle circle = new Circle(new Point(x, y), intRadius, false, c, innerC);
			AddCommand command = new AddCommand(circle, model);
			command.execute();
			this.undoManager.addToUndoList(command);
			logForObserver = command.toString();
			notifyObservers();
		}
	}
	
	private void drawDonut() {
		int x = frmDrawing.getClickedPoint().getX();
		int y = frmDrawing.getClickedPoint().getY();
		DlgDonut dlgD = new DlgDonut();
		dlgD.getTxtXc().setText(String.valueOf(x));
		dlgD.getTxtYc().setText(String.valueOf(y));
		dlgD.getTxtXc().setEditable(false);
		dlgD.getTxtYc().setEditable(false);
		dlgD.setColor(frmDrawing.getShapeColor());
		dlgD.setInnerColor(frmDrawing.getShapeInnerColor());
		dlgD.setVisible(true);
		
		if (dlgD.isOk()) {
			Color c = dlgD.getColor();
			Color innerC = dlgD.getInnerColor();
			Donut d = new Donut(new Point(x, y), Integer.parseInt(dlgD.getTxtR().getText().toString()), Integer.parseInt(dlgD.getTxtIR().getText().toString()), false, c, innerC);
			AddCommand command = new AddCommand(d, model);
			command.execute();
			this.undoManager.addToUndoList(command);
			logForObserver = command.toString();
			notifyObservers();
		}
	}
	private void drawHexagon() {
		int x = frmDrawing.getClickedPoint().getX();
		int y = frmDrawing.getClickedPoint().getY();
		DlgHexagon dlgH = new DlgHexagon();
		dlgH.getTxtXc().setText(String.valueOf(x));
		dlgH.getTxtYc().setText(String.valueOf(y));
		dlgH.getTxtXc().setEditable(false);
		dlgH.getTxtYc().setEditable(false);
		dlgH.setColor(frmDrawing.getShapeColor());
		dlgH.setInnerColor(frmDrawing.getShapeInnerColor());
		dlgH.setVisible(true);
		
		if (dlgH.isOk()) {
			Color c = dlgH.getColor();
			Color innerC = dlgH.getInnerColor();
			HexagonAdapter h = new HexagonAdapter(new Point(x, y), Integer.parseInt(dlgH.getTxtR().getText().toString()), false, c, innerC);
			AddCommand command = new AddCommand(h, model);
			command.execute();
			this.undoManager.addToUndoList(command);
			logForObserver = command.toString();
			notifyObservers();
		}
	}
	public void select() {
		boolean shapeContainingPointFound = false;
		int selectedNumber = 0;
		List<Shape> list = model.getShapes();
		for (int i= list.size() - 1; i >= 0; i--) {
			Shape shape = list.get(i);
			if (shape.contains(frmDrawing.getClickedPoint().getX(), frmDrawing.getClickedPoint().getY())) {
				shapeContainingPointFound = true;
				if(shape.isSelected() == false) {
					SelectionCommand command= new SelectionCommand(model,shape);
					command.execute();
					this.undoManager.addToUndoList(command);
					logForObserver = command.toString();
					notifyObservers();
					return;
				}
				else {
					UnSelectionCommand command =new UnSelectionCommand(model);
					command.setShapeToUnselect(shape);
					command.execute();
					this.undoManager.addToUndoList(command);
					logForObserver = command.toString();
					notifyObservers();
					return;
				}
			}
			
			if (shape.isSelected()) {
				selectedNumber++;
			}
		}
		
		if (shapeContainingPointFound == false) {
			if (selectedNumber == 0) return; 
			UnSelectionCommand command = new UnSelectionCommand(model);
			command.execute();
			this.undoManager.addToUndoList(command);
			logForObserver = command.toString();
			notifyObservers();
		} 
	}
	public void modify() {
		
		if (model.getShapes().isEmpty())
			JOptionPane.showMessageDialog(null, "You need to add shape first!");
		else if (model.selectedShape() == null)
			JOptionPane.showMessageDialog(null, "You need to select a shape!");
		else
		{
			for (int i = 0; i < model.getShapes().size(); i++) { 		      
				if (model.getShapes().get(i).isSelected() && model.getShapes().get(i) instanceof Circle && (model.getShapes().get(i) instanceof Donut == false))
				{
					DlgCircle dlg = new DlgCircle();
					Circle temp = (Circle) model.getShapes().get(i);		
					dlg.getTxtXc().setText(String.valueOf(temp.getCenter().getX()));
					dlg.getTxtYc().setText(String.valueOf(temp.getCenter().getY()));
					dlg.getTxtR().setText(String.valueOf(temp.getRadius()));
					dlg.getBtnColor().setBackground(temp.getColor());
					dlg.getBtnInnerColor().setBackground(temp.getInnerColor());
					dlg.setTitle("Modify circle");
					dlg.setVisible(true);
					if (dlg.isOk()) {
					Circle c = new Circle(new Point(Integer.parseInt(dlg.getTxtXc().getText().toString()), Integer.parseInt(dlg.getTxtYc().getText().toString())), Integer.parseInt(dlg.getTxtR().getText().toString()), false, dlg.getBtnColor().getBackground(), dlg.getBtnInnerColor().getBackground());
					ModifyCircle command=new ModifyCircle(model, c);
					undoManager.addToUndoList(command);
					command.execute();
					logForObserver = command.toString();
					notifyObservers();
				}
			} 
				else if (model.getShapes().get(i).isSelected() && model.getShapes().get(i) instanceof Donut) {				
					DlgDonut dlg = new DlgDonut();
					Donut temp = (Donut) model.getShapes().get(i);		
					dlg.getTxtXc().setText(String.valueOf(temp.getCenter().getX()));
					dlg.getTxtYc().setText(String.valueOf(temp.getCenter().getY()));
					dlg.getTxtR().setText(String.valueOf(temp.getRadius()));
					dlg.getTxtIR().setText(String.valueOf(temp.getInnerRadius()));
					dlg.getBtnColor().setBackground(temp.getColor());
					dlg.getBtnInnerColor().setBackground(temp.getInnerColor());
					dlg.setTitle("Modify donut");
					dlg.setVisible(true);
					if (dlg.isOk()) {
					Donut d = new Donut(new Point(Integer.parseInt(dlg.getTxtXc().getText().toString()), Integer.parseInt(dlg.getTxtYc().getText().toString())), Integer.parseInt(dlg.getTxtR().getText().toString()), Integer.parseInt(dlg.getTxtIR().getText().toString()), false, dlg.getBtnColor().getBackground(), dlg.getBtnInnerColor().getBackground());
					ModifyDonut command =new ModifyDonut(model, d);
					command.execute();
					undoManager.addToUndoList(command);
					logForObserver = command.toString();
					notifyObservers();
				}
			
//			
			} else if (model.getShapes().get(i).isSelected() && model.getShapes().get(i) instanceof Rectangle)
			{
				DlgRect dlg = new DlgRect();			
				Rectangle temp = (Rectangle) model.getShapes().get(i);			
				dlg.getTxtY().setText(String.valueOf(temp.getUpperLeftPoint().getY()));
				dlg.getTxtX().setText(String.valueOf(temp.getUpperLeftPoint().getX()));
				dlg.getTxtWidth().setText(String.valueOf(temp.getWidth()));
				dlg.getTxtHeight().setText(String.valueOf(temp.getHeight()));
				dlg.getBtnColor().setBackground(temp.getColor());
				dlg.getBtnInnerColor().setBackground(temp.getInnerColor());
				dlg.setTitle("Modify rectangle");
				dlg.setVisible(true);
				if (dlg.isOk()) {
					Rectangle r = new Rectangle(new Point(Integer.parseInt(dlg.getTxtX().getText().toString()), Integer.parseInt(dlg.getTxtY().getText().toString())), Integer.parseInt(dlg.getTxtWidth().getText().toString()), Integer.parseInt(dlg.getTxtHeight().getText().toString()), false, dlg.getBtnColor().getBackground(), dlg.getBtnInnerColor().getBackground());
					ModifyRectangle command = new ModifyRectangle(model, r);
					command.execute();
					logForObserver = command.toString();
					undoManager.addToUndoList(command);
					notifyObservers();
				}
			} else if (model.getShapes().get(i).isSelected() && model.getShapes().get(i) instanceof Point) {
				DlgPoint dlg = new DlgPoint();
				Point temp = (Point) model.getShapes().get(i);		
				dlg.getTxtX().setText(String.valueOf(temp.getX()));
				dlg.getTxtY().setText(String.valueOf(temp.getY()));
				dlg.getBtnColor().setBackground(temp.getColor());
				dlg.setTitle("Modify point");
				dlg.setVisible(true);
				if (dlg.isOk()) {
						Point p3 = new Point(Integer.parseInt(dlg.getTxtX().getText().toString()), Integer.parseInt(dlg.getTxtY().getText().toString()), false, dlg.getBtnColor().getBackground());
						ModifyPoint comm=new ModifyPoint(model, p3);
						comm.execute();
						logForObserver = comm.toString();
						undoManager.addToUndoList(comm);
						notifyObservers();
				}
			} else if (model.getShapes().get(i).isSelected() && model.getShapes().get(i) instanceof Line) {
				DlgLine dlg = new DlgLine();
				Line temp = (Line) model.getShapes().get(i);		
				dlg.getTxtX1().setText(String.valueOf(temp.getStartPoint().getX()));
				dlg.getTxtX2().setText(String.valueOf(temp.getEndPoint().getX()));
				dlg.getTxtY1().setText(String.valueOf(temp.getStartPoint().getY()));
				dlg.getTxtY2().setText(String.valueOf(temp.getEndPoint().getY()));
				dlg.getBtnColor().setBackground(temp.getColor());
				dlg.setTitle("Modify line");
				dlg.setVisible(true);
				if (dlg.isOk()) {
						Line l = new Line(new Point(Integer.parseInt(dlg.getTxtX1().getText().toString()), Integer.parseInt(dlg.getTxtY1().getText().toString())), new Point(Integer.parseInt(dlg.getTxtX2().getText().toString()), Integer.parseInt(dlg.getTxtY2().getText().toString())), false, dlg.getBtnColor().getBackground());
						ModifyLine comm=new ModifyLine(model, l);
						comm.execute();
						logForObserver = comm.toString();
						undoManager.addToUndoList(comm);
						notifyObservers();
				}
			} else if (model.getShapes().get(i).isSelected() && model.getShapes().get(i) instanceof HexagonAdapter) {
				DlgHexagon dlg = new DlgHexagon();
				HexagonAdapter temp = (HexagonAdapter) model.getShapes().get(i);		
				dlg.getTxtXc().setText(String.valueOf(temp.getX()));
				dlg.getTxtYc().setText(String.valueOf(temp.getY()));
				dlg.getTxtR().setText(String.valueOf(temp.getRadius()));
				dlg.getBtnColor().setBackground(temp.getColor());
				dlg.getBtnInnerColor().setBackground(temp.getInnerColor());
				dlg.setTitle("Modify hexagon");
				dlg.setVisible(true);
				if (dlg.isOk()) {
				HexagonAdapter h = new HexagonAdapter(new Point(Integer.parseInt(dlg.getTxtXc().getText().toString()), Integer.parseInt(dlg.getTxtYc().getText().toString())), Integer.parseInt(dlg.getTxtR().getText().toString()), false, dlg.getBtnColor().getBackground(), dlg.getBtnInnerColor().getBackground());
				ModifyHexagon command =new ModifyHexagon(model, h);
				command.execute();
				logForObserver = command.toString();
				undoManager.addToUndoList(command);
				notifyObservers();
			}
			}
			frmDrawing.repaint();
		}
		
		}
	}
	public void up() {
		UpCommand command = new UpCommand(model);
		command.execute();
		this.undoManager.addToUndoList(command);
		frmDrawing.repaint();
		logForObserver = command.toString();
		notifyObservers();
	}
	public void down() {
		DownCommand command = new DownCommand(model);
		command.execute();
		this.undoManager.addToUndoList(command);
		frmDrawing.repaint();
		logForObserver = command.toString();
		notifyObservers();
	}
	public void top() {
		Top top =new Top(model);
		top.execute();
		this.undoManager.addToUndoList(top);
		frmDrawing.repaint();
		logForObserver = top.toString();
		notifyObservers();
	}
	public void bottom() {
		Bottom bottom = new Bottom(model);
		bottom.execute();
		this.undoManager.addToUndoList(bottom);
		frmDrawing.repaint();
		logForObserver = bottom.toString();
		notifyObservers();
		
	}
	public void undo() {
		Command c=this.undoManager.executeUndo();
		c.unexecute();
		frmDrawing.repaint();
		logForObserver = "UNDO";
		notifyObservers();
	}
	public void redo() {
		Command c=this.undoManager.executeRedo();
		c.execute();
		frmDrawing.repaint();
		logForObserver = "REDO";
		notifyObservers();
	}
	
	public void delete() {
		RemoveCommand command = new RemoveCommand(model);
		command.execute();
		this.undoManager.addToUndoList(command);
		frmDrawing.repaint();
		logForObserver = command.toString();
		notifyObservers();
	}
	@Override
	public void addObserver(Observer observer) {
		
		observers.add(observer);
	}
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		
	}
	@Override
	public void notifyObservers() {
		
		observers.forEach(o -> {
			if (logForObserver != "") {
				o.saveLog(logForObserver);
				logForObserver = "";
			}
			
			ArrayList<Boolean> enableList = new ArrayList<>();
			boolean up = false;
			boolean down = false;
			
			ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
			model.getShapes().forEach(s -> {
				if (s.isSelected())
					selectedShapes.add(s);
			});
			
			
			if (selectedShapes.size() == 1) {
				Shape selectedShape = selectedShapes.get(0);
				down = model.getShapes().indexOf(selectedShape) > 0;
				up = model.getShapes().indexOf(selectedShape) < model.getShapes().size() - 1;
			}
			
			boolean next = nextLogManager.getNext()!=null; 
			boolean undo = undoManager.sizeUndo() > 0;
			boolean redo = undoManager.sizeRedo() > 0;
			
			
			boolean select = model.getShapes().size() > 0;
			boolean modify = selectedShapes.size() == 1;
			boolean remove = selectedShapes.size() > 0;
			enableList.add(select);
			enableList.add(modify);
			enableList.add(remove);
			enableList.add(next);
			enableList.add(undo);
			enableList.add(redo);
			enableList.add(up);
			enableList.add(down);
			
			o.updateUI(enableList);
		});
	}
	public void save(String string) {
		if (string.equals("Log")) {
			SaveLogStrategy strategy = new SaveLogStrategy();
			strategy.setFrrm(frmDrawing);
			context.setStrategy(strategy);
			context.executeStrategy();
		} else {
			SaveStrategy strategy = new SaveSerializedStrategy(model);
			context.setStrategy(strategy);
			context.executeStrategy();
		}
		
	}
	public boolean read(String string) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (string.equals("Log")) {
				ArrayList<String> list = new ArrayList<String>();
				
				File selectedFile = jfc.getSelectedFile();
				Scanner myReader;
				try {
					myReader = new Scanner(selectedFile);
					 while (myReader.hasNextLine()) {
					        String data = myReader.nextLine();
					        list.add(data);
					      }
				      myReader.close();
				      model.getShapes().clear();
				      undoManager.restart();
					  nextLogManager.restart();
					  nextLogManager.setList(list);
					  frmDrawing.repaint();
					  notifyObservers();
					  return true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				ObjectInputStream objectinputstream = null;
				try {
				    FileInputStream streamIn = new FileInputStream(jfc.getSelectedFile().getAbsolutePath());
				    objectinputstream = new ObjectInputStream(streamIn);
				    List<Shape> value = (List<Shape>) objectinputstream.readObject();
				    model.getShapes().clear();
				    value.forEach(s-> {
				    	model.addShape(s);
				    });
				    undoManager.restart();
				    nextLogManager.restart();
				    objectinputstream.close();
				    frmDrawing.repaint();
				    notifyObservers();
				    return true;
				} catch (Exception e) {
				    e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}
	public void next() {
		String log = nextLogManager.getNext();
		if (log.startsWith("SELECTED") ) {
			Command c =nextLogManager.select(log);
			this.undoManager.addToUndoList(c);
			c.execute();
			
		} else if (log.startsWith("DRAW-POINT")) {
			Command c =nextLogManager.getPoint(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("DRAW-LINE")) {
			Command c =nextLogManager.getLine(log);
			c.execute();
			this.undoManager.addToUndoList(c);
		} else if (log.startsWith("DRAW-HEXAGON")) {
			Command c =nextLogManager.getHex(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("DRAW-RECTANGLE")) {
			Command c =nextLogManager.getRect(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("DRAW-DONUT")) {
			Command c =nextLogManager.getDonut(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("DRAW-CIRCLE")) {
			Command c =nextLogManager.getCircle(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("REMOVE")) {
			Command c =nextLogManager.remove();
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("UNSELECT")) {
			Command c =nextLogManager.unselect(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("MODIFY-POINT")) {
			Command c =nextLogManager.modifyPoint(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("MODIFY-LINE")) {
			Command c =nextLogManager.modifyLine(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("MODIFY-HEXAGON")) {
			Command c =nextLogManager.modifyHex(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("MODIFY-RECTANGLE")) {
			Command c =nextLogManager.modifyRect(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("MODIFY-DONUT")) {
			Command c =nextLogManager.modifyDonut(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("MODIFY-CIRCLE")) {
			Command c =nextLogManager.modifyCircle(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		}else if (log.startsWith("MOVED-UP")) {
			Command c =nextLogManager.moveUp(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		}else if (log.startsWith("MOVED-DOWN")) {
			Command c =nextLogManager.moveDown(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		}else if (log.startsWith("MOVED-TOP")) {
			Command c =nextLogManager.moveTop(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		}else if (log.startsWith("MOVED-BOTTOM")) {
			Command c =nextLogManager.moveBottom(log);
			this.undoManager.addToUndoList(c);
			c.execute();
		} else if (log.startsWith("UNDO")) {
			undo();
		} else if (log.startsWith("REDO")) {
			redo();
		}
		
		frmDrawing.repaint();
		nextLogManager.removeNext();
		logForObserver = log;
		notifyObservers();
	}
}