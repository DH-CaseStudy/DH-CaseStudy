package StudentManagementRefactor;

import java.io.IOException;
import java.util.HashMap;

/**
 * 학생 관리 시스템의 메인 클래스.
 * <p>
 * 학생 정보를 입력, 조회, 정렬(성적순,이름순 내림차순), 삭제하는 기능을 실행하며, 사용자 입력을 받아 처리한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public class Main {

    /**
     * 학생 관리 시스템의 진입점.
     * <p>
     * 사용자가 메뉴를 선택하여 학생 정보를 입력, 조회, 정렬(성적순,이름순 내림차순), 삭제할 수 있도록 한다.
     * </p>
     *
     * @throws IOException 데이터 로딩 중 발생할 수 있는 예외
     */
    public static void main(String[] args) throws IOException {
        StudentManager.getInstance().loadData();

        while (true) {
            System.out.println("학생정보 조회 시스템 입니다.");
            System.out.println("1.입력 2.전체조회 3.학번으로 조회 4.정렬(이름 순) 5.정렬(성적 순) 6.삭제 중 원하는 번호를 입력하세요.");
            int input = Utility.readInput(Integer.class);

            switch (input) {
                case 1: // 학생 입력
                    String sno = getValidatedStudentNumber();
                    String name = getValidatedName();

                    int korean = getValidatedScore("국어");
                    int english = getValidatedScore("영어");
                    int math = getValidatedScore("수학");
                    int science = getValidatedScore("과학");

                    int total = korean + english + math + science;
                    double average = (double) total / 4;
                    String grade = calculateGrade(average);

                    Student student = new Student(sno, name, korean, english, math, science, total, average, grade);
                    StudentManager.getInstance().input(student); // JSON 파일에 데이터 입력
                    break;

                case 2: // 전체 테이블 조회
                    StudentManager.getInstance().loadData();
                    StudentManager.getInstance().output();
                    break;

                case 3: // 특정 학생 조회
                    System.out.println("조회하고자 하는 학번을 입력하세요.");
                    String searchKey = getValidatedStudentNumber();
                    StudentManager.getInstance().search(searchKey);
                    break;

                case 4: // 이름 기준 정렬
                    StudentManager.getInstance().sortByName();
                    StudentManager.getInstance().output();
                    break;

                case 5: // 성적 기준 정렬
                    StudentManager.getInstance().sortByTotal();
                    StudentManager.getInstance().output();
                    break;

                case 6: // 특정 학생 삭제 후 최신 테이블 반환
                    System.out.println("삭제하고자 하는 학번을 입력하세요.");
                    String deleteKey = getValidatedStudentNumber();
                    StudentManager.getInstance().deleteStudent(deleteKey);
                    StudentManager.getInstance().output();
                    break;

                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    /**
     * 학번 입력을 검증하는 메서드.
     * <p>
     * 학번은 반드시 5자리 숫자로 입력해야 한다.
     * </p>
     *
     * @return 유효한 학번 (5자리 숫자)
     */
    private static String getValidatedStudentNumber() {
        System.out.println("학번을 입력하세요 (5자리 숫자):");
        while (true) {
            String sno = Utility.readInput(String.class);
            if (sno.matches("\\d{5}")) {
                return sno;
            }
            System.out.println("학번은 반드시 5자리 숫자로 입력해야 합니다. 다시 입력하세요.");
        }
    }

    /**
     * 특정 과목의 점수를 입력받고 검증하는 메서드.
     * <p>
     * 점수는 반드시 0~100 사이의 정수여야 한다.
     * </p>
     *
     * @param subject 입력할 과목명 (예: 국어, 영어, 수학, 과학)
     * @return 유효한 점수 (0~100 범위의 정수)
     */
    private static int getValidatedScore(String subject) {
        System.out.println(subject + " 점수를 입력하세요 (0~100):");
        while (true) {
            int score = Utility.readInput(Integer.class);
            if (score >= 0 && score <= 100) {
                return score;
            }
            System.out.println("점수는 0에서 100 사이로 입력해야 합니다. 다시 입력하세요.");
        }
    }

    /**
     * 한글 이름 입력을 검증하는 메서드.
     * <p>
     * 이름은 반드시 한글만 포함해야 하며, 영어 및 숫자는 입력할 수 없다.
     * </p>
     *
     * @return 유효한 한글 이름
     */
    private static String getValidatedName() {
        System.out.println("이름을 입력하세요 (한글만 가능):");
        while (true) {
            String name = Utility.readInput(String.class);
            if (name.matches("[가-힣]+")) {  // 한글만 허용
                return name;
            }
            System.out.println("이름은 한글만 입력해야 합니다. 다시 입력하세요.");
        }
    }

    /**
     * 평균 점수를 기준으로 학점을 계산하는 메서드.
     *
     * @param average 학생의 평균 점수
     * @return 학점 (A, B, C, D, F)
     */
    private static String calculateGrade(double average) {
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }
}
