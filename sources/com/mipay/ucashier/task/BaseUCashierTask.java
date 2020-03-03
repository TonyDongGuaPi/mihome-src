package com.mipay.ucashier.task;

import android.content.Context;
import android.util.Log;
import com.mipay.common.base.a;
import com.mipay.common.data.d;
import com.mipay.common.data.h;
import com.mipay.common.data.i;
import com.mipay.common.exception.e;
import com.mipay.common.exception.f;
import com.mipay.common.exception.g;
import com.mipay.ucashier.task.BaseUCashierTask.Result;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseUCashierTask<Progress, TaskResult extends Result> extends a<Progress, TaskResult> {
    private static final String b = "BaseUCashierTask";

    /* renamed from: a  reason: collision with root package name */
    protected Context f8187a;

    public static class Result extends a.C0063a {
        public int mErrorCode;
        public String mErrorDesc;
    }

    public BaseUCashierTask(Context context, Class<TaskResult> cls) {
        super(cls);
        this.f8187a = context;
    }

    public BaseUCashierTask(Context context, Class<TaskResult> cls, boolean z) {
        super(cls, z);
        this.f8187a = context;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(h hVar, TaskResult taskresult) throws f {
        if (i.a(this.f8187a)) {
            b(hVar, taskresult);
            return;
        }
        throw new e();
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, TaskResult taskresult) throws f {
    }

    /* access modifiers changed from: protected */
    public abstract com.mipay.common.data.e b(h hVar);

    /* access modifiers changed from: protected */
    public void b(h hVar, TaskResult taskresult) throws f {
        JSONObject e = b(hVar).e();
        a(e, taskresult);
        try {
            int i = e.getInt("errcode");
            String optString = e.optString("errDesc");
            taskresult.mErrorCode = i;
            taskresult.mErrorDesc = optString;
            if (i != 200) {
                if (d.DEBUG) {
                    Log.w(b, "result error : error code " + i);
                    Log.w(b, "result error : error desc " + optString);
                }
                b(e, taskresult);
                return;
            }
            c(e, taskresult);
        } catch (JSONException e2) {
            throw new g("error code not exists", e2);
        }
    }

    /* access modifiers changed from: protected */
    public void b(JSONObject jSONObject, TaskResult taskresult) throws f {
    }

    /* access modifiers changed from: protected */
    public void c(JSONObject jSONObject, TaskResult taskresult) throws f {
    }
}
