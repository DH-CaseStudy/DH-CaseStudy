package StudentManagementRefactor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class StudentManager extends StudentDBIO{
    //    * 데이터 가공 및 관리 담당
    //    * 출력 및 조회 기능을 포함
    private static final StudentManager INSTANCE = new StudentManager();
    private HashMap<String, Student> studentList = new HashMap<>(); //키,밸류 형태 -> sno를 키로 조회 해야 하기 때문

    private StudentManager() { }

    public static StudentManager getInstance() {
        return INSTANCE;
    }

    public HashMap<String, Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(HashMap<String, Student>  studentList) {
        this.studentList.clear();
        this.studentList = studentList;
    }

    //가공될 데이터를 정의

    @Override
    public HashMap<String, Student> loadData(){
        studentList.clear();
        studentList = super.loadData();

        return null;
    }

    @Override
    public Student search(String sno) {
        studentList.clear();
        Student student =  super.search(sno);
        if(student != null){
            studentList.put(student.getSno(), student);
        }

        return null;
    }

    @Override
    public HashMap<String, Student> sortByTotal() {
        studentList.clear();
        studentList = super.sortByTotal();

        return null;
    }

    @Override
    public HashMap<String, Student> sortByName() {
        studentList.clear();
        studentList = super.sortByName();

        return null;
    }


    @Override
    public void output() {
        if (studentList.isEmpty()) {
            System.out.println("학생 데이터가 없습니다.");
        } else {
            System.out.println(Student.getTableHeader());
            for(Student students : studentList.values()){
                System.out.println(students);
            }
        }
    }

    @Override
    public HashMap<String, Student> deleteStudent(String sno) {
        studentList.clear();
        studentList = super.deleteStudent(sno);
        System.out.println(sno + "학생 데이터가 제거되었습니다.");

        return null;
    }

    public void clearList(){
        getStudentList().clear();
    }
}
