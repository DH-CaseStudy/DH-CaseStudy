package StudentManagementRefactor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class StudentDBIO extends ObjectIO implements StudentIO {
    //파일의 입출력을 담당한다 이후에 DB에 접근하는 로직으로 변경.

    private static StudentDBIO instance;
    //왜 static final 로 선언했을까?
    //파일 입출력(혹은 DB)는 전역에 단일 객체로 접근하는것이 안전하다고 생각.
    //여러 객체가 생성되서 테이블의 무결성이 훼손되는 것을 방지한다.
    //StudentManager 클래스는 StudentDBIO를 상속받았기 때문에 StudentDBIO 타입의 인스턴스 생성이 가능하다.

    private static final String filePath = "src/StudentManagementRefactor/students.json";

    protected StudentDBIO() {
        super();
    }

    public static StudentDBIO getInstance() {
        if (instance == null) {
            instance = StudentManager.getInstance(); // 기본 인스턴스를 StudentManager로 설정
        }
        return instance;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public void saveData(Object student) {
        if (!(student instanceof Student)) {
            System.out.println("⚠️ 잘못된 데이터 타입입니다.");
            return;
        }

        Student newStudent = (Student) student;
        File file = new File(filePath);

        try {
            // ✅ 기존 데이터 불러오기
            String json = file.exists() ? new String(Files.readAllBytes(Paths.get(filePath))) : "{ \"students\": [] }";
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
            JSONArray studentArray = (JSONArray) jsonObject.get("students");

            // ✅ 새로운 학생 데이터 추가
            JSONObject studentObj = new JSONObject();
            studentObj.put("sno", newStudent.getSno());
            studentObj.put("name", newStudent.getName());
            studentObj.put("korean", newStudent.getKorean());
            studentObj.put("english", newStudent.getEnglish());
            studentObj.put("math", newStudent.getMath());
            studentObj.put("science", newStudent.getScience());
            studentObj.put("total", newStudent.getTotal());
            studentObj.put("average", newStudent.getAverage());
            studentObj.put("grade", newStudent.getGrade());

            studentArray.add(studentObj);
            jsonObject.put("students", studentArray);

            // ✅ JSON 파일에 저장
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsonObject.toJSONString());
            }

            System.out.println("✅ 학생 정보가 JSON 파일에 추가되었습니다!");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadData() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("{ \"students\": [] }"); // ✅ 빈 배열을 기본값으로 설정
            }
            return;
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            if (json.trim().isEmpty() || json.equals("{ \"students\": [] }"))
            {
                System.out.println("데이터가 비어있습니다.");
            } else{
                List<Student> studentList = parseJson(json);
                StudentManager.getInstance().setStudentList(studentList);
                for (Student student : StudentManager.getInstance().getStudentList()) {
                    System.out.println(student);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Student> parseJson(String json) {
        List<Student> students = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            // ✅ JSON 문자열을 `JSONObject`로 변환
            JSONObject jsonObject = (JSONObject) parser.parse(json);

            // ✅ "students" 키에서 `JSONArray` 가져오기
            JSONArray jsonArray = (JSONArray) jsonObject.get("students");

            // ✅ 배열을 순회하며 `Student` 객체 생성
            for (Object obj : jsonArray) {
                JSONObject studentObj = (JSONObject) obj;
                Student student = new Student(
                        (String) studentObj.get("sno"),
                        (String) studentObj.get("name"),
                        ((Long) studentObj.get("korean")).intValue(),
                        ((Long) studentObj.get("english")).intValue(),
                        ((Long) studentObj.get("math")).intValue(),
                        ((Long) studentObj.get("science")).intValue(),
                        ((Long) studentObj.get("total")).intValue(),
                        ((Double) studentObj.get("average")).floatValue(), // 평균을 Float으로 변환
                        (String) studentObj.get("grade")
                );
                students.add(student);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return students;
    }


    @Override
    public void input() {
        //add
        System.out.println("StudentDBIO input 이벤트!");
    }

    //DBIO 에서 일어나는 행위는 콜백이 필요하다.
    //StudentManager의 수행 시점은 DBIO의 이벤트 이 후 필요.
    @Override
    public void output() {
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
    public void search(String sno) {
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
