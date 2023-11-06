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
            DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
            if (selectedItem != null) { // selectedItem�� null���� Ȯ��
            	classListModel.addElement("A+ " + selectedItem);
            }
        }
	}

}
