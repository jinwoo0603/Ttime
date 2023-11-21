package frontend;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class SearchResultListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			JList<String> jList = (JList<String>)e.getSource();
            String selectedItem = jList.getSelectedValue();
            GUI.GUIData.set_calendar(selectedItem, 3, true);
            DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
            if (selectedItem != null) { // selectedItem가 null인지 확인
            	classListModel.addElement("A+ " + selectedItem);
            }
        }
	}

}
