package Exception;

public class ExceptionTest02 {
    public static void main(String[] args) {
        int[] array = new int[3];
        try {
            array[0] = 1;
            array[1] = 2;
            array[2] = 3;
            array[3] = 4;
        } catch (Exception e) {
            System.out.println("배열 에러입니다.");
        } finally {
            System.out.println("finally");
        }
    }
}
