package frontend;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;

public class SearchResultListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			JList<String> jList = (JList<String>)e.getSource();
            String selectedItem = jList.getSelectedValue();
            GUI.GUIData.class_list.add("A+ " + selectedItem);
        }
	}

}
