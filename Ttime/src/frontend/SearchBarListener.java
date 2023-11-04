package frontend;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;

class SearchBarListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JTextField t = (JTextField)e.getSource();
		
		database.DB_Connect db_connect = new database.DB_Connect();
		String[] list = db_connect.findMatchingRows(t.getText());
		
		DefaultListModel<String> listmodel = new DefaultListModel<String>();
		for (int i = 0; i < list.length; i++) {
			listmodel.addElement(list[i]);
		}
		
		GUI.GUIData.search_result.setModel(listmodel);
	}
}