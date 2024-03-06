package file.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import file.SaveStrategy;
import old_project.drawing.FrmDrawing;

public class SaveLogStrategy implements SaveStrategy {

	FrmDrawing frm;
	
	@Override
	public void save() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			 try {
			      FileWriter myWriter = new FileWriter(selectedFile.getAbsolutePath() + ".txt");
			      ArrayList<String> logs = frm.getLogs();
			      String str = "";
			      for (String log: logs) {
			    	  str += log + "\n";
			      }
			      myWriter.write(str);
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
	}

	public void setFrrm(FrmDrawing frm) {
		this.frm = frm;
	}
}
