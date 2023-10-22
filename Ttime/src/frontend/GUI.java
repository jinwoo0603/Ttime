package frontend;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

enum Week { //������ ���� ������ ����
	��(1),
	ȭ(2),
	��(3),
	��(4),
	��(5);
	private final int value;
	Week(int value) {
		this.value = value;
	}
}

public class GUI extends JFrame {
	
	static class GUIData //UI�� ��Ÿ�� �������� ������ ���� Ŭ����
	{
		static Vector<String> class_list = new Vector<String>(); //�ð�ǥ ������ ������ ���� �迭
		static JLabel[][] calendar_label = new JLabel[9][6]; //�� �ð�ǥ ĭ�� ���� ������ �迭
		static JLabel[] grades_label = new JLabel[4]; //���� ������ ���� �迭
		static JList<String> search_result = new JList<String>(); //�˻������ ���� ����Ʈ
		
		static //����Ŭ������ �ʱ�ȭ
		{
			search_result.setFixedCellWidth(100); //�˻����â ũ�� ����
			
			grades_label[0] = new JLabel("�̹��б�");
			grades_label[1] = new JLabel("�������: 0.0    ��û����: 0.0");
			grades_label[2] = new JLabel("��ü�б�");
			grades_label[3] = new JLabel("�������: 0.0    ��û����: 0.0");
			
			for (int i = 0; i < 9; i++) { //�ð�ǥ �г� �ʱ⼳��
				for (int j = 0; j < 6; j++) {
					calendar_label[i][j] = new JLabel();
					if (i != 0 && j != 0) //����, �ð� ĭ�� �ƴѰ�� ȸ�� �׵θ� �׸�
						calendar_label[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
				}
			}
			Week[] week = Week.values();
			for (int i = 0; i < 5; i++) { //���� ĭ �ʱ�ȭ
				calendar_label[0][i+1].setText(week[i].name());
			}
			for (int i = 1; i < 9; i++) { //�ð� ĭ �ʱ�ȭ
				calendar_label[i][0].setText(String.format("%02d:00", 8+i));
			}
		}
		
		static void set_calendar(String[][] s) //���ڿ� �迭�� ���۷����� ���޹޾� �ð�ǥ �� ����
		{
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 5; j++)
					calendar_label[i+1][j+1].setText(s[i][j]);
		}
		
		static void set_grades(double[][] d) //�Ǽ� �迭�� ���۷����� ���޹޾� ���� �� ����
		{
			grades_label[1].setText("�������: " + d[0][0] + "    ��û����: " + d[0][1]);
			grades_label[3].setText("�������: " + d[1][0] + "    ��û����: " + d[1][1]);
		}
	}
	
	public GUI() {
		setTitle("Ttime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MainPane = getContentPane(); //��ü UI�� ���� ������
		
		JPanel calendar = new JPanel(); //�ð�ǥ�� ��Ÿ�� �г�
		calendar.setLayout(new GridLayout(9, 6)); //[9][6]�� �迭�� �� ���� ����
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED)); //�ܰ��� ���� ����
		
		for (int i = 0; i < 9; i++) //�ð�ǥ �г� �ʱ⼳��
			for (int j = 0; j < 6; j++)
				calendar.add(GUIData.calendar_label[i][j]);
		
		JPanel cemester = new JPanel(); //�б� ����â �г�
		cemester.setLayout(new GridLayout(1, 2));
		String[] cemester_list = {"1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1", "4-2"};
		JComboBox<String> cemester_combo = new JComboBox<String>(cemester_list); //�б� ����â�� ������ �޺���ư
		cemester.add(cemester_combo);
		cemester.add(new JButton("����")); //������ư
		
		JPanel grades = new JPanel(); //�г� �� ���� �ؽ�Ʈ�� ���� �г�
		grades.setLayout(new GridLayout(4, 1));
		for (int i = 0; i < 4; i++)
			grades.add(GUIData.grades_label[i]);
		
		JPanel search_bar = new JPanel(); //�˻�â �г�
		search_bar.setLayout(new FlowLayout());
		search_bar.add(new JLabel("�˻� "));
		search_bar.add(new JTextField(10)); //�˻�â
		
		JPanel search = new JPanel(); //�˻������ ���� �г�
		search.setLayout(new BorderLayout());
		search.add(search_bar, BorderLayout.NORTH);
		search.add(new JScrollPane(GUIData.search_result), BorderLayout.CENTER); //����Ʈ�� ��ũ�� ����� ����
		
		JList<String> class_list = new JList<String>(GUIData.class_list); //���� ���� ����Ʈ
		class_list.setFixedCellWidth(100); //���� ����Ʈ ũ�� ����
		
		JPanel menu = new JPanel(); //�ð�ǥ�� ������ �гε��� ���� �޴� �г�
		menu.setLayout(new BorderLayout());
		menu.add(cemester, BorderLayout.NORTH);
		menu.add(grades, BorderLayout.SOUTH);
		menu.add(new JScrollPane(class_list), BorderLayout.CENTER); //����Ʈ�� ��ũ�� ����� ����
		
		MainPane.setLayout(new BorderLayout()); //��ü �гο� ��� ��� �߰�
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(menu, BorderLayout.WEST);
		MainPane.add(search, BorderLayout.EAST);
		
		setSize(700, 500);
		setVisible(true);
		String[][] myArray = new String[8][5];
        myArray[1][2] = "Test";
        GUIData.set_calendar(myArray);
	}

}