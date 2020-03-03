package com.xiaomi.push;

import android.os.AsyncTask;

class cr extends AsyncTask<String, Integer, Integer> {

    /* renamed from: a  reason: collision with root package name */
    cv f12680a;
    String b;
    String c;
    co d;

    public cr(cv cvVar, String str, String str2, co coVar) {
        this.b = str;
        this.c = str2;
        this.f12680a = cvVar;
        this.d = coVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Integer doInBackground(String... strArr) {
        return Integer.valueOf(cs.a(this.b, this.c, this.d));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        if (this.f12680a != null) {
            this.f12680a.a(num, this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        if (this.f12680a != null) {
            this.f12680a.a(1, this.d);
        }
    }
}
