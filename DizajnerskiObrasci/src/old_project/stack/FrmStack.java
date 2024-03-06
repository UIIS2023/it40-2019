package old_project.stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import old_project.geometry.Point;
import old_project.geometry.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	JList listRect = new JList(); //u konstruktoru povezujemo listu i model

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Petrov Dejan IT40-2019");
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlg = new DlgStack();
				dlg.setTitle("Add new rectangle");
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
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listRect.getLastVisibleIndex();	//indeks poslednjeg elementa u listi
					try
					{
						DlgStack dlg = new DlgStack();	
						dlg.setTitle("Remove the rectangle");
						int i = listRect.getLastVisibleIndex();
						dlg.getTxtX().setText(String.valueOf(dlm.elementAt(i).getUpperLeftPoint().getX()));
						dlg.getTxtY().setText(String.valueOf(dlm.elementAt(i).getUpperLeftPoint().getY()));
						dlg.getTxtWidth().setText(String.valueOf(dlm.elementAt(i).getWidth()));
						dlg.getTxtHeight().setText(String.valueOf(dlm.elementAt(i).getHeight()));
						dlg.getTxtX().setEditable(false); //read only
						dlg.getTxtY().setEditable(false);
						dlg.getTxtWidth().setEditable(false);
						dlg.getTxtHeight().setEditable(false);
						dlg.setVisible(true);
						if(dlg.isOk())
						{
							int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure?",
									"Rectangle remove", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(confirmation==0)
								dlm.remove(index);
						}
					}
					catch (ArrayIndexOutOfBoundsException ex)
					{
						JOptionPane.showMessageDialog(null, "The list is empty!");
					}
								
			}
		});
		pnlSouth.add(btnRemove);
		
		JScrollPane scrlPaneRect = new JScrollPane();
		contentPane.add(scrlPaneRect, BorderLayout.CENTER);
		
		scrlPaneRect.setViewportView(listRect);
		listRect.setModel(dlm); //bindovanje modela dlm na listu igraca ListRect
		
	}

}
