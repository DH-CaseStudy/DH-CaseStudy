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
        this.studentList = studentList;
    }

    //가공될 데이터를 정의

    @Override
    public void search(String sno) {
        super.search(sno);
        //list
        //callback
    }

    @Override
    public void sortByTotal() {
        super.sortByTotal();
    }

    @Override
    public void sortByName() {
        super.sortByName();
    }

    @Override
    public void input() {
        super.input();
        System.out.println("StudentManager input 이벤트!");
    }

    @Override
    public void output() {
        super.output();
    }

    @Override
    void print() {
        //studentList 애를 순회해서 추력할거에여.
    }
}
