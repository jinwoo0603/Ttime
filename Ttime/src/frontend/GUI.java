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
		
		JPanel grades = new JPanel(); //���� �����͸� ǥ���� �г�
		
		JPanel classes = new JPanel();
		classes.setLayout(new BorderLayout()); //�� ������ �˻�â�� ���� �г�
		
		MainPane.setLayout(new BorderLayout());
		
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(cemester, BorderLayout.NORTH);
		MainPane.add(grades, BorderLayout.SOUTH);
		MainPane.add(classes, BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}

}
