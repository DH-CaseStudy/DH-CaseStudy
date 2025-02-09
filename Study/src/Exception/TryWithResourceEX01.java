package Exception;

import java.io.FileWriter;

public class TryWithResourceEX01 {
    public static void main(String[] args) {
        try(FileWriter fw = new FileWriter("text.txt")) {
            fw.write("test입니다");
        } catch (Exception e) {
            System.out.println("오류 발생입니다.");
        }
    }
}
