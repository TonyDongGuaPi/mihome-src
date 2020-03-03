package com.ximalaya.ting.android.opensdk.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import java.lang.reflect.Type;

public class AsyncGson<T> extends MyAsyncTask<Object, Void, Object> {

    /* renamed from: a  reason: collision with root package name */
    private IResult<T> f2249a;
    private IResult<T> b;

    public interface IResult<T> {
        void a(Exception exc);

        void a(T t);
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        if (objArr.length == 1) {
            try {
                String json = new Gson().toJson(objArr[0]);
                if (this.b != null) {
                    this.b.a(json);
                }
                return json;
            } catch (Exception e) {
                e.printStackTrace();
                if (this.b != null) {
                    this.b.a(e);
                }
                return e;
            }
        } else if (objArr.length != 2) {
            return new Exception("params is error");
        } else {
            if (objArr[1] instanceof Type) {
                try {
                    return new Gson().fromJson(objArr[0], objArr[1]);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return e2;
                }
            } else {
                try {
                    return new Gson().fromJson(objArr[0], objArr[1]);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return e3;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        if (this.f2249a == null) {
            return;
        }
        if (obj instanceof Exception) {
            this.f2249a.a((Exception) obj);
        } else {
            this.f2249a.a(obj);
        }
    }

    public void a(Object obj, @NonNull IResult<T> iResult) {
        this.f2249a = iResult;
        if (obj == null) {
            iResult.a(new Exception("IllegalArgument"));
            return;
        }
        a((Params[]) new Object[]{obj});
    }

    public void b(Object obj, @NonNull IResult iResult) {
        this.b = iResult;
        if (obj == null) {
            iResult.a(new Exception("IllegalArgument"));
            return;
        }
        a((Params[]) new Object[]{obj});
    }

    public void a(String str, Type type, @NonNull IResult<T> iResult) {
        this.f2249a = iResult;
        if (TextUtils.isEmpty(str) || type == null) {
            iResult.a(new Exception("IllegalArgument"));
            return;
        }
        a((Params[]) new Object[]{str, type});
    }

    public void a(String str, Class<T> cls, @NonNull IResult<T> iResult) {
        this.f2249a = iResult;
        if (TextUtils.isEmpty(str) || cls == null) {
            iResult.a(new Exception("IllegalArgument"));
            return;
        }
        a((Params[]) new Object[]{str, cls});
    }
}
