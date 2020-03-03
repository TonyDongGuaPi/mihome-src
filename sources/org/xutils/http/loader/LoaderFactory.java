package org.xutils.http.loader;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

public final class LoaderFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<Type, Loader> f10785a = new HashMap<>();

    private LoaderFactory() {
    }

    static {
        f10785a.put(JSONObject.class, new JSONObjectLoader());
        f10785a.put(JSONArray.class, new JSONArrayLoader());
        f10785a.put(String.class, new StringLoader());
        f10785a.put(File.class, new FileLoader());
        f10785a.put(byte[].class, new ByteArrayLoader());
        BooleanLoader booleanLoader = new BooleanLoader();
        f10785a.put(Boolean.TYPE, booleanLoader);
        f10785a.put(Boolean.class, booleanLoader);
        IntegerLoader integerLoader = new IntegerLoader();
        f10785a.put(Integer.TYPE, integerLoader);
        f10785a.put(Integer.class, integerLoader);
    }

    public static Loader<?> a(Type type, RequestParams requestParams) {
        Loader<?> loader;
        Loader loader2 = f10785a.get(type);
        if (loader2 == null) {
            loader = new ObjectLoader(type);
        } else {
            loader = loader2.a();
        }
        loader.a(requestParams);
        return loader;
    }

    public static <T> void a(Type type, Loader<T> loader) {
        f10785a.put(type, loader);
    }
}
