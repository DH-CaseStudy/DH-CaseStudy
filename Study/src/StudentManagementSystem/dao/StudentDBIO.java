package StudentManagementSystem.dao;

import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.io.StudentIO;
import StudentManagementSystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDBIO implements StudentIO {

    //DAO의 역할을 하니까 객체가 전역에 단 하나만 존재하는 것이 맞지 않을까? => 싱글톤
    private static StudentDBIO instance;

    private StudentDBIO(){}

    public static StudentDBIO getInstance() {
        if (instance == null) {
            instance = new StudentDBIO();
        }
        return instance;
    }

    @Override
    public StudentDTO saveStudentData(StudentDTO student) {
        StudentDTO studentDTO = student;
        //DB Create
        String sql = "INSERT INTO STUDENT (sno, name, korean, english, math, science, total, average, grade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getSno());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getKorean());
            pstmt.setInt(4, student.getEnglish());
            pstmt.setInt(5, student.getMath());
            pstmt.setInt(6, student.getScience());
            pstmt.setInt(7, student.getTotal());
            pstmt.setFloat(8, student.getAverage());
            pstmt.setString(9, student.getGrade());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ 학생 정보 저장 완료: " + student.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentDTO;
    }

    @Override
    public List<StudentDTO> loadStudentData() {
        //DB Read
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT sno, name, korean, english, math, science, total, average, grade FROM STUDENT";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            // ✅ ResultSet에서 데이터를 읽어 StudentDTO 리스트로 변환
            while (rs.next()) {
                StudentDTO student = new StudentDTO(
                        rs.getString("sno"),
                        rs.getString("name"),
                        rs.getInt("korean"),
                        rs.getInt("english"),
                        rs.getInt("math"),
                        rs.getInt("science"),
                        rs.getInt("total"),
                        rs.getFloat("average"),
                        rs.getString("grade")
                );

                students.add(student); // ✅ 리스트에 추가
            }


            System.out.println("✅ DB에서 학생 정보 로드 완료! 총 " + students.size() + "명");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public StudentDTO search(String sno) {
        String sql = "SELECT sno, name, korean, english, math, science, total, average, grade FROM STUDENT WHERE sno = ?";
        StudentDTO student = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sno); // ✅ 학번 바인딩
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { // ✅ 결과가 존재할 경우
                student = new StudentDTO(
                        rs.getString("sno"),
                        rs.getString("name"),
                        rs.getInt("korean"),
                        rs.getInt("english"),
                        rs.getInt("math"),
                        rs.getInt("science"),
                        rs.getInt("total"),
                        rs.getFloat("average"),
                        rs.getString("grade")
                );

                System.out.println("✅ 학생 정보 조회 성공: " + student);
            } else {
                System.out.println("❌ 해당 학번을 가진 학생이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student; // ✅ StudentDTO 객체 반환 (없으면 null)
    }

    @Override
    public List<StudentDTO> sortBySno() {
        //학번 순 정렬
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT ORDER BY sno DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                students.add(new StudentDTO(
                        rs.getString("sno"),
                        rs.getString("name"),
                        rs.getInt("korean"),
                        rs.getInt("english"),
                        rs.getInt("math"),
                        rs.getInt("science"),
                        rs.getInt("total"),
                        rs.getFloat("average"),
                        rs.getString("grade")
                ));
            }

            System.out.println("✅ 학번 순 정렬 완료!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public List<StudentDTO> sortByTotal() {
        //성적 순 정렬
        List<StudentDTO> students = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT ORDER BY total DESC"; // ✅ 총점 내림차순 정렬

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                students.add(new StudentDTO(
                        rs.getString("sno"),
                        rs.getString("name"),
                        rs.getInt("korean"),
                        rs.getInt("english"),
                        rs.getInt("math"),
                        rs.getInt("science"),
                        rs.getInt("total"),
                        rs.getFloat("average"),
                        rs.getString("grade")
                ));
            }

            System.out.println("✅ 성적 순 정렬 완료!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }




    @Override
    public void input(StudentDTO studentDTO) {
        //File write...
    }

    @Override
    public void output(StudentDTO studentDTO) {
        // 단순 출력..
    }
}
