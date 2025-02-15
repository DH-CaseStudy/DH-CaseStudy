package StudentManagementRefactor;

import java.io.IOException;

public abstract class ObjectIO<T> {
    abstract void loadData() throws IOException;
//    abstract void saveData(Object student); //추후 Employee 고려하여 Object 타입으로

}
