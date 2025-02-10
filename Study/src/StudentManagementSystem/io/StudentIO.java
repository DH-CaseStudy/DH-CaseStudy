package StudentManagementSystem.io;

import StudentManagementSystem.dto.StudentDTO;

import java.util.List;

public interface StudentIO extends StudentInput, SearchStudent, SortedStudent{
    void saveStudentData(List<StudentDTO> students);
    void loadStudentData(List<StudentDTO> students);
}
