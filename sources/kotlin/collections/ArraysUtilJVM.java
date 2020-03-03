package kotlin.collections;

import java.util.Arrays;
import java.util.List;

class ArraysUtilJVM {
    ArraysUtilJVM() {
    }

    static <T> List<T> a(T[] tArr) {
        return Arrays.asList(tArr);
    }
}
