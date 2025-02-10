package StudentManagementSystem.service;
import StudentManagementSystem.dao.StudentFileIO;

public class StudentManager extends StudentFileIO {
    private static StudentManager instance;

    private StudentManager() {}

    public static StudentManager getInstance(){
        if(instance == null){
            instance = new StudentManager();
        }

        return instance;
    }

}
