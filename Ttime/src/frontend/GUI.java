package frontend;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	public GUI() {
		setTitle("Ttime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MainPane = getContentPane();
		
		JPanel calendar = new JPanel();
		calendar.setLayout(new GridLayout(9, 6)); //�ð�ǥ�� ��Ÿ�� �г�
		
		JPanel cemester = new JPanel(); //�б⸦ ������ �� �ִ� �г�
		cemester.setLayout(new FlowLayout());
		JPanel cemester_inner = new JPanel(); //�г� �� ��ư�� ���� �г�
		cemester_inner.setLayout(new FlowLayout());
		cemester_inner.add(new JButton("�б�")); //���߿� ����â���� ����
		cemester_inner.add(new JButton("����"));
		cemester.add(cemester_inner);
		
		JPanel grades = new JPanel(); //���� �����͸� ǥ���� �г�
		grades.setLayout(new FlowLayout());
		JPanel grades_inner = new JPanel(); //�г� �� ���� �ؽ�Ʈ�� ���� �г�
		grades_inner.setLayout(new FlowLayout());
		grades_inner.add(new JLabel("�̹��б� ����"));
		grades_inner.add(new JLabel("��ü�б� ����"));
		grades.add(grades_inner);
		
		JPanel classes = new JPanel();
		classes.setLayout(new BorderLayout()); //�� ������ �˻�â�� ���� �г�
		JPanel search = new JPanel(); // classes �ȿ� �� �˻�â �г�
		search.setLayout(new FlowLayout());
		search.add(new JLabel("�˻� "));
		search.add(new JTextField(10));
		classes.add(search, BorderLayout.NORTH);
		
		
		MainPane.setLayout(new BorderLayout());
		
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(cemester, BorderLayout.NORTH);
		MainPane.add(grades, BorderLayout.SOUTH);
		MainPane.add(classes, BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}

}
