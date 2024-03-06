package old_project.drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mvc.Controller;
import mvc.Model;
import observer.Observer;
import old_project.geometry.Point;

import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.JColorChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class FrmDrawing extends JFrame implements Observer{

	private JPanel contentPane;
	private final ButtonGroup btnGroup = new ButtonGroup();
	private final ButtonGroup btnGroup2 = new ButtonGroup();
	private PnlDrawing drawing;
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnSelection;
	private JToggleButton tglbtnHexagon;
	JToolBar toolBar;
	JButton tglbtnDelete;
	JButton tglbtnModify;
	private Controller contr;
	private Point clickedPoint = null;
	private JPanel panel;
	private JButton upButton;
	private JButton downBtn;
	private JButton topBtn;
	private JButton bottomBtn;
	private JPanel shapeColor;
	private JPanel shapeInnerColor;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JList list;
	private DefaultListModel<String> listModel = new DefaultListModel();
	private JButton undoButton;
	private JButton redoButton;
	private JLabel lblNewLabel_2;
	private JButton btnLog;
	private JButton btnSerialized;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Create the frame.
	 */
	public FrmDrawing(PnlDrawing drawing) {
		this.drawing = drawing;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1200, 800);
		contentPane = new JPanel();
		setTitle("Petrov Dejan IT40-2019");
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			
		getContentPane().setBackground(new Color(238, 238, 238));
		getContentPane().add(drawing, BorderLayout.CENTER);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		tglbtnSelection = new JToggleButton("Selection");
		btnGroup.add(tglbtnSelection);
		toolBar.add(tglbtnSelection);
		
		tglbtnPoint = new JToggleButton("Point");
		toolBar.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		toolBar.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		toolBar.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		toolBar.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		toolBar.add(tglbtnDonut);
		
		tglbtnModify = new JButton("Modify");
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		btnGroup.add(tglbtnHexagon);
		toolBar.add(tglbtnHexagon);

		toolBar.add(tglbtnModify);
		
		tglbtnDelete = new JButton("Delete");
		
		toolBar.add(tglbtnDelete);
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnCircle);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{47, 61, 0};
		gbl_panel.rowHeights = new int[]{24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		upButton = new JButton("Up");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.up();
			}
		});
		GridBagConstraints gbc_upButton = new GridBagConstraints();
		gbc_upButton.insets = new Insets(0, 0, 5, 5);
		gbc_upButton.gridx = 0;
		gbc_upButton.gridy = 0;
		panel.add(upButton, gbc_upButton);
		
		downBtn = new JButton("Down");
		downBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.down();
			}
		});
		GridBagConstraints gbc_downBtn = new GridBagConstraints();
		gbc_downBtn.insets = new Insets(0, 0, 5, 0);
		gbc_downBtn.gridx = 1;
		gbc_downBtn.gridy = 0;
		panel.add(downBtn, gbc_downBtn);
		
		topBtn = new JButton("Top");
		topBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.top();
			}
		});
		GridBagConstraints gbc_topBtn = new GridBagConstraints();
		gbc_topBtn.insets = new Insets(0, 0, 5, 5);
		gbc_topBtn.gridx = 0;
		gbc_topBtn.gridy = 1;
		panel.add(topBtn, gbc_topBtn);
		
		bottomBtn = new JButton("Bottom");
		bottomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.bottom();
			}
		});
		GridBagConstraints gbc_bottomBtn = new GridBagConstraints();
		gbc_bottomBtn.insets = new Insets(0, 0, 5, 0);
		gbc_bottomBtn.gridx = 1;
		gbc_bottomBtn.gridy = 1;
		panel.add(bottomBtn, gbc_bottomBtn);
		
		lblNewLabel = new JLabel("Border color");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Inner color");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		shapeColor = new JPanel();
		shapeColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose color", shapeColor.getBackground());
				if (color != null) shapeColor.setBackground(color);
			}
		});
		shapeColor.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_shapeColor = new GridBagConstraints();
		gbc_shapeColor.insets = new Insets(0, 0, 5, 5);
		gbc_shapeColor.fill = GridBagConstraints.BOTH;
		gbc_shapeColor.gridx = 0;
		gbc_shapeColor.gridy = 3;
		panel.add(shapeColor, gbc_shapeColor);
		
		shapeInnerColor = new JPanel();
		shapeInnerColor.setBackground(new Color(255, 255, 255));
		shapeInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose inner color", shapeInnerColor.getBackground());
				if (color != null) shapeInnerColor.setBackground(color);
			}
		});
		GridBagConstraints gbc_shapeInnerColor = new GridBagConstraints();
		gbc_shapeInnerColor.insets = new Insets(0, 0, 5, 0);
		gbc_shapeInnerColor.fill = GridBagConstraints.BOTH;
		gbc_shapeInnerColor.gridx = 1;
		gbc_shapeInnerColor.gridy = 3;
		panel.add(shapeInnerColor, gbc_shapeInnerColor);
		
		undoButton = new JButton("UNDO");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.undo();
			}
		});
		GridBagConstraints gbc_undoButton = new GridBagConstraints();
		gbc_undoButton.insets = new Insets(0, 0, 5, 5);
		gbc_undoButton.gridx = 0;
		gbc_undoButton.gridy = 4;
		panel.add(undoButton, gbc_undoButton);
		
		redoButton = new JButton("REDO");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.redo();
			}
		});
		GridBagConstraints gbc_redoButton = new GridBagConstraints();
		gbc_redoButton.insets = new Insets(0, 0, 5, 0);
		gbc_redoButton.gridx = 1;
		gbc_redoButton.gridy = 4;
		panel.add(redoButton, gbc_redoButton);
		
		lblNewLabel_2 = new JLabel("Save");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		btnLog = new JButton("Log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.save("Log");
			}
		});
		GridBagConstraints gbc_btnLog = new GridBagConstraints();
		gbc_btnLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnLog.gridx = 0;
		gbc_btnLog.gridy = 6;
		panel.add(btnLog, gbc_btnLog);
		
		btnSerialized = new JButton("Serialized");
		btnSerialized.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.save("");
			}
		});
		GridBagConstraints gbc_btnSerialized = new GridBagConstraints();
		gbc_btnSerialized.insets = new Insets(0, 0, 5, 0);
		gbc_btnSerialized.gridx = 1;
		gbc_btnSerialized.gridy = 6;
		panel.add(btnSerialized, gbc_btnSerialized);
		
		lblNewLabel_3 = new JLabel("Read");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 7;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		btnNewButton = new JButton("Log");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean success =contr.read("Log");
				if (success) {
					listModel.clear();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Serialized");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean success =contr.read("Serialized");
				if (success) {
					listModel.clear();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 8;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_2 = new JButton("Next");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.next();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 9;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(listModel);
		//btnGroup.add(tglbtnModify);
		//btnGroup.add(tglbtnDelete);
		
		drawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickedPoint = new Point(e.getX(), e.getY());
				contr.whiteSurfaceClicked();
			}
		});
		
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.modify();
			}
		});
		
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contr.delete();
			}
		});
		
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public ButtonGroup getBtnGroup() {
		return btnGroup;
	}

	public ButtonGroup getBtnGroup2() {
		return btnGroup2;
	}

	public void setController(Controller c) {
		this.contr = c;
	}
	
	public void setPnlDrawing(PnlDrawing pnl) {
		this.drawing = pnl;
	}
	
	public Point getClickedPoint() {
		return clickedPoint;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}
	
	public Color getShapeColor() {
		return shapeColor.getBackground();
	}
	
	public Color getShapeInnerColor() {
		return shapeInnerColor.getBackground();
	}

	@Override
	public void updateUI(ArrayList<Boolean> list) {
		tglbtnSelection.setEnabled(list.get(0));
		tglbtnModify.setEnabled(list.get(1));
		tglbtnDelete.setEnabled(list.get(2));
		btnNewButton_2.setEnabled(list.get(3));
		undoButton.setEnabled(list.get(4));
		redoButton.setEnabled(list.get(5));
		upButton.setEnabled(list.get(6));
		topBtn.setEnabled(list.get(6));
		downBtn.setEnabled(list.get(7));
		bottomBtn.setEnabled(list.get(7));
	}

	@Override
	public void saveLog(String log) {
		listModel.addElement(log);
		
	}

	public ArrayList<String> getLogs() {
		ArrayList<String> logs = new ArrayList<String>();
		for (int i =0;i<listModel.size();i++) {
			logs.add(listModel.getElementAt(i));
		}
		
		return logs;
	}
	
	
}
