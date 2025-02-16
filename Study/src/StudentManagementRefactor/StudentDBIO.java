package StudentManagementRefactor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class StudentDBIO extends ObjectIO implements StudentIO {
    // * 데이터 저장, 로드, JSON 파싱 담당 (saveData(), loadData())

    private static StudentDBIO instance;
    //파일 입출력(혹은 DB)는 전역에 단일 객체로 접근하는것이 안전하다고 생각.
    //여러 객체가 생성되서 테이블의 무결성이 훼손되는 것을 방지한다.

    private static final String filePath = "src/StudentManagementRefactor/students.json";

    protected StudentDBIO() { }

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
    public void loadData() throws IOException {
        //json 파일에 있는 모든 학생들의 정보를 읽어들인다.
        //read 역할
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
                HashMap<String, Student> studentList = parseJson(json);
                StudentManager.getInstance().setStudentList(studentList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void input(Object student) {
        if (!(student instanceof Student)) {
            System.out.println("잘못된 데이터 타입입니다.");
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

            System.out.println("학생 정보가 입력되었습니다.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void search(String sno) {

    }

    @Override
    public void sortByName() {

    }

    @Override
    public void sortByTotal() {

    }

    @Override
    public void output() {

    }

    @Override
    public void deleteStudent(String sno) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("데이터 파일이 존재하지 않습니다.");
            return;
        }

        try {
            // ✅ JSON 파일 읽기
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
            JSONArray studentArray = (JSONArray) jsonObject.get("students");

            // ✅ 학번(`sno`)에 해당하는 학생 찾기
            boolean found = false;
            for (int i = 0; i < studentArray.size(); i++) {
                JSONObject studentObj = (JSONObject) studentArray.get(i);
                if (studentObj.get("sno").equals(sno)) {
                    studentArray.remove(i); // ✅ 해당 학생 제거
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println(" 학번 '" + sno + "'에 해당하는 학생을 찾을 수 없습니다.");
                return;
            }

            // 수정된 JSON 데이터 저장
            jsonObject.put("students", studentArray);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsonObject.toJSONString());
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    private HashMap<String, Student> parseJson(String json) {
        HashMap<String, Student> students = new HashMap<>();
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
                        ((Double) studentObj.get("average")).floatValue(),
                        (String) studentObj.get("grade")
                );
                students.put(student.getSno(), student);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return students;
    }

}
