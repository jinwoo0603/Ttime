package backend;// �떆媛꾪몴 �뜲�씠�꽣 �뙆�씪�쓣 �깮�꽦, �렪吏묓븯�뒗 �옉�뾽 泥섎━

import java.io.*;
import javax.swing.DefaultListModel;
import frontend.GUI;

public class TimetableFileManager {
	private String file_name;
	private File file;
	public TimetableFileManager(String s) {
		file_name = new String(s);
		file = new File(file_name);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// �떆媛꾪몴 �뜲�씠�꽣 �뙆�씪濡� ���옣�븯�뒗 硫붿냼�뱶
	public void saveTimeTableToFile() {
		DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
		
		try (PrintWriter writer = new PrintWriter(file)) {
			
            for (int i = 0; i < classListModel.getSize(); i++) {
                writer.println(classListModel.getElementAt(i)); // �뙆�씪�뿉 �븳 以꾩뵫 媛뺤쓽 �뜲�씠�꽣 ���옣
            }
        } catch (IOException e) {
            e.printStackTrace(); // �뙆�씪 ���옣 以� 諛쒖깮�븯�뒗 �삁�쇅 泥섎━
        }
	}

    //�뙆�씪�뿉�꽌 �떆媛꾪몴 �뜲�씠�꽣瑜� �씫�뼱�삤�뒗 硫붿냼�뱶
    public void readTimetableFromFile() {
    	DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            classListModel.removeAllElements();
            //나중에 시간표 기능 수정때 추가
            while ((line = reader.readLine()) != null) {
            	classListModel.addElement(line); // �뙆�씪�뿉�꽌 �븳 以꾩뵫 �씫�뼱�� 媛뺤쓽 �뜲�씠�꽣 異붽�
            }
        } catch (IOException e) {
            e.printStackTrace(); // �뙆�씪 �씫湲� 以� 諛쒖깮�븯�뒗 �삁�쇅 泥섎━
        }
        
    }
}
