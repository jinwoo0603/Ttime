package frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import backend.SummaryFileManager;
import backend.TimetableFileManager;
import backend.Calculator;

public class EditBtnListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		TimetableFileManager tfile_manager = new TimetableFileManager(GUI.GUIData.current_cemester + ".txt");
		tfile_manager.saveTimeTableToFile();
		
		SummaryFileManager sfile_manager = new SummaryFileManager();
		String[][] former_score = sfile_manager.readSummaryFromFile();
		
		int index = 0;
		if (GUI.GUIData.current_cemester.equals("1-1"))
			index = 0;
		else if (GUI.GUIData.current_cemester.equals("1-2"))
			index = 1;
		else if (GUI.GUIData.current_cemester.equals("2-1"))
			index = 2;
		else if (GUI.GUIData.current_cemester.equals("2-2"))
			index = 3;
		else if (GUI.GUIData.current_cemester.equals("3-1"))
			index = 4;
		else if (GUI.GUIData.current_cemester.equals("3-2"))
			index = 5;
		else if (GUI.GUIData.current_cemester.equals("4-1"))
			index = 6;
		else if (GUI.GUIData.current_cemester.equals("4-2"))
			index = 7;
		
		Calculator cal = new Calculator();
		double[] new_score = cal.cemesterAverage();
		
		former_score[index][0] = String.valueOf(new_score[0]);
		former_score[index][1] = String.valueOf(new_score[1]);
		cal.summaryAverage(former_score);
		String[][] return_value = {{former_score[index][0], former_score[index][1]}, {former_score[8][0], former_score[8][1]}};
		GUI.GUIData.set_grades(return_value);
		sfile_manager.saveSummaryToFile(former_score);
	}

}
