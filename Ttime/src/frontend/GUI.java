package frontend;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	public GUI() {
		setTitle("Ttime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MainPane = getContentPane();
		
		JPanel calendar = new JPanel();
		calendar.setLayout(new GridLayout(9, 6)); //시간표를 나타낼 패널
		
		JPanel cemester = new JPanel(); //학기를 선택할 수 있는 패널
		
		JPanel grades = new JPanel(); //성적 데이터를 표기할 패널
		
		JPanel classes = new JPanel();
		classes.setLayout(new BorderLayout()); //각 강의의 검색창을 담을 패널
		
		MainPane.setLayout(new BorderLayout());
		
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(cemester, BorderLayout.NORTH);
		MainPane.add(grades, BorderLayout.SOUTH);
		MainPane.add(classes, BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}

}
