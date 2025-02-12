package StudentManagementSystem.io;

import StudentManagementSystem.dto.StudentDTO;

public interface StudentInput {
    StudentDTO input(StudentDTO student); //학생 데이터 입력
}
