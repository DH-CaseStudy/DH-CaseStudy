package StudentManagementRefacotr;

public abstract class StudentDBIO implements StudentIO {
    //파일의 입출력을 담당한다 이후에 DB에 접근하는 로직으로 변경.

    private static final StudentDBIO INSTANCE = new StudentManager();
    //왜 static final 로 선언했을까?
    //파일 입출력(혹은 DB)는 전역에 단일 객체로 접근하는것이 안전하다고 생각.
    //여러 객체가 생성되서 테이블의 무결성이 훼손되는 것을 방지한다.
    //StudentManager 클래스는 StudentDBIO를 상속받았기 때문에 StudentDBIO 타입의 인스턴스를 생성 가능하다.

    protected StudentDBIO(){

    }

    public static StudentDBIO getInstance(){
        return INSTANCE;
    }

    @Override
    public void input() {
        System.out.println(getClass().getName());
        //add
        System.out.println("StudentDBIO input");
    }

    @Override
    public void output(){
        System.out.println(getClass().getName());
    }

    @Override
    public void sort(String sno) {
        System.out.println(getClass().getName());
    }

    @Override
    public void sort(int total) {
        System.out.println(getClass().getName());
    }

    @Override
    public void search(){
        System.out.println(getClass().getName());
    }

    //input output sort search 는 DBIO의 책임
    //학생 관련 데이터를 가공하는 메서드는 해당 클래스에서 추상함수로 명시

    abstract void print();
    //서브클래스가 print 메서드를 강제 구현 하도록
    //print는 유저에게 보여 줄 데이터
}
