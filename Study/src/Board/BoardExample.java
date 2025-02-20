package Board;

import StudentManagementRefactor.Utility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardExample {
    private static final String filePath = "src/Board/board.json";
    private static List<Board> boardList = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
        boardList = parseJson();

    }

    public static void mainMenu() {
        while (true) {
            System.out.println("[게시물 목록]");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("no    writer       date        title");
            System.out.println("-----------------------------------------------------------------");
            boardList = parseJson();
            for (Board board : boardList) {
                System.out.println(board);
            }
            System.out.println("-----------------------------------------------------------------");
            System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
            System.out.print("메뉴 선택: ");
            int input = Utility.readInput(Integer.class);

            switch (input) {
                case 1:
                    create();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    clear();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    break;
            }
        }

    }

    private static void create() {
        System.out.print("제목 : ");
        String title = Utility.readInput(String.class);
        System.out.print("내용 : ");
        String content = Utility.readInput(String.class);
        System.out.print("작성자 : ");
        String writer = Utility.readInput(String.class);

        Board board = new Board(boardList.size() + 1, title, content, writer, new Date());
        boardList.add(board);
        saveToJson();

    }

    private static void saveToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Board board : boardList) {
            JSONObject boardObj = new JSONObject();
            boardObj.put("bno", board.getBno());
            boardObj.put("btitle", board.getBtitle());
            boardObj.put("bcontent", board.getBcontent());
            boardObj.put("bwriter", board.getBwriter());
            boardObj.put("Date", board.getFormattedDate()); // Date → String 변환 후 저장

            jsonArray.add(boardObj);
        }

        try (FileWriter file = new FileWriter(filePath)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("board", jsonArray);
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        System.out.println();
        System.out.println("[게시물 읽기]");
        System.out.print("번호를 입력하세요 : ");
        int bno = Utility.readInput(Integer.class);

        boardList.stream()
                .filter(board -> board.getBno() == bno) // 입력된 번호와 일치하는 게시글 찾기
                .findFirst()
                .ifPresentOrElse(
                        board -> { // 게시글이 존재할 경우
                            System.out.println("##############");
                            System.out.println("번호: " + board.getBno());
                            System.out.println("제목: " + board.getBtitle());
                            System.out.println("내용: " + board.getBcontent());
                            System.out.println("작성자: " + board.getBwriter());
                            System.out.println("날짜: " + board.getFormattedDate());
                            System.out.println("##############");
                            System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3.List");
                            System.out.print("메뉴 선택 : ");
                            int select = Utility.readInput(Integer.class);

                            switch (select) {
                                case 1:
                                    updateBoard(board);
                                    System.out.println("보조 메뉴 : 1.OK | 2.Cancel");
                                    System.out.print("메뉴 선택: ");
                                    int okOrCacancel1 = Utility.readInput(Integer.class);
                                    if(okOrCacancel1 == 1){
                                        saveToJson(); // JSON 파일에 반영
                                        System.out.println("게시글이 수정되었습니다.");
                                    } else{
                                        System.out.println("취소 되었습니다.");
                                    }
                                    break;

                                case 2:
                                    System.out.println("보조 메뉴 : 1.OK | 2.Cancel");
                                    System.out.print("메뉴 선택: ");
                                    int okOrCacancel2 = Utility.readInput(Integer.class);
                                    if(okOrCacancel2 == 1){
                                        deleteBoard(board);
                                        saveToJson(); // JSON 파일에 반영
                                        System.out.println("게시글이 삭제되었습니다.");
                                    } else{
                                        System.out.println("취소 되었습니다.");
                                    }
                                    break;

                                case 3:
                                    System.out.println("🔙 리스트로 돌아갑니다.");
                                    break;

                                default:
                                    System.out.println("❌ 잘못된 입력입니다.");
                            }
                        },
                        () -> System.out.println("❌ 없는 게시물입니다.") // 게시글이 없을 경우
                );

        System.out.println();
    }

    private static void updateBoard(Board board) {
        System.out.println("[수정 내용 입력]");
        System.out.print("제목: ");
        String title = Utility.readInput(String.class);
        System.out.print("내용: ");
        String content = Utility.readInput(String.class);
        System.out.print("작성자: ");
        String writer = Utility.readInput(String.class);

        board.setBtitle(title);
        board.setBcontent(content);
        board.setBwriter(writer);
    }

    private static void deleteBoard(Board board) {
        boardList.removeIf(b -> b.getBno() == board.getBno());
    }

    private static void clear(){
        System.out.println("[게시물 전체 삭제]");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.OK | 2.Cancel");
        System.out.print("메뉴 선택: ");
        int okOrCancel = Utility.readInput(Integer.class);
        if(okOrCancel == 1){
            System.out.println("게시물이 모두 삭제 되었습니다.");
            boardList.clear();
            saveToJson();
        }
    }

    private static void exit() {
        System.exit(0);
    }

    private static List<Board> parseJson() {
        List<Board> boardList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return boardList; // 에러케이스
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            if (json.trim().isEmpty() || json.equals("{ \"board\": [] }")) {
                return boardList;
            }

            JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
            JSONArray jsonArray = (JSONArray) jsonObject.get("board");

            for (Object obj : jsonArray) {
                JSONObject boardObj = (JSONObject) obj;

                int bno = ((Long) boardObj.get("bno")).intValue(); // 숫자 변환
                String btitle = (String) boardObj.get("btitle");
                String bcontent = (String) boardObj.get("bcontent");
                String bwriter = (String) boardObj.get("bwriter");

                Date bdate = parseDate((String) boardObj.get("Date"));

                Board board = new Board(bno, btitle, bcontent, bwriter, bdate);

                boardList.add(board);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return boardList;
    }

    private static Date parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return sdf.parse(dateString);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
