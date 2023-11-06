package frontend;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

public class ClassListListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		JList<String> jlist = (JList<String>)e.getSource();
		String selectedItem = jlist.getSelectedValue();
		int selectedIndex = jlist.getSelectedIndex();
		
		String[] score = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F", "P", "NP"};
		String[] sub = selectedItem.split(" ", 2);
		String selected_score = sub[0];
		String rest = " " + sub[1];
		DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
		int score_index = Arrays.binarySearch(score, selected_score);
		
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
        	classListModel.remove(selectedIndex);
        }
        else if (e.getKeyChar() == ',') {
        	if (selected_score != "A+") {
        		String new_score = score[--score_index];
        		classListModel.set(selectedIndex, new_score + rest);
        	}
        }
        else if (e.getKeyChar() == '.') {
        	if (selected_score != "NP") {
        		String new_score = score[++score_index];
        		classListModel.set(selectedIndex, new_score + rest);
        	}
        }
    }
}
