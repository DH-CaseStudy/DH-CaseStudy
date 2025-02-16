package StudentManagementRefactor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //StudentDBIO.getInstance().input(); // () => true {output});
        //콜백으로 output 구현.
        //목요일까지
        //근거! 가 있는 향상될 수 있는 점2
        StudentDBIO.getInstance().loadData();


        while(true){
            System.out.println("학생정보 조회 시스템 입니다.");
            System.out.println("1.입력 2.전체조회 3.학번으로 조회 4.정렬(이름 순) 5.정렬(성적 순) 6.삭제 중 원하는 번호를 입력하세요.");
            int input = Utility.readInput(Integer.class);

            switch (input){
                case 1 :
                    System.out.println("학번을 입력하세요.");
                    String sno = Utility.readInput(String.class);
                    System.out.println("이름을 입력하세요.");
                    String  name = Utility.readInput(String.class);
                    System.out.println("국어 점수를 입력하세요.");
                    int korean = Utility.readInput(Integer.class);
                    System.out.println("영어 점수를 입력하세요.");
                    int english = Utility.readInput(Integer.class);
                    System.out.println("수학 점수를 입력하세요.");
                    int math = Utility.readInput(Integer.class);
                    System.out.println("과학 점수를 입력하세요.");
                    int science = Utility.readInput(Integer.class);
                    int total = korean + english + math + science;
                    double average = (double) total/4;
                    String grade;

                    if (average >= 90) grade = "A";
                    else if (average >= 80) grade = "B";
                    else if (average >= 70) grade = "C";
                    else if (average >= 60) grade = "D";
                    else grade = "F";

                    Student student = new Student(sno, name, korean, english, math, science, total, average, grade);
                    StudentDBIO.getInstance().input(student); // json 파일에 데이터 입력 발생
                    break;

                case 2 :
                    StudentDBIO.getInstance().loadData(); //json 데이터 로드
                    StudentManager.getInstance().output(); // 출력.
                    break;
                case 3 :
                    System.out.println("학번을 입력하세요.");
                    String search = Utility.readInput(String.class);

                case 4 : break;
                case 5 : break;
                case 6 : break;
                default :
                    System.out.println("잘못 입력 하셨습니다.");
            }
        }


    }
}
