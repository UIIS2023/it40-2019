package old_project.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import old_project.geometry.Circle;
import old_project.geometry.Donut;
import old_project.geometry.Line;
import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.geometry.Shape;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mvc.Model;

import java.awt.BorderLayout;

public class PnlDrawing extends JPanel {
	
	private Model model;
	
	int check;
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	Shape selected;
	int x;
	int y;
	int x2;
	int y2;
	Point startPoint;
	/**
	 * Create the panel.
	 */
	public PnlDrawing() {	
		setLayout(new BorderLayout(0, 0));	
	} 
	
	public void paint(Graphics g) {
		super.paint(g);
		if (model!=null) {
			for (Shape shape : model.getShapes()) {
				shape.draw(g);
			}
		}
	}
	
	public void check(int c) {
		check = c;
	}
	
	public void delete() {	
			if (shapes.isEmpty())
				JOptionPane.showMessageDialog(null, "You need to add shape first!");
			else if (selected == null)
				JOptionPane.showMessageDialog(null, "You need to select a shape!");
			else
			{
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure?",
						"Delete shape", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirmation == 0) {
					for (int i = 0; i < shapes.size(); i++) { 		      
						if (shapes.get(i).isSelected()) {									
							shapes.remove(shapes.get(i));
							selected = null;
						}	
				      }
					getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
					for (int j = 0; j < shapes.size(); j++)
						shapes.get(j).draw(getGraphics());
						}
			}
		
	}
	public void deleteAll() {
		if (shapes.isEmpty())
			JOptionPane.showMessageDialog(null, "There is nothing to delete!");
		else
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure?",
					"Delete shape", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (confirmation == 0) {
				getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
				selected = null;
				shapes.clear();
		}
	}
	}
	public void selection(Graphics g) {
		for (int i = 0; i < shapes.size(); i++) { 		      
			if (shapes.get(i).contains(x, y)) {		
				selected = shapes.get(i);
				getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
				for (int j = 0; j < shapes.size(); j++) {
					shapes.get(j).setSelected(false);
					shapes.get(j).draw(getGraphics());
				}
				selected.setSelected(true);
				selected.draw(getGraphics());
			} 
		}
			}
	
	
	public void setModel(Model model) {
		this.model = model;
	}

}
