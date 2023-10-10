//각 학기별 시간표 데이터를 나타내며, 강의 이름, 시간, 강의실 정보 저장
package backend;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class TimetableData {
    private List<String> courses; // 강의 데이터 저장하는 리스트

    public TimetableData() {
        this.courses = new ArrayList<>(); // 생성자에서 리스트 초기화
    }

// 강의 데이터 추가하는 메소드
    public void addCourse(String raw_data) {
        courses.add(raw_data);
    }
    
// 저장된 강의 데이터를 반환하는 메소드
    public List<String> getCourses() {
        return courses;
    }
}
