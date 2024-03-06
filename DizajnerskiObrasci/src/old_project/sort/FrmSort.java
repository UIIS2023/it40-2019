package old_project.sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import old_project.geometry.Point;
import old_project.geometry.Rectangle;
import old_project.stack.DlgStack;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	JList listRect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSort() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgStack dlg = new DlgStack();
				dlg.setVisible(true);
				
				if(dlg.isOk())
				{
					String x = dlg.getTxtX().getText().toString();
					int intX = Integer.parseInt(x);
					String y = dlg.getTxtY().getText().toString();
					int intY = Integer.parseInt(y);
					String width = dlg.getTxtWidth().getText().toString();
					int intWidth = Integer.parseInt(width);
					String height = dlg.getTxtHeight().getText().toString();
					int intHeight = Integer.parseInt(height);
					
					Rectangle r = new Rectangle(new Point(intX, intY), intWidth, intHeight);
					dlm.addElement(r);
				}
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!dlm.isEmpty()) //ako nije prazna 
				{
					Rectangle[] rArray = new Rectangle[dlm.getSize()]; //kreiranje niza
					for (int i = 0; i < rArray.length; i++)
						rArray[i] = dlm.getElementAt(i); 
					Arrays.sort(rArray); //sortiranje niza
					dlm.clear(); //brisanje liste
					for (int i = 0; i < rArray.length; i++)
						dlm.addElement(rArray[i]); //dodavanje sortiranih pravougaonika
					JOptionPane.showMessageDialog(pnlSouth, "Rectangles are sorted by area!", "Successfully", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(pnlSouth, "The list is empty!", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		pnlSouth.add(btnSort);
		
		JScrollPane scrlPaneRect = new JScrollPane();
		contentPane.add(scrlPaneRect, BorderLayout.CENTER);
		
		listRect = new JList();
		scrlPaneRect.setViewportView(listRect);
		listRect.setModel(dlm);
		setTitle("Petrov Dejan IT40-2019");
		
	}

}