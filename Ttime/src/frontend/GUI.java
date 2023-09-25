package frontend;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

enum Week { //요일을 담은 열거형 변수
	월(1),
	화(2),
	수(3),
	목(4),
	금(5);
	private final int value;
	Week(int value) {
		this.value = value;
	}
}

public class GUI extends JFrame {
	public GUI() {
		setTitle("Ttime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MainPane = getContentPane(); //전체 UI를 담을 프레임
		
		JPanel calendar = new JPanel(); //시간표를 나타낼 패널
		calendar.setLayout(new GridLayout(9, 6)); //[9][6]의 배열로 각 라벨을 담음
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED)); //외곽선 굴곡 설정
		
		JLabel[][] label = new JLabel[9][6]; //각 시간표 칸을 담은 이차원 배열
		for (int i = 0; i < 9; i++) { //시간표 패널 초기설정
			for (int j = 0; j < 6; j++) {
				label[i][j] = new JLabel();
				if (i != 0 && j != 0) //요일, 시간 칸이 아닌경우 회색 테두리 그림
					label[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
				calendar.add(label[i][j]);
			}
		}
		Week[] week = Week.values();
		for (int i = 0; i < 5; i++) { //요일 칸 초기화
			label[0][i+1].setText(week[i].name());
		}
		for (int i = 1; i < 9; i++) { //시간 칸 초기화
			label[i][0].setText(String.format("%02d:00", 8+i));
		}
		
		JPanel cemester = new JPanel(); //학기 선택창 패널
		cemester.setLayout(new GridLayout(1, 2));
		String[] cemester_list = {"1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1", "4-2"};
		JComboBox<String> cemester_combo = new JComboBox<String>(cemester_list); //학기 선택창을 구현할 콤보버튼
		cemester.add(cemester_combo);
		cemester.add(new JButton("수정")); //수정버튼
		
		JPanel grades = new JPanel(); //패널 안 성적 텍스트를 놓을 패널
		grades.setLayout(new GridLayout(4, 1));
		grades.add(new JLabel("이번학기"));
		grades.add(new JLabel("평점평균: 0.0    신청학점: 0.0"));
		grades.add(new JLabel("전체학기"));
		grades.add(new JLabel("평점평균: 0.0    신청학점: 0.0"));
		
		JPanel classes = new JPanel(); //검색창, 수강목록을 담은 패널
		classes.setLayout(new BorderLayout());
		JPanel search = new JPanel(); // classes 안에 들어갈 검색창 패널
		search.setLayout(new FlowLayout());
		search.add(new JLabel("검색 "));
		search.add(new JTextField(10)); //검색창
		classes.add(search, BorderLayout.NORTH);
		String[] test_list = {"[A+]테스트1", "[A+]테스트2", "[A+]테스트3", "[A+]테스트4", "[A+]테스트5"}; //DB에서 받아온 데이터
		JList<String> class_list = new JList<String>(test_list); //현재 과목 리스트
		classes.add(new JScrollPane(class_list), BorderLayout.CENTER); //리스트에 스크롤 기능을 넣음
		
		JPanel menu = new JPanel(); //시간표를 제외한 패널들을 담은 메뉴 패널
		menu.setLayout(new BorderLayout());
		menu.add(cemester, BorderLayout.NORTH);
		menu.add(grades, BorderLayout.SOUTH);
		menu.add(classes, BorderLayout.CENTER);
		
		MainPane.setLayout(new BorderLayout()); //전체 패널에 모든 요소 추가
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(menu, BorderLayout.WEST);
		
		setSize(500, 400);
		setVisible(true);
	}

}
