package StudentManagementSystem.io;

import StudentManagementSystem.dto.StudentDTO;

public interface SearchStudent extends StudentOutput{
    StudentDTO search(String sno); //특정 학생을 검색
}
