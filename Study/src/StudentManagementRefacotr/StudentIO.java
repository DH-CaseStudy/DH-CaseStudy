package StudentManagementRefacotr;

public interface StudentIO extends StudentInput, SortedStudent, SearchStudent {

    //다중 상속을 하기 위한 징검다리 역할 인터페이스
    // StudentIO 와 StudentDBIO 상속관계
}
