//시간표 데이터 파일을 생성, 편집하는 작업 처리

import java.io.*;
import java.util.List;
import javax.swing.DefaultListModel;

public class TimetableFileManager {
	// 시간표 데이터 파일로 저장하는 메소드
	DefaultListModel<String> classListModel = (DefaultListModel<String>) GUI.GUIData.class_list.getModel();
	public void saveTimetableToFile(String raw_data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(raw_data))) {
            // 8줄 초기화
            for (int i = 0; i < 8; i++) {
                writer.println("0.0,0.0"); // (평균 학점, 신청 학점)
            }

            // 전체 평균 1줄 초기화
            writer.println("0.0,0.0");
		
	try (PrintWriter writer = new PrintWriter(new FileWriter(raw_data))) {
            for (int i = 0; i < classListModel.getSize(); i++) {
                writer.println(classListModel.getElementAt(i)); // 파일에 한 줄씩 강의 데이터 저장
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 저장 중 발생하는 예외 처리
        }

    //파일에서 시간표 데이터를 읽어오는 메소드
    public static data readTimetableFromFile(String[] raw_data) {
        data = new data();
        try (BufferedReader reader = new BufferedReader(new FileReader(raw_data))) {
            String[] line;
            while ((line = reader.readLine()) != null) {
                data.addCourse(line); // 파일에서 한 줄씩 읽어와 강의 데이터 추가
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기 중 발생하는 예외 처리
        }
        return data;
    }
}
