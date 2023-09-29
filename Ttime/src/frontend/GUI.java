package frontend;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

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
		static ArrayList<String[]> class_list; //�ð�ǥ ������ ������ ���� �迭
		static JLabel[][] calendar_label = new JLabel[9][6]; //�� �ð�ǥ ĭ�� ���� ������ �迭
		static JLabel[] grades_label = new JLabel[4]; //���� ������ ���� �迭
		
		static //����Ŭ������ �ʱ�ȭ
		{
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
		
		JPanel classes = new JPanel(); //�˻�â, ��������� ���� �г�
		classes.setLayout(new BorderLayout());
		JPanel search = new JPanel(); // classes �ȿ� �� �˻�â �г�
		search.setLayout(new FlowLayout());
		search.add(new JLabel("�˻� "));
		search.add(new JTextField(10)); //�˻�â
		classes.add(search, BorderLayout.NORTH);
		
		//�̺κ��� ���� DB ��Ʈ �ϼ��Ǹ� ������
		String[] test_list = {"[A+]�׽�Ʈ1", "[A+]�׽�Ʈ2", "[A+]�׽�Ʈ3", "[A+]�׽�Ʈ4", "[A+]�׽�Ʈ5"}; //DB���� �޾ƿ� ������
		JList<String> class_list = new JList<String>(test_list); //���� ���� ����Ʈ
		//
		classes.add(new JScrollPane(class_list), BorderLayout.CENTER); //����Ʈ�� ��ũ�� ����� ����
		
		JPanel menu = new JPanel(); //�ð�ǥ�� ������ �гε��� ���� �޴� �г�
		menu.setLayout(new BorderLayout());
		menu.add(cemester, BorderLayout.NORTH);
		menu.add(grades, BorderLayout.SOUTH);
		menu.add(classes, BorderLayout.CENTER);
		
		MainPane.setLayout(new BorderLayout()); //��ü �гο� ��� ��� �߰�
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(menu, BorderLayout.WEST);
		
		setSize(500, 400);
		setVisible(true);
	}

}
