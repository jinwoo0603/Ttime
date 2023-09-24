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
		
		String[] cemester_list = {"1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1", "4-2"};
		
		JPanel cemester = new JPanel(); //학기를 선택할 수 있는 패널
		cemester.setLayout(new FlowLayout());
		
		JPanel cemester_inner = new JPanel(); //패널 안 버튼을 놓을 패널
		cemester_inner.setLayout(new FlowLayout());
		
		JComboBox<String> cemester_combo = new JComboBox<String>(cemester_list); //학기 선택창을 구현할 콤보버튼
		cemester_inner.add(cemester_combo);
		cemester_inner.add(new JButton("수정"));
		cemester.add(cemester_inner);
		
		JPanel grades = new JPanel(); //성적 데이터를 표기할 패널
		grades.setLayout(new FlowLayout());
		JPanel grades_inner = new JPanel(); //패널 안 성적 텍스트를 놓을 패널
		grades_inner.setLayout(new FlowLayout());
		grades_inner.add(new JLabel("이번학기 성적"));
		grades_inner.add(new JLabel("전체학기 성적"));
		grades.add(grades_inner);
		
		JPanel classes = new JPanel();
		classes.setLayout(new BorderLayout()); //각 강의의 검색창을 담을 패널
		JPanel search = new JPanel(); // classes 안에 들어갈 검색창 패널
		search.setLayout(new FlowLayout());
		search.add(new JLabel("검색 "));
		search.add(new JTextField(10));
		classes.add(search, BorderLayout.NORTH);
		
		String[] test_list = {"테스트1", "테스트2", "테스트3", "테스트4", "테스트5"};
		JList<String> class_list = new JList<String>(test_list); //현재 과목 리스트
		
		classes.add(new JScrollPane(class_list), BorderLayout.CENTER);
		
		MainPane.setLayout(new BorderLayout());
		
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(cemester, BorderLayout.NORTH);
		MainPane.add(grades, BorderLayout.SOUTH);
		MainPane.add(classes, BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}

}
