package StudentManagementRefacotr;

import java.util.ArrayList;
import java.util.List;

public class StudentManager extends StudentDBIO{
    private List<Student> studentList = new ArrayList<>();
    //가공될 데이터를 정의

    @Override
    public void search() {
        super.search();
        //list
        //callback
    }

    @Override
    public void sort(int total) {
        super.sort(total);
    }

    @Override
    public void sort(String sno) {
        super.sort(sno);
    }

    @Override
    public void input() {
        super.input();
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
