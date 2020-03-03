package com.taobao.weex.utils;

import android.support.annotation.NonNull;
import com.taobao.weex.utils.FunctionParser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SingleFunctionParser<V> extends FunctionParser<String, List<V>> {
    private static transient /* synthetic */ boolean[] $jacocoData;

    public interface FlatMapper<V> {
        V map(String str);
    }

    public interface NonUniformMapper<V> {
        List<V> map(List<String> list);
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-7755039360059739898L, "com/taobao/weex/utils/SingleFunctionParser", 6);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleFunctionParser(@NonNull String str, @NonNull final FlatMapper<V> flatMapper) {
        super(str, new FunctionParser.Mapper<String, List<V>>() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(4107557431204982293L, "com/taobao/weex/utils/SingleFunctionParser$1", 7);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public Map<String, List<V>> map(String str, List<String> list) {
                boolean[] $jacocoInit = $jacocoInit();
                HashMap hashMap = new HashMap();
                $jacocoInit[1] = true;
                LinkedList linkedList = new LinkedList();
                $jacocoInit[2] = true;
                $jacocoInit[3] = true;
                for (String map : list) {
                    $jacocoInit[4] = true;
                    linkedList.add(flatMapper.map(map));
                    $jacocoInit[5] = true;
                }
                hashMap.put(str, linkedList);
                $jacocoInit[6] = true;
                return hashMap;
            }
        });
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SingleFunctionParser(@NonNull String str, @NonNull final NonUniformMapper<V> nonUniformMapper) {
        super(str, new FunctionParser.Mapper<String, List<V>>() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8015479947522727934L, "com/taobao/weex/utils/SingleFunctionParser$2", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public Map<String, List<V>> map(String str, List<String> list) {
                boolean[] $jacocoInit = $jacocoInit();
                HashMap hashMap = new HashMap();
                $jacocoInit[1] = true;
                hashMap.put(str, nonUniformMapper.map(list));
                $jacocoInit[2] = true;
                return hashMap;
            }
        });
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    public List<V> parse(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        LinkedHashMap parse = parse();
        $jacocoInit[2] = true;
        if (parse.containsKey(str)) {
            $jacocoInit[3] = true;
            List<V> list = (List) parse.get(str);
            $jacocoInit[4] = true;
            return list;
        }
        $jacocoInit[5] = true;
        return null;
    }
}
