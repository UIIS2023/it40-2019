package file;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
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
import hexagon.HexagonAdapter;
import mvc.Model;
import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

public class NextLogManager {
	
	public NextLogManager(Model model) {
		this.model = model;
	}

	private ArrayList<String> logs = new ArrayList<String>();
	private Model model;
	
	public Command getPoint(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Point p=new Point();
		p.setX(Integer.parseInt(attributes[0]));
		p.setY(Integer.parseInt(attributes[1]));
		p.setColor(new Color(Integer.parseInt(attributes[2])));
		AddCommand c = new AddCommand(p, model);
		return c;
	}
	
	public Command getLine(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Line line=new Line();
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		Point e = new Point();
		e.setX(Integer.parseInt(attributes[2]));
		e.setY(Integer.parseInt(attributes[3]));
		
		line.setStartPoint(s);
		line.setEndPoint(e);
		line.setColor(new Color(Integer.parseInt(attributes[4])));
		AddCommand c = new AddCommand(line, model);
		return c;
	}
	
	public Command getHex(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		HexagonAdapter hex=new HexagonAdapter();
		
		hex.setX(Integer.parseInt(attributes[0]));
		hex.setY(Integer.parseInt(attributes[1]));
		hex.setRadius(Integer.parseInt(attributes[2]));
		hex.setColor(new Color(Integer.parseInt(attributes[3])));
		hex.setInnerColor(new Color(Integer.parseInt(attributes[4])));
		AddCommand c = new AddCommand(hex, model);
		return c;
	}
	
	public Command getRect(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Rectangle rectangle=new Rectangle();
		
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		rectangle.setUpperLeftPoint(s);
		rectangle.setWidth(Integer.parseInt(attributes[2]));
		rectangle.setHeight(Integer.parseInt(attributes[3]));
		rectangle.setColor(new Color(Integer.parseInt(attributes[4])));
		rectangle.setInnerColor(new Color(Integer.parseInt(attributes[5])));
		AddCommand c = new AddCommand(rectangle, model);
		return c;
	}
	
	public Command getDonut(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Donut donut = new Donut();
		
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		donut.setCenter(s);
		try {
			donut.setRadius(Integer.parseInt(attributes[2]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		donut.setColor(new Color(Integer.parseInt(attributes[3])));
		donut.setInnerColor(new Color(Integer.parseInt(attributes[4])));
		donut.setInnerRadius(Integer.parseInt(attributes[5]));
		AddCommand c = new AddCommand(donut, model);
		return c;
	}
	
	public Command getCircle(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Donut donut = new Donut();
		
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		donut.setCenter(s);
		try {
			donut.setRadius(Integer.parseInt(attributes[2]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setColor(new Color(Integer.parseInt(attributes[3])));
		donut.setInnerColor(new Color(Integer.parseInt(attributes[4])));
		AddCommand c = new AddCommand(donut, model);
		return c;
	}
	
	public Command modifyPoint(String log) {
		String attributesText=log.split(" ")[2];
		String[] attributes = attributesText.split(",");
		Point p=new Point();
		p.setX(Integer.parseInt(attributes[0]));
		p.setY(Integer.parseInt(attributes[1]));
		p.setColor(new Color(Integer.parseInt(attributes[2])));
		ModifyPoint c = new ModifyPoint(model, p);
		return c;
	}
	
	public Command modifyLine(String log) {
		String attributesText=log.split(" ")[2];
		String[] attributes = attributesText.split(",");
		Line line=new Line();
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		Point e = new Point();
		e.setX(Integer.parseInt(attributes[2]));
		e.setY(Integer.parseInt(attributes[3]));
		
		line.setStartPoint(s);
		line.setEndPoint(e);
		line.setColor(new Color(Integer.parseInt(attributes[4])));
		ModifyLine c = new ModifyLine(model, line);
		return c;
	}
	
	public Command modifyHex(String log) {
		String attributesText=log.split(" ")[2];
		String[] attributes = attributesText.split(",");
		HexagonAdapter hex=new HexagonAdapter();
		
		hex.setX(Integer.parseInt(attributes[0]));
		hex.setY(Integer.parseInt(attributes[1]));
		hex.setRadius(Integer.parseInt(attributes[2]));
		hex.setColor(new Color(Integer.parseInt(attributes[3])));
		hex.setInnerColor(new Color(Integer.parseInt(attributes[4])));
		ModifyHexagon c = new ModifyHexagon(model, hex);
		return c;
	}
	
	public Command modifyRect(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Rectangle rectangle=new Rectangle();
		
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		rectangle.setUpperLeftPoint(s);
		rectangle.setWidth(Integer.parseInt(attributes[2]));
		rectangle.setHeight(Integer.parseInt(attributes[3]));
		rectangle.setColor(new Color(Integer.parseInt(attributes[4])));
		rectangle.setInnerColor(new Color(Integer.parseInt(attributes[5])));
		ModifyRectangle c = new ModifyRectangle(model, rectangle);
		return c;
	}
	
	public Command modifyDonut(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Donut donut = new Donut();
		
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		donut.setCenter(s);
		try {
			donut.setRadius(Integer.parseInt(attributes[2]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		donut.setColor(new Color(Integer.parseInt(attributes[3])));
		donut.setInnerColor(new Color(Integer.parseInt(attributes[4])));
		donut.setInnerRadius(Integer.parseInt(attributes[5]));
		ModifyDonut c = new ModifyDonut(model, donut);
		return c;
	}
	
	public Command modifyCircle(String log) {
		String attributesText=log.split(" ")[1];
		String[] attributes = attributesText.split(",");
		Donut donut = new Donut();
		
		Point s = new Point();
		s.setX(Integer.parseInt(attributes[0]));
		s.setY(Integer.parseInt(attributes[1]));
		donut.setCenter(s);
		try {
			donut.setRadius(Integer.parseInt(attributes[2]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setColor(new Color(Integer.parseInt(attributes[3])));
		donut.setInnerColor(new Color(Integer.parseInt(attributes[4])));
		ModifyCircle c = new ModifyCircle(model, donut);
		return c;
	}

	public void setList(ArrayList<String> list) {
	this.logs =list;
		
	}

	public String getNext() {
		if (this.logs.isEmpty()) return null;
		return this.logs.get(0);
	}
	
	public String removeNext() {
		return this.logs.remove(0);
	}

	public Command select(String log) {
		String attributesText=log.split("&")[1];
		int index = Integer.parseInt(attributesText);
		SelectionCommand c = new SelectionCommand(model, model.getShapes().get(index));
		return c;
		
	}

	public Command moveUp(String log) {
		UpCommand c =new UpCommand(model);
		return c;
		
	}
	public Command moveDown(String log) {
		DownCommand c =new DownCommand(model);
		return c;
		
	}
	public Command moveTop(String log) {
		Top c =new Top(model);
		return c;
		
	}
	public Command moveBottom(String log) {
		Bottom c =new Bottom(model);
		return c;
		
	}

	public Command remove() {
		RemoveCommand c=new RemoveCommand(model);
		return c;
	}
	
	public Command unselect(String log) {
		UnSelectionCommand c=new UnSelectionCommand(model);
		if (!log.contains("@")) {
			String index = log.split(" ")[3];
			int indexInt = Integer.parseInt(index);
			Shape s = model.getShapes().get(indexInt);
			c.setShapeToUnselect(s);
		}
			
		return c;
		
	}

	public void restart() {
		logs =new ArrayList<String>();
	}
}
