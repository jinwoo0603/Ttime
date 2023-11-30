package frontend;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;

class SearchBarListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JTextField t = (JTextField)e.getSource();
		String search_word = t.getText();
		String[] search_token = search_word.split(" ");
		
		database.DB_Connect db_connect = new database.DB_Connect();
		String[] list = db_connect.findMatchingRows(search_token[0]);
		
		DefaultListModel<String> listmodel = new DefaultListModel<String>();
		if (search_token.length == 1) {
			for (int i = 0; i < list.length; i++) {
				listmodel.addElement(list[i]);
			}
		}
		else {
			for (int i = 0; i < list.length; i++) {
				for (int j = 1; j < search_token.length; j++) {
					if (list[i].contains(search_token[j])) {
						listmodel.addElement(list[i]);
						break;
					}
				}
			}
		}
		
		GUI.GUIData.search_result.setModel(listmodel);
	}
}