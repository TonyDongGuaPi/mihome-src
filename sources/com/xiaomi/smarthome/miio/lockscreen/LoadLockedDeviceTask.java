package com.xiaomi.smarthome.miio.lockscreen;

import android.os.AsyncTask;

public class LoadLockedDeviceTask extends AsyncTask<Integer, Integer, Integer> {

    /* renamed from: a  reason: collision with root package name */
    ClientAllLockedDialog f13613a;

    public void a(ClientAllLockedDialog clientAllLockedDialog) {
        this.f13613a = clientAllLockedDialog;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Integer doInBackground(Integer... numArr) {
        if (this.f13613a == null) {
            return null;
        }
        this.f13613a.p();
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        if (this.f13613a != null) {
            this.f13613a.r();
        }
    }
}
