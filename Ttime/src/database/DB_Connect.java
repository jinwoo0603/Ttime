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
        return false;
    }

    public String[] findMatchingRows(String input) {
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
                         "remark = ?";

            PreparedStatement st = connect.prepareStatement(find);
            for (int i = 1; i <= 11; i++) {
                st.setString(i, input);
            }

            result = st.executeQuery();

            List<String> matchingRowsList = new ArrayList<>();
            while (result.next()) {
                // 검색어가 각 열의 값과 정확하게 일치해야만 데이터를 가져옴
                if (input.equals(result.getString("subjectCode")) ||
                    input.equals(result.getString("subjectName")) ||
                    input.equals(result.getString("professor")) ||
                    input.equals(result.getString("scheduleRoom")) ||
                    input.equals(result.getString("classification")) ||
                    input.equals(result.getString("grade")) ||
                    input.equals(result.getString("credit")) ||
                    input.equals(result.getString("rating")) ||
                    input.equals(result.getString("capacity")) ||
                    input.equals(result.getString("target")) ||
                    input.equals(result.getString("remark"))) {

                    StringBuffer rowData = new StringBuffer("");
                    rowData.append(result.getString("subjectCode")).append(" "); 
                    rowData.append(result.getString("subjectName")).append(" "); 
                    rowData.append(result.getString("professor")).append(" "); 
                    rowData.append(result.getString("scheduleRoom")).append(" "); 
                    rowData.append(result.getString("classification")).append(" "); 
                    rowData.append(result.getString("grade")).append(" "); 
                    rowData.append(result.getString("credit")).append(" "); 
                    rowData.append(result.getString("rating")).append(" "); 
                    rowData.append(result.getString("capacity")).append(" "); 
                    rowData.append(result.getString("target")).append(" "); 
                    rowData.append(result.getString("remark")); 

                    matchingRowsList.add(rowData.toString());
                }
            }

            String[] matchingRows = matchingRowsList.toArray(new String[matchingRowsList.size()]);
            return matchingRows;
        } catch (Exception e) {
            System.out.println("데이터 검색 과정에 오류가 발생했습니다: " + e.getMessage());
        }

        return new String[0];
    }
}
