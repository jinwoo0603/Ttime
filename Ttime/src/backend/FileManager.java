// 완성된 거 아니에요!!!


package backend;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    // 시간표 데이터를 파일로 저장하는 메서드
    public static void createTimeFile(String[] raw_data) {
        String fileName ="raw_data.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String course : raw_data) {
                writer.println(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 총괄 파일에 데이터를 추가하는 메서드
    public static void editSummaryFile(String[] summaryData) {
        String fileName = "summary.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (String entry : summaryData) {
                writer.println(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 시간표 데이터를 읽어서 스트링 배열로 반환하는 메서드
    public static String[] readTimeFile(String semester) {
        String fileName = semester + "raw_data.txt";
        List<String> timetableData = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                timetableData.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return timetableData.toArray(new String[0]);
    }

    // 총괄 데이터를 읽어서 스트링 배열로 반환하는 메서드
    public static String[] readSummaryFile() {
        String fileName = "summary.txt";
        List<String> summaryData = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                summaryData.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return summaryData.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // 각 학기별 시간표 데이터 예시
        String[] springData = {"Course 1", "Course 2", "Course 3"};
        String[] summerData = {"Course 4", "Course 5", "Course 6"};
        String[] fallData = {"Course 7", "Course 8", "Course 9"};

        // 각 학기별 시간표 파일 생성
        createTimeFile("spring", springData);
        createTimeFile("summer", summerData);
        createTimeFile("fall", fallData);

        // 총괄 파일 편집 예시
        String[] summaryData = {"Spring Semester Summary", "Summer Semester Summary", "Fall Semester Summary"};
        editSummaryFile(summaryData);

        // 시간표 데이터 읽어오기
        String[] springTimetable = readTimeFile("spring");
        for (String course : springTimetable) {
            System.out.println("Spring: " + course);
        }

        // 총괄 데이터 읽어오기
        String[] summary = readSummaryFile();
        for (String entry : summary) {
            System.out.println("Summary: " + entry);
        }
    }
}
