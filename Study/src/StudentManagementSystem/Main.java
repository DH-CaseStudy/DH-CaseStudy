package StudentManagementSystem;

import StudentManagementSystem.dao.StudentDBIO;
import StudentManagementSystem.dto.StudentDTO;
import StudentManagementSystem.service.StudentManager;
import StudentManagementSystem.util.DBConnection;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String regex = "^[0-9]{9,10}$"; // 정규식: 숫자 10자리
        StudentDBIO studentDBIO = new StudentDBIO();

        while (true) {
            try {
                System.out.println("학생정보 조회 시스템 입니다.");
                System.out.println("1.입력 2.조회 3.정렬(학번 순) 4.정렬(성적 순) 5.삭제 중 원하는 번호를 입력하세요.");
                int selectNum = Integer.parseInt(br.readLine());

                switch (selectNum) {
                    case 1 -> {
                        System.out.println("입력을 선택하셨습니다.");
                        System.out.println("학번을 입력해주세요(10자리 숫자만 입력 가능)");
                        String sno = br.readLine();

                        if(!sno.matches(regex)){
                            System.out.println("5자리의 숫자로만 입력해주세요.");
                        }

                        System.out.println("이름을 입력해 주세요.");
                        String name = br.readLine();
                        System.out.println("국어 점수를 입력해주세요.");
                        int korean = Integer.parseInt(br.readLine());
                        System.out.println("영어 점수를 입력해주세요.");
                        int english = Integer.parseInt(br.readLine());
                        System.out.println("수학 점수를 입력해주세요.");
                        int math = Integer.parseInt(br.readLine());
                        System.out.println("과학 점수를 입력해주세요.");
                        int science = Integer.parseInt(br.readLine());
                        int total = korean + english + math + science;
                        int average = total/4;
                        String grade;
                        if (average >= 90) grade = "A";
                        else if (average >= 80) grade = "B";
                        else if (average >= 70) grade = "C";
                        else if (average >= 60) grade = "D";
                        else grade = "F";

                        //extracted(sno, name, korean, english, math, science, total, average);
                        // ✅ StudentDTO 객체 생성 후 StudentManager에 추가
                        StudentDTO student = new StudentDTO(sno, name, korean, english, math, science, total, average, grade);
                        studentDBIO.saveStudentData(student);
                        System.out.println(" 학생 정보가 메모리에 저장되었습니다.");

                    }

                    case 2 -> {
                        System.out.println("조회");
                    }
                    case 3 -> {
                        System.out.println("정렬(학번 순)");
                    }
                    case 4 -> {
                        System.out.println("정렬(성적 순");
                    }
                    case 5 -> {
                        System.out.println("삭제");
                    }
                    default -> {
                        System.out.println("1 부터 5 사이의 숫자를 입력해주세요.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요 ");
            }
        }
    }

//    private static StudentDTO createStudent(String sno, String name, int korean, int english, int math, int science, int total, int average) {
//        String grade;
//        if (average >= 90) grade = "A";
//        else if (average >= 80) grade = "B";
//        else if (average >= 70) grade = "C";
//        else if (average >= 60) grade = "D";
//        else grade = "F";
//
//        return new StudentDTO(sno, name, korean, english, math, science, total, average, grade);
//    }
}