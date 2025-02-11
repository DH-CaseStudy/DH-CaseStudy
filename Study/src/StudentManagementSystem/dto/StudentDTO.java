package StudentManagementSystem.dto;

public class StudentDTO {
    private String sno;
    private String name;
    private int korean;
    private int english;
    private int math;
    private int science;
    private int total;
    private float average;
    private String grade;

    public StudentDTO(String sno, String name, int korean, int english, int math, int science, int total, float average, String grade) {
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

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "{" +
                "학생번호='" + sno + '\'' +
                ", 이름='" + name + '\'' +
                ", 국어점수=" + korean +
                ", 영어점수=" + english +
                ", 수학점수=" + math +
                ", 과학점수=" + science +
                ", 총합=" + total +
                ", 평균=" + average +
                ", 등급='" + grade + '\'' +
                '}' + "\n";
    }
}
