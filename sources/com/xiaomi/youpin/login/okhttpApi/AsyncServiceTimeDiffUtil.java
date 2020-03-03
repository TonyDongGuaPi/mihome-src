package com.xiaomi.youpin.login.okhttpApi;

import android.os.AsyncTask;
import android.util.Pair;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;

public class AsyncServiceTimeDiffUtil {
    public static void a(AsyncCallback<Long, Error> asyncCallback) {
        AsyncTaskUtils.a(new GetTimeDiffAsyncTask(asyncCallback), new Object[0]);
    }

    private static class GetTimeDiffAsyncTask extends AsyncTask<Object, Object, Pair<Long, Boolean>> {

        /* renamed from: a  reason: collision with root package name */
        private AsyncCallback<Long, Error> f23530a;

        public GetTimeDiffAsyncTask(AsyncCallback<Long, Error> asyncCallback) {
            this.f23530a = asyncCallback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Pair<Long, Boolean> doInBackground(Object... objArr) {
            return MiLoginApi.a().h().c();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Pair<Long, Boolean> pair) {
            long longValue = ((Long) pair.first).longValue();
            if (((Boolean) pair.second).booleanValue()) {
                this.f23530a.b(Long.valueOf(longValue));
            } else {
                this.f23530a.b(null);
            }
        }
    }
}
