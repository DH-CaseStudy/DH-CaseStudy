package StudentManagementRefactor;

import java.io.IOException;
import java.util.HashMap;
/**
 * 학생 데이터를 입력하는 기능을 정의하는 인터페이스.
 * <p>
 * 이 인터페이스를 구현하는 클래스는 학생 데이터를 저장하는 기능을 제공해야 한다.
 * </p>
 *
 * @author 이동휘
 * @version 1.0
 * @since 2024-02-16
 */
public abstract class ObjectIO {
    /**
     * 학생 데이터를 입력하는 메서드.
     * <p>
     * 이 메서드는 학생 데이터를 받아서 저장하는 기능을 수행해야 한다.
     * 구현 클래스에서 실제 입력 방식(DB 저장, 파일 저장 등)을 정의해야 한다.
     *  추후에 Student는 Object로 전환해야 할 것 같다!
     *  Employee 통합 고려해야함.
     * </p>
     *
     * @return json파일로부터 불러온 테이블의 데이터를 반환한다.
     */
    abstract HashMap<String, Student> loadData() throws IOException;

}
