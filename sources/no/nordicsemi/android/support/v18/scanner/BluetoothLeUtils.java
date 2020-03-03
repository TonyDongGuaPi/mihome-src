package no.nordicsemi.android.support.v18.scanner;

import android.bluetooth.BluetoothAdapter;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class BluetoothLeUtils {
    BluetoothLeUtils() {
    }

    static String a(@Nullable SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return "null";
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BLOCK_START);
        for (int i = 0; i < sparseArray.size(); i++) {
            sb.append(sparseArray.keyAt(i));
            sb.append("=");
            sb.append(Arrays.toString(sparseArray.valueAt(i)));
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    static <T> String a(@Nullable Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BLOCK_START);
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Object key = it.next().getKey();
            sb.append(key);
            sb.append("=");
            sb.append(Arrays.toString(map.get(key)));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    static boolean a(@Nullable SparseArray<byte[]> sparseArray, @Nullable SparseArray<byte[]> sparseArray2) {
        if (sparseArray == sparseArray2) {
            return true;
        }
        if (sparseArray == null || sparseArray2 == null || sparseArray.size() != sparseArray2.size()) {
            return false;
        }
        for (int i = 0; i < sparseArray.size(); i++) {
            if (sparseArray.keyAt(i) != sparseArray2.keyAt(i) || !Arrays.equals(sparseArray.valueAt(i), sparseArray2.valueAt(i))) {
                return false;
            }
        }
        return true;
    }

    static <T> boolean a(@Nullable Map<T, byte[]> map, Map<T, byte[]> map2) {
        if (map == map2) {
            return true;
        }
        if (map == null || map2 == null || map.size() != map2.size()) {
            return false;
        }
        Set<T> keySet = map.keySet();
        if (!keySet.equals(map2.keySet())) {
            return false;
        }
        for (T next : keySet) {
            if (!Objects.a(map.get(next), map2.get(next))) {
                return false;
            }
        }
        return true;
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    static void a(@Nullable BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter == null || bluetoothAdapter.getState() != 12) {
            throw new IllegalStateException("BT Adapter is not turned ON");
        }
    }
}
