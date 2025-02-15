package StudentManagementRefactor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class StudentDBIO extends ObjectIO implements StudentIO {
    //파일의 입출력을 담당한다 이후에 DB에 접근하는 로직으로 변경.

    private static final StudentDBIO INSTANCE = new StudentManager();
    //왜 static final 로 선언했을까?
    //파일 입출력(혹은 DB)는 전역에 단일 객체로 접근하는것이 안전하다고 생각.
    //여러 객체가 생성되서 테이블의 무결성이 훼손되는 것을 방지한다.
    //StudentManager 클래스는 StudentDBIO를 상속받았기 때문에 StudentDBIO 타입의 인스턴스 생성이 가능하다.

    private static final String filePath = "src/StudentManagementRefactor/students.json";
    protected StudentDBIO() {
        super();
    }

    public static StudentDBIO getInstance(){
        return INSTANCE;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public void saveData(){

    }

    @Override
    public void loadData() throws IOException {
        try{
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            System.out.println(json);

        } catch (IOException e) {
            System.out.println("json 파일이 존재하지 않아 생성합니다.");
            FileWriter fw = new FileWriter(new File(filePath));
            String defaultJson = "{\n  \"students\": [\n    {\n    }\n  ]\n}";
            fw.write(defaultJson);
            fw.close();
        }

    }

    @Override
    public void input() {
        //add
        System.out.println("StudentDBIO input 이벤트!");
    }

    //DBIO 에서 일어나는 행위는 콜백이 필요하다.
    //StudentManager의 수행 시점은 DBIO의 이벤트 이 후 필요.
    @Override
    public void output(){
        System.out.println(getClass().getName());
    }
    //출력 할 데이터를 json 파일에서 로드한다.

    @Override
    public void sortByName() {
        System.out.println(getClass().getName());
    }
    //json 파일에서 읽어온 데이터를 학번 순 으로 정렬한다.

    @Override
    public void sortByTotal() {
        System.out.println(getClass().getName());
    }
    //json 파일에서 읽어온 데이터를 성적 순 으로 정렬한다.

    @Override
    public void search(String sno){
        System.out.println(getClass().getName());
    }
    //json 파일에서 특정 학번으로 데이터를 찾는다.

    //input output sort search 는 DBIO의 책임
    //DBIO가 반환한 데이터를 StudentManager가 입력받음
    //학생 관련 데이터를 가공하는 메서드는 해당 클래스에서 추상함수로 명시

    abstract void print();
    //서브클래스가 print 메서드를 강제 구현 하도록
    //print는 유저에게 보여 줄 데이터
    //최종 데이터는 Manager에게 있으니 Manager의 list를 출력 하면 됨.
}
