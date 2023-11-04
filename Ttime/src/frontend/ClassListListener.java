package frontend;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import java.util.Arrays;

public class ClassListListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		JList<String> jlist = (JList<String>)e.getSource();
		String selectedItem = jlist.getSelectedValue();
		
		String[] score = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F", "P", "NP"};
		String selected_score = selectedItem.substring(0, 2);
		String rest = selectedItem.substring(2);
		int score_index = Arrays.binarySearch(score, selected_score);
		
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            GUI.GUIData.class_list.remove(selectedItem);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
        	String new_score = score[(score_index-1+score.length) % score.length];
        	GUI.GUIData.class_list.set(GUI.GUIData.class_list.indexOf(selectedItem), new_score.concat(rest));
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	String new_score = score[(score_index+1) % score.length];
        	GUI.GUIData.class_list.set(GUI.GUIData.class_list.indexOf(selectedItem), new_score.concat(rest));
        }
    }
}
