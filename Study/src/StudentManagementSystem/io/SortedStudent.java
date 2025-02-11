package StudentManagementSystem.io;

import StudentManagementSystem.dto.StudentDTO;

import java.util.List;

public interface SortedStudent { //성적 순 정렬
    List<StudentDTO> sortBySno();
    List<StudentDTO> sortByTotal();// 학번 순 정렬
}
