package StudentManagementSystem.dao;

import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.io.StudentIO;
import StudentManagementSystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDBIO implements StudentIO {
    //입력과 출력을 담당하여 File Write
    public StudentDBIO() {
    }

    @Override
    public void saveStudentData(StudentDTO student) {
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
    }

    @Override
    public void loadStudentData(List<StudentDTO> students) {
        //DB Read
    }

    @Override
    public void search(String sno) {
        //학번으로 검색
    }

    @Override
    public void sort(float average) {
        //성적순 정렬
    }

    @Override
    public void sort(String sno) {
        //학번 순 정렬
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
