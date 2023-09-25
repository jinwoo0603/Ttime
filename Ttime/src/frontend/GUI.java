package frontend;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

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
	public GUI() {
		setTitle("Ttime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MainPane = getContentPane(); //��ü UI�� ���� ������
		
		JPanel calendar = new JPanel(); //�ð�ǥ�� ��Ÿ�� �г�
		calendar.setLayout(new GridLayout(9, 6)); //[9][6]�� �迭�� �� ���� ����
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED)); //�ܰ��� ���� ����
		
		JLabel[][] label = new JLabel[9][6]; //�� �ð�ǥ ĭ�� ���� ������ �迭
		for (int i = 0; i < 9; i++) { //�ð�ǥ �г� �ʱ⼳��
			for (int j = 0; j < 6; j++) {
				label[i][j] = new JLabel();
				if (i != 0 && j != 0) //����, �ð� ĭ�� �ƴѰ�� ȸ�� �׵θ� �׸�
					label[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
				calendar.add(label[i][j]);
			}
		}
		Week[] week = Week.values();
		for (int i = 0; i < 5; i++) { //���� ĭ �ʱ�ȭ
			label[0][i+1].setText(week[i].name());
		}
		for (int i = 1; i < 9; i++) { //�ð� ĭ �ʱ�ȭ
			label[i][0].setText(String.format("%02d:00", 8+i));
		}
		
		JPanel cemester = new JPanel(); //�б� ����â �г�
		cemester.setLayout(new GridLayout(1, 2));
		String[] cemester_list = {"1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1", "4-2"};
		JComboBox<String> cemester_combo = new JComboBox<String>(cemester_list); //�б� ����â�� ������ �޺���ư
		cemester.add(cemester_combo);
		cemester.add(new JButton("����")); //������ư
		
		JPanel grades = new JPanel(); //�г� �� ���� �ؽ�Ʈ�� ���� �г�
		grades.setLayout(new GridLayout(4, 1));
		grades.add(new JLabel("�̹��б�"));
		grades.add(new JLabel("�������: 0.0    ��û����: 0.0"));
		grades.add(new JLabel("��ü�б�"));
		grades.add(new JLabel("�������: 0.0    ��û����: 0.0"));
		
		JPanel classes = new JPanel(); //�˻�â, ��������� ���� �г�
		classes.setLayout(new BorderLayout());
		JPanel search = new JPanel(); // classes �ȿ� �� �˻�â �г�
		search.setLayout(new FlowLayout());
		search.add(new JLabel("�˻� "));
		search.add(new JTextField(10)); //�˻�â
		classes.add(search, BorderLayout.NORTH);
		String[] test_list = {"[A+]�׽�Ʈ1", "[A+]�׽�Ʈ2", "[A+]�׽�Ʈ3", "[A+]�׽�Ʈ4", "[A+]�׽�Ʈ5"}; //DB���� �޾ƿ� ������
		JList<String> class_list = new JList<String>(test_list); //���� ���� ����Ʈ
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
