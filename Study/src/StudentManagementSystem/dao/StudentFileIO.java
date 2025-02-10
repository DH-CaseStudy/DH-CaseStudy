package StudentManagementSystem.dao;
import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.io.StudentIO;
import StudentManagementSystem.service.StudentManager;

import java.util.List;

public abstract class StudentFileIO implements StudentIO {
    private static final StudentFileIO INSTANCE = new StudentManager();

    protected StudentFileIO(){

    }

    // 싱글톤 인스턴스 반환
    public static StudentFileIO getInstance() {
        return INSTANCE;
    }

}
