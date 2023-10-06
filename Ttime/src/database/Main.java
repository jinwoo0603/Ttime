package database;

public class Main {

    public static void main(String[] args) {
        DB_Connect connection = new DB_Connect();

        // 데이터베이스 연결이 잘 되었는지 확인하는 예시입니다.
        boolean isAdmin = connection.isAdmin("507428-003", "자바프로그래밍", "장수미", "정보816[월6-8]", "전선", "2", "3", "0", "28", "-", "-");

        // true일 경우 연결이 되었다는 뜻, false일 경우 문제가 있다는 뜻.
        System.out.println("여부: " + isAdmin);
    }
}
