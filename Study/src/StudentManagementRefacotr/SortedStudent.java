package StudentManagementRefacotr;

public interface SortedStudent {
    void sort(String sno);
    void sort(int total);
    //정렬하는 기능
    //1. 학번으로 정렬
    //2. 성적으로 정렬
    // 정렬은 오름 차순으로 고정한다
    // Comparator 혹은 Comparable을 이용하여 정렬한다.
    //출력은 하지 않는다.
}
