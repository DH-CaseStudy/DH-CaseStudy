package StudentManagementSystem.dto;

public class StudentDTO {
    private String sno;
    private String name;
    private int korean;
    private int english;
    private int math;
    private int science;
    private int totla;
    private float average;
    private String grade;

    public StudentDTO(String sno, String name, int korean, int english, int math, int science, int totla, float average, String grade) {
        this.sno = sno;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
        this.science = science;
        this.totla = totla;
        this.average = average;
        this.grade = grade;
    }
}
