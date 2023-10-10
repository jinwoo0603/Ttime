import java.io.*;
import java.util.List;

public class TimetableFileManager {
	// 시간표 뎅터 파일로 저장하는 메소드
    public static void saveTimetableToFile(String raw_data, TimetableData save_data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(raw_data))) {
            for (String courseData : save_data.getCourses()) {
                writer.println(courseData); // 파일에 한 줄씩 강의 데이터 저장
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 저장!! 중 발생하는 예외 처리
        }
    }

    //파일에서 시간표 데이터를 읽어오는 메소드
    public static TimetableData readTimetableFromFile(String raw_data) {
        TimetableData timetableData = new TimetableData();
        try (BufferedReader reader = new BufferedReader(new FileReader(raw_data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                timetableData.addCourse(line); // 파일에서 한 줄씩 읽어와 강의 데이터 추가
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기!! 중 발생하는 예외 처리
        }
        return timetableData;
    }
}
