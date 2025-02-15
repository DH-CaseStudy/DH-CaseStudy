package StudentManagementRefactor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //StudentDBIO.getInstance().input(); // () => true {output});
        //콜백으로 output 구현.
        //목요일까지
        //근거! 가 있는 향상될 수 있는 점
        StudentDBIO.getInstance().loadData();

//        while(true){
//            System.out.println("학생정보 조회 시스템 입니다.");
//            System.out.println("1.입력 2.전체조회 3.학번으로 조회 4.정렬(이름 순) 5.정렬(성적 순) 6.삭제 중 원하는 번호를 입력하세요.");
//        }


    }
}
