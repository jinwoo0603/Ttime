package frontend;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import frontend.GUI.GUIData;

public class BrightBtnListener implements ActionListener {
	private static void changeColors(Container container, String bright) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                // 재귀적으로 하위 컴포넌트의 색상 변경
                changeColors((Container) component, bright);
            }
            // 각 컴포넌트의 배경색 변경
            if (bright.equals("LIGHT")) {
            	component.setBackground(Color.darkGray);
            	component.setForeground(Color.WHITE);
            }
            else if (bright.equals("DARK")) {
            	component.setBackground(new Color(235, 235, 235));
            	component.setForeground(Color.BLACK);
            }
        }
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource()));
		Container container = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		JButton btn = (JButton)e.getSource();
		if (btn.getText().equals("DARK"))
			btn.setText("LIGHT");
		else if (btn.getText().equals("LIGHT"))
			btn.setText("DARK");
		changeColors(container, btn.getText());
		if (btn.getText().equals("DARK")) {
			GUI.GUIData.search_textfield.setBackground(Color.WHITE);
	        GUI.GUIData.class_list.setBackground(Color.WHITE);
	        GUI.GUIData.search_result.setBackground(Color.WHITE);
		}
		else if (btn.getText().equals("LIGHT")) {
			GUI.GUIData.search_textfield.setBackground(new Color(128, 128, 128));
	        GUI.GUIData.class_list.setBackground(new Color(128, 128, 128));
	        GUI.GUIData.search_result.setBackground(new Color(128, 128, 128));
		}
	}

}
