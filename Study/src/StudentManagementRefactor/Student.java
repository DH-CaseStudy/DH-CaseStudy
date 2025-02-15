package StudentManagementRefactor;

import lombok.Data;

@Data
public class Student {
    //DTO 클래스로 서 역할을 한다
    private String sno;
    private String name;
    private int korean;
    private int english;
    private int math;
    private int science;
    private int total;
    private double average;
    private String grade;

    public Student(String sno, String name, int korean, int english, int math, int science, int total, double average, String grade) {
        this.sno = sno;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.science = science;
        this.total = total;
        this.average = average;
        this.grade = grade;
    }
}
