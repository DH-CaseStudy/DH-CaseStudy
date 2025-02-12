package StudentManagementSystem.io;

import StudentManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentOutput {
    List<StudentDTO> output();//학생 목록 총 출력
}
