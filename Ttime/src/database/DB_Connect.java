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
    public Statement state;//�듅�젙�븳 �뜲�씠�꽣踰좎씠�뒪�뿉 sql臾몄옣�쓣 �떎�뻾 媛��뒫�븯寃뚰빐二쇰뒗 媛쒖껜
    public ResultSet result;//�떎�뻾 寃곌낵 諛쏆븘�삤�뒗 媛앹껜	
   
    public DB_Connect() {
        try {
        	String jdbcUrl = "jdbc:mysql://34.64.55.10/class_list?user=root&password=admin";
        	connect = DriverManager.getConnection(jdbcUrl); //援ш� �겢�씪�슦�뱶 sql�뿉 �젒�냽
            state = connect.createStatement();//荑쇰━ �떎�뻾	
            
        } catch (Exception e) {
            System.out.println("�뜲�씠�꽣 �뿰寃� 怨쇱젙�뿉 �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎: " + e.getMessage());
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
            System.out.println("�뜲�씠�꽣 寃��깋 怨쇱젙�뿉 �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎: " + e.getMessage());
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
                         "target = ? OR " +
                         "remark = ?";

            PreparedStatement st = connect.prepareStatement(find);
            for (int i = 1; i <= 7; i++) {
                st.setString(i, input);
            }

            result = st.executeQuery();

            List<String> matchingRowsList = new ArrayList<>();
            while (result.next()) {
            	StringBuffer rowData = new StringBuffer("");	; // 열의 수에 따라 배열 크기 설정 + 평점? 학점?을 저장할 공간 하나 더 만들어두긴 했는데 이게 맞나 모르겠네요 아니면 나중에 수정하겠습니다.
                for (int i = 0; i < 11; i++) {
                	rowData.append(result.getString(i + 1));
                    if (i != 10) rowData.append(" ");
                }
                matchingRowsList.add(rowData.toString());
            }

            String[] matchingRows = matchingRowsList.toArray(new String[matchingRowsList.size()]);
            return matchingRows;
        } catch (Exception e) {
            System.out.println("�뜲�씠�꽣 寃��깋 怨쇱젙�뿉 �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎: " + e.getMessage());
        }

        return new String[0];
    }
}
