package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_Connect {

    private Connection connect;
    private Statement state;
    private ResultSet result;

    public DB_Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db", "root", "admin"); // 'root'은 user명, 'admin'은 비밀번호 입니다.
            state = connect.createStatement();
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
}
