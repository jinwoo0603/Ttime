// 총괄 파일을 생성, 편집하고 읽는 작업을 처리

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class SummaryFileManager {
    // 총괄 파일에 총괄 정보를 저장하는 메소드
    public static void saveSummaryToFile(raw_data, List<String []> summaryData) { //
        try (PrintWriter writer = new PrintWriter(new FileWriter(raw_data))) {
            for (String summaryLine : summaryData) {
                String line = String.join(",", summaryLine);
                writer.println(summaryLine); // 한 줄씩 총괄 정보를 파일에 저장
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 저장 중 발생하는 예외 출력
        }
    }

    // 파일에서 총괄 정보를 읽어와 List<String []>으로 반환하는 메소드
    public static List<String []> readSummaryFromFile(String raw_data) {
        List<String []> summaryData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(raw_data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] summaryLine = line.split(","); // 한 줄을 쉼표로 나눠 배열에 저장
                summaryData.add(summaryLine); // 총괄 정보 List에 추가
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기 중 발생하는 예외 출력
        }
        return summaryData;
    }
}
