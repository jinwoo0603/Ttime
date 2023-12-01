package frontend;
import javax.swing.*;
import javax.swing.border.*;

import backend.Calculator;
import backend.SummaryFileManager;
import backend.TimetableFileManager;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static class GUIData //UI에 나타낼 정보들을 저장할 정적 클래스
	{
		public static JList<String> class_list = new JList<String>(); //시간표 정보를 저장할 동적 배열
		static DefaultListModel<String> class_list_model = new DefaultListModel<String>();
		public static String current_cemester;
		public static JLabel[][] calendar_label = new JLabel[9][6]; //각 시간표 칸을 담은 이차원 배열
		static JLabel[] grades_label = new JLabel[4]; //학점 정보를 담은 배열
		static JList<String> search_result = new JList<String>(); //검색결과를 담을 리스트
		public static JTextField search_textfield = new JTextField(10);
		
		static //정적클래스의 초기화
		{
			search_result.setFixedCellWidth(100); //검색결과창 크기 고정
			search_result.addKeyListener(new SearchResultListener());
			search_result.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	DefaultListModel<String> listModel = new DefaultListModel<String>();

	                String selectedValue = search_result.getSelectedValue();
	                search_result.setToolTipText(selectedValue);
	            }
	        });
			
			current_cemester = new String("1-1");
			
			class_list.setFixedCellWidth(100);
			class_list.addKeyListener(new ClassListListener());
			class_list.setModel(class_list_model);
			
			grades_label[0] = new JLabel("이번학기");
			grades_label[1] = new JLabel("평점평균: 0.0    신청학점: 0.0");
			grades_label[2] = new JLabel("전체학기");
			grades_label[3] = new JLabel("평점평균: 0.0    신청학점: 0.0");
			
			for (int i = 0; i < 9; i++) { //시간표 패널 초기설정
				for (int j = 0; j < 6; j++) {
					calendar_label[i][j] = new JLabel();
					if (i != 0 && j != 0) //요일, 시간 칸이 아닌경우 회색 테두리 그림
						calendar_label[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
				}
			}
			Week[] week = Week.values();
			for (int i = 0; i < 5; i++) { //요일 칸 초기화
				calendar_label[0][i+1].setText(week[i].name());
			}
			for (int i = 1; i < 9; i++) { //시간 칸 초기화
				calendar_label[i][0].setText(String.format("%02d:00", 8+i));
			}
		}
		
		public static boolean set_calendar(String s, int type, boolean mode) //문자열 배열을 레퍼런스로 전달받아 시간표 값 수정
		{
			Vector<String[]> v = new Vector<String[]>();
			String[] sSplit = s.split(" ");
			String time_raw = sSplit[type];
			
			String[] time_list = time_raw.split("\\|");
			
			for (String time : time_list) {
				Pattern pattern = Pattern.compile("\\[([^\\]]*)[\\-]?\\d*\\]");
				Matcher matcher = pattern.matcher(time);
				String match = null;
				while (matcher.find()) {
		            match = matcher.group(1); // 대괄호 안의 내용에 해당하는 그룹
		        }
				
				String weekDay = match.substring(0, 1);
				String classTime = match.substring(1);
				
				Week w = Week.valueOf(weekDay);
				weekDay = String.valueOf(w.ordinal());
				
				v.add(new String[] {weekDay, classTime});
			}
			
			for (String[] ct : v) {
				String[] class_col = ct[1].split("-");

				if (class_col.length == 1) {
					if (!calendar_label[Integer.valueOf(ct[1])][Integer.valueOf(ct[0])+1].getText().equals("") && mode)
						return false;
				}
				else {
					for (int i = 0; i < 2; i++) {
						if (!calendar_label[Integer.valueOf(class_col[i])][Integer.valueOf(ct[0])+1].getText().equals("") && mode)
							return false;
					}
				}
			}
			
			String m = "";
			if (mode)
				m = "<html>" + sSplit[type - 2] + "<br>" + sSplit[type] + "<html>";
			
			for (String[] ct : v) {
				String[] class_col = ct[1].split("-");
				if (class_col.length == 1) {
					calendar_label[Integer.valueOf(ct[1])][Integer.valueOf(ct[0])+1].setText(m);
				}
				else {
					for (int i = Integer.valueOf(class_col[0]); i <= Integer.valueOf(class_col[1]); i++) {
						calendar_label[i][Integer.valueOf(ct[0])+1].setText(m);
					}
				}
			}
			return true;
		}
		
		static void set_grades(String[][] d) //실수 배열을 레퍼런스로 전달받아 성적 값 수정
		{
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (d[i][j].length() > 3)
						d[i][j] = d[i][j].substring(0, 4);
				}
			}
			grades_label[1].setText("평점평균: " + d[0][0] + "    신청학점: " + d[0][1]);
			grades_label[3].setText("평점평균: " + d[1][0] + "    신청학점: " + d[1][1]);
		}
		
		public static void refresh_file() {
			TimetableFileManager tfile_manager = new TimetableFileManager(current_cemester + ".txt");
			tfile_manager.saveTimeTableToFile();
			
			SummaryFileManager sfile_manager = new SummaryFileManager();
			String[][] former_score = sfile_manager.readSummaryFromFile();
			
			int index = 0;
			if (current_cemester.equals("1-1"))
				index = 0;
			else if (current_cemester.equals("1-2"))
				index = 1;
			else if (current_cemester.equals("2-1"))
				index = 2;
			else if (current_cemester.equals("2-2"))
				index = 3;
			else if (current_cemester.equals("3-1"))
				index = 4;
			else if (current_cemester.equals("3-2"))
				index = 5;
			else if (current_cemester.equals("4-1"))
				index = 6;
			else if (current_cemester.equals("4-2"))
				index = 7;
			
			Calculator cal = new Calculator();
			double[] new_score = cal.cemesterAverage();
			
			former_score[index][0] = String.valueOf(new_score[0]);
			former_score[index][1] = String.valueOf(new_score[1]);
			cal.summaryAverage(former_score);
			String[][] return_value = {{former_score[index][0], former_score[index][1]}, {former_score[8][0], former_score[8][1]}};
			GUI.GUIData.set_grades(return_value);
			sfile_manager.saveSummaryToFile(former_score);
		}
	}
	
	public GUI() {
		setTitle("Ttime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MainPane = getContentPane(); //전체 UI를 담을 프레임
		//getContentPane().setBackground(Color.WHITE);
		
		JPanel calendar = new JPanel(); //시간표를 나타낼 패널
		calendar.setLayout(new GridLayout(9, 6)); //[9][6]의 배열로 각 라벨을 담음
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED)); //외곽선 굴곡 설정
		
		for (int i = 0; i < 9; i++) //시간표 패널 초기설정
			for (int j = 0; j < 6; j++)
				calendar.add(GUIData.calendar_label[i][j]);
		
		JPanel cemester = new JPanel(); //학기 선택창 패널
		cemester.setLayout(new GridLayout(1, 2));
		String[] cemester_list = {"1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1", "4-2"};
		JComboBox<String> cemester_combo = new JComboBox<String>(cemester_list); //학기 선택창을 구현할 콤보버튼
		cemester_combo.setBackground(new Color(235, 235, 235));
		cemester_combo.setBorder(new BevelBorder(BevelBorder.RAISED));
		cemester_combo.addActionListener(new CemesterListener());
		cemester.add(cemester_combo);
		JButton bright_button = new JButton("DARK");
		bright_button.setBackground(new Color(235, 235, 235));
		bright_button.setBorder(new BevelBorder(BevelBorder.RAISED));
		bright_button.addActionListener(new BrightBtnListener());
		cemester.add(bright_button); //수정버튼
		
		JPanel grades = new JPanel(); //패널 안 성적 텍스트를 놓을 패널
		grades.setLayout(new GridLayout(4, 1));
		for (int i = 0; i < 4; i++)
			grades.add(GUIData.grades_label[i]);
		
		JPanel search_bar = new JPanel(); //검색창 패널
		search_bar.setLayout(new FlowLayout());
		search_bar.add(new JLabel("검색 "));
		
		//JTextField search_textfield = new JTextField(10);
		GUIData.search_textfield.addActionListener(new SearchBarListener());
		search_bar.add(GUIData.search_textfield); //검색창
		
		JButton sort_button = new JButton("정렬");
		sort_button.setBackground(new Color(235, 235, 235));
		sort_button.setBorder(new BevelBorder(BevelBorder.RAISED));
		sort_button.addActionListener(new SortBtnListener());
		search_bar.add(sort_button);
		
		JPanel search = new JPanel(); //검색결과를 담을 패널
		search.setLayout(new BorderLayout());
		search.add(search_bar, BorderLayout.NORTH);
		search.add(new JScrollPane(GUIData.search_result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER); //리스트에 스크롤 기능을 넣음
		
		//JList<String> class_list = new JList<String>(GUIData.class_list); //현재 과목 리스트
		//class_list.addKeyListener(new ClassListListener());
		//class_list.setFixedCellWidth(100); //과목 리스트 크기 고정
		
		JPanel menu = new JPanel(); //시간표를 제외한 패널들을 담은 메뉴 패널
		menu.setLayout(new BorderLayout());
		menu.add(cemester, BorderLayout.NORTH);
		menu.add(grades, BorderLayout.SOUTH);
		menu.add(new JScrollPane(GUIData.class_list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER); //리스트에 스크롤 기능을 넣음
		
		MainPane.setLayout(new BorderLayout()); //전체 패널에 모든 요소 추가
		MainPane.add(calendar, BorderLayout.CENTER);
		MainPane.add(menu, BorderLayout.WEST);
		MainPane.add(search, BorderLayout.EAST);
		
		setSize(1000, 700);
		setVisible(true);
	}

}