package StudentManagementSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb"; // 데이터베이스명 수정 가능
    private static final String USER = "root"; // MySQL 사용자 계정
    private static final String PASSWORD = "3546"; // MySQL 비밀번호

    private static Connection conn = null; // 싱글턴 패턴 적용

    // 생성자를 private으로 선언하여 외부에서 인스턴스화 방지
    private DBConnection() {}

    // 데이터베이스 연결을 위한 getInstance() 메서드 (싱글턴 적용)
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ MySQL 연결 성공!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ MySQL 연결 실패!");
            e.printStackTrace();
        }
        return conn;
    }

    // 데이터베이스 연결 해제 메서드
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("✅ MySQL 연결이 종료되었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("❌ MySQL 연결 종료 중 오류 발생!");
            e.printStackTrace();
        }
    }
}
