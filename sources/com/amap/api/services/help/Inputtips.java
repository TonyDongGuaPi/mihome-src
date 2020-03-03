package com.amap.api.services.help;

import android.content.Context;
import com.amap.api.services.a.bf;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IInputtipsSearch;
import java.util.List;

public final class Inputtips {

    /* renamed from: a  reason: collision with root package name */
    private IInputtipsSearch f4467a = null;

    public interface InputtipsListener {
        void onGetInputtips(List<Tip> list, int i);
    }

    public Inputtips(Context context, InputtipsListener inputtipsListener) {
        if (this.f4467a == null) {
            try {
                this.f4467a = new bf(context, inputtipsListener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Inputtips(Context context, InputtipsQuery inputtipsQuery) {
        if (this.f4467a == null) {
            try {
                this.f4467a = new bf(context, inputtipsQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public InputtipsQuery getQuery() {
        if (this.f4467a != null) {
            return this.f4467a.getQuery();
        }
        return null;
    }

    public void setQuery(InputtipsQuery inputtipsQuery) {
        if (this.f4467a != null) {
            this.f4467a.setQuery(inputtipsQuery);
        }
    }

    public void setInputtipsListener(InputtipsListener inputtipsListener) {
        if (this.f4467a != null) {
            this.f4467a.setInputtipsListener(inputtipsListener);
        }
    }

    public void requestInputtipsAsyn() {
        if (this.f4467a != null) {
            this.f4467a.requestInputtipsAsyn();
        }
    }

    public List<Tip> requestInputtips() throws AMapException {
        if (this.f4467a != null) {
            return this.f4467a.requestInputtips();
        }
        return null;
    }

    public void requestInputtips(String str, String str2) throws AMapException {
        if (this.f4467a != null) {
            this.f4467a.requestInputtips(str, str2);
        }
    }

    public void requestInputtips(String str, String str2, String str3) throws AMapException {
        if (this.f4467a != null) {
            this.f4467a.requestInputtips(str, str2, str3);
        }
    }
}
