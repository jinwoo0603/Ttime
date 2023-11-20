package backend;

import javax.swing.DefaultListModel;
import frontend.GUI;

public class Calculator {
    public double[] cemesterAverage() {
    	double[] values = {0, 0};
    	DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
    	if (classListModel.size() == 0)
    		return values;
    	else {
    	int non_score_count = 0;
    	for (int i = 0; i < classListModel.getSize(); i++) {
    		String[] element = classListModel.getElementAt(i).split(" ");
    		if (element[0].equals("A+"))
    			values[0] += 4.5;
    		else if (element[0].equals("A0"))
    			values[0] += 4.0;
    		else if (element[0].equals("B+"))
    			values[0] += 3.5;
    		else if (element[0].equals("B0"))
    			values[0] += 3.0;
    		else if (element[0].equals("C+"))
    			values[0] += 2.5;
    		else if (element[0].equals("C0"))
    			values[0] += 2.0;
    		else if (element[0].equals("D+"))
    			values[0] += 1.5;
    		else if (element[0].equals("D0"))
    			values[0] += 1.0;
    		else if (element[0].equals("F")) {}
    		else
    			non_score_count++;
    		values[1] += Double.parseDouble(element[6]); //8踰덉㎏ �썝�냼媛� �븰�젏�엫
    		}
    	values[0] /= (classListModel.getSize() - non_score_count);
    	return values;
    	}
    }
    public String[][] summaryAverage(String[][] num) {
    	double avg = 0, tot = 0;
    	int cnt = 0;
    	for (int i = 0; i < 8; i++) {
    		String[] element = {num[i][0], num[i][1]};
    		if (Double.parseDouble(element[0]) != 0)
    			cnt++;
    		avg += Double.parseDouble(element[0]);
    		tot += Double.parseDouble(element[1]);
    	}
    	if (cnt == 0)
    		num[8][0] = Double.toString(avg);
    	else
    		num[8][0] = Double.toString(avg / cnt);
    	num[8][1] = Double.toString(tot);
    	return num;
    }
}
