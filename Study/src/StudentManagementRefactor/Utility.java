package StudentManagementRefactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility<T> {
    //데이터를 json에 기록하는것과 사용자에게 입력받는 input의 개념은 다름
    //json에 기록을 하는것은 DBIO의 input
    //사용자의 '입력'을 받는 것은 유틸리티 클래스의 readInput
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static <T> T readInput(Class<T> type) {
        while(true) {
            try {
                String input = br.readLine();

                if (type == String.class) {
                    return type.cast(input);
                } else if (type == Integer.class) {
                    return type.cast(Integer.parseInt(input));
                } else if (type == Double.class) {
                    return type.cast(Double.parseDouble(input));
                } else if (type == Float.class) {
                    return type.cast(Float.parseFloat(input));
                } else {
                    System.out.println("지원 하는 타입이 아닙니다");
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("올바른 타입을 입력하세요.");
            } catch (NullPointerException e) {
                System.out.println("잘못 입력 하였습니다.");
            }

            return null;
        }
    }
}
