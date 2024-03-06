package mvc;

import java.awt.Color;
import java.awt.EventQueue;

import old_project.drawing.FrmDrawing;
import old_project.drawing.PnlDrawing;

public class Application {

	public static void main(String[] args) {
		Model m = new Model();
		Controller c = new Controller();
		PnlDrawing pnl = new PnlDrawing();
		pnl.setModel(m);
		pnl.setBackground(Color.white);
		FrmDrawing frm = new FrmDrawing(pnl);
		frm.setPnlDrawing(pnl);
		frm.setController(c);
		c.setFrmDrawing(frm);
		c.setModel(m);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm.setResizable(false);
					frm.setSize(800, 600);
					frm.setVisible(true);
					c.notifyObservers();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
