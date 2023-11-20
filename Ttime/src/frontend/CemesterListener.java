package frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import backend.TimetableFileManager;
import backend.SummaryFileManager;

public class CemesterListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
		String selectedFile = (String) comboBox.getSelectedItem() + ".txt";
		GUI.GUIData.current_cemester = new String((String) comboBox.getSelectedItem());
		TimetableFileManager tfile_manager = new TimetableFileManager(selectedFile);
		tfile_manager.readTimetableFromFile();
		SummaryFileManager sfile_manager = new SummaryFileManager();
		String[][] summary = sfile_manager.readSummaryFromFile();
		int index = 0;
		if (selectedFile.equals("1-1.txt"))
			index = 0;
		else if (selectedFile.equals("1-2.txt"))
			index = 1;
		else if (selectedFile.equals("2-1.txt"))
			index = 2;
		else if (selectedFile.equals("2-2.txt"))
			index = 3;
		else if (selectedFile.equals("3-1.txt"))
			index = 4;
		else if (selectedFile.equals("3-2.txt"))
			index = 5;
		else if (selectedFile.equals("4-1.txt"))
			index = 6;
		else if (selectedFile.equals("4-2.txt"))
			index = 7;
		String[][] score = { {summary[index][0], summary[index][1]}, {summary[8][0], summary[8][1]} };
		GUI.GUIData.set_grades(score);
	}
	

}
