package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Connect {

	public Connection connect;
    public Statement state;//특정한 데이터베이스에 sql문장을 실행 가능하게해주는 개체
    public ResultSet result;//실행 결과 받아오는 객체	
   
    public DB_Connect() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://34.64.55.10:3306/class_list?user=root&password=admin&ssl=true"); //구글 클라우드 sql에 접속
            state = connect.createStatement();//쿼리 실행	
            
        } catch (Exception e) {
            System.out.println("데이터 연결 과정에 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public boolean isAdmin(String subjectCode, String subjectName, String professor, String scheduleRoom, String classification, String grade, String credit, String lectureRating, String capacity, String target, String remarks) {
        try {
            String SQL = "SELECT * FROM raw_data " +
                         "WHERE " +
                         "subjectCode = '" + subjectCode + "' AND " +
                         "subjectName = '" + subjectName + "' AND " +
                         "professor = '" + professor + "' AND " +
                         "scheduleRoom = '" + scheduleRoom + "' AND " +
                         "classification = '" + classification + "' AND " +
                         "grade = " + grade + " AND " +
                         "credit = " + credit + " AND " +
                         "rating = " + lectureRating + " AND " +
                         "capacity = " + capacity + " AND " +
                         "target = '" + target + "' AND " +
                         "remark = '" + remarks + "'";

            result = state.executeQuery(SQL);

            if (result.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("데이터 검색 과정에 오류가 발생했습니다: " + e.getMessage());
        }
        return false;//데이터 쿼리랑 비교해서 있는지 확인 함수
    }

    public String[] findMatchingRows(String input) {//스윙에서 입력받은 매개변수 finMatchingRows(매개변수)로 매소드 호출해서 데이터 저장된 이차원 배열 리턴 받으시면 될 것 같아요 
        try {
            String find = "SELECT * FROM raw_data " +
                         "WHERE " +
                         "subjectCode = ? OR " +
                         "subjectName = ? OR " +
                         "professor = ? OR " +
                         "scheduleRoom = ? OR " +
                         "classification = ? OR " +
                         "grade = ? OR " +
                         "credit = ? OR " +
                         "rating = ? OR " +
                         "capacity = ? OR " +
                         "target = ? OR " +
                         "remark = ?";//?는 플레이스홀더로 쿼리 텍스트 부분과 값을 동적으로 바인딩 가능 

            PreparedStatement st = connect.prepareStatement(find);//비교하기 위해만든 find가 위의 SQL이랑 자료형이랑 달리 하나라도 같으면 추출하기 위해서 OR을 사용해 자료구조가 다르기 때문에 새로이 쿼리에 값을 불러와 저장하기 위한 객체 생성했음
            for (int i = 1; i <= 11; i++) {
                st.setString(i, input);// subjectCode subjectName professor scheduleRoom classification grade credit rating capacity target remark 값들이 전부 input으로 바인딩됨
            }

            result = st.executeQuery();//PreparedStatement에 설정되 형식의 쿼리에서 or을 사용해서 하나라도 같은 데이터가 있으면 그 행들 전부로 집합을 만들고 Resultset 객체 resultdp 저장함
            
            List<String> matchingRowsList = new ArrayList<>();
            while (result.next()) {
            	 StringBuffer rowData = new StringBuffer("");	; // 열의 수에 따라 배열 크기 설정 + 평점? 학점?을 저장할 공간 하나 더 만들어두긴 했는데 이게 맞나 모르겠네요 아니면 나중에 수정하겠습니다.
                for (int i = 0; i < 11; i++) {
                	rowData.append(result.getString(i + 1));
                    if (i != 10) rowData.append(" "); // 1부터 시작하는 인덱스를 사용하여 추출했던 행의 열 데이터 추출
                }
                matchingRowsList.add(rowData.toString()); // 일치하는 행을 리스트에 추가 *행 하나의 열들은 스트링 배열로 만들었고 그렇게 만든 행들을 리스트에 넣어둠
            }

            // matchingRowsList를 배열로 변환
            
            String[]matchingRows = matchingRowsList.toArray(new String[matchingRowsList.size()]); 

            return matchingRows ;//일치하는 모든 행을 이차원 배열로 리턴했어요 이차원 배열로 리턴받으시면 될 것 같아요
            
        } catch (Exception e) {
            System.out.println("데이터 검색 과정에 오류가 발생했습니다: " + e.getMessage());
        }

       return new String[0]; // 일치하는 데이터가 없을 때 빈 배열 반환
    }//
    
}
