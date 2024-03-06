package file.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import file.SaveStrategy;
import mvc.Model;

public class SaveSerializedStrategy implements SaveStrategy {

	private Model model;
	@Override
	public void save() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			 try {
				 FileOutputStream fout = new FileOutputStream(selectedFile.getAbsolutePath());
				 ObjectOutputStream oos = new ObjectOutputStream(fout);
				 oos.writeObject(model.getShapes());
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
	}

	public SaveSerializedStrategy(Model model) {
		this.model = model;
	}
}
