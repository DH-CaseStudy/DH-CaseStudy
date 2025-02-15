package StudentManagementRefactor;

import java.util.ArrayList;
import java.util.List;

public class StudentManager extends StudentDBIO{
    private static final StudentManager INSTANCE = new StudentManager();
    private List<Student> studentList = new ArrayList<>();

    protected StudentManager() {
       super();
    }

    public static StudentManager getInstance() {
        return INSTANCE;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList.clear();
        this.studentList = studentList;
    }

    //가공될 데이터를 정의

    @Override
    public void search(String sno) {

    }

    @Override
    public void sortByTotal() {

    }

    @Override
    public void sortByName() {

    }


    @Override
    public void output() {
        for(Student student : studentList){
            System.out.println(student);
        }
    }
}
