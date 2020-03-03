package com.xiaomi.smarthome.scene.location.model;

import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UsrLocInfoManger {

    /* renamed from: a  reason: collision with root package name */
    private static UsrLocInfoManger f21612a;
    /* access modifiers changed from: private */
    public List<UsrLocInfo> b = new ArrayList();
    /* access modifiers changed from: private */
    public boolean c = false;

    public static UsrLocInfoManger a() {
        if (f21612a == null) {
            synchronized (UsrLocInfoManger.class) {
                if (f21612a == null) {
                    f21612a = new UsrLocInfoManger();
                }
            }
        }
        return f21612a;
    }

    private UsrLocInfoManger() {
    }

    public void a(final UsrLocInfo usrLocInfo, final AsyncCallback<UsrLocInfo, Error> asyncCallback) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), usrLocInfo, (AsyncCallback<Integer, Error>) new AsyncCallback<Integer, Error>() {
            /* renamed from: a */
            public void onSuccess(Integer num) {
                usrLocInfo.a(num.intValue());
                UsrLocInfoManger.this.b.add(usrLocInfo);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(usrLocInfo);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void b(final UsrLocInfo usrLocInfo, final AsyncCallback<UsrLocInfo, Error> asyncCallback) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), usrLocInfo, (AsyncCallback<Integer, Error>) new AsyncCallback<Integer, Error>() {
            /* renamed from: a */
            public void onSuccess(Integer num) {
                int i = 0;
                while (true) {
                    if (i >= UsrLocInfoManger.this.b.size()) {
                        i = -1;
                        break;
                    } else if (((UsrLocInfo) UsrLocInfoManger.this.b.get(i)).b() == usrLocInfo.b()) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i != -1) {
                    UsrLocInfoManger.this.b.remove(i);
                    UsrLocInfoManger.this.b.add(i, usrLocInfo);
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(usrLocInfo);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<UsrLocInfo> list) {
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> e = list.get(i).e();
            if (!e.isEmpty() && e.containsKey("template")) {
                String str = (String) e.get("template");
                if (TextUtils.equals(str, "home")) {
                    z = true;
                } else if (TextUtils.equals(str, "office")) {
                    z2 = true;
                }
            }
        }
        if (!z) {
            list.add(UsrLocInfo.f());
        }
        if (!z2) {
            list.add(UsrLocInfo.g());
        }
        Collections.sort(list);
        this.b = list;
    }

    public void c(final UsrLocInfo usrLocInfo, final AsyncCallback<UsrLocInfo, Error> asyncCallback) {
        RemoteFamilyApi.a().b(SHApplication.getAppContext(), usrLocInfo, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(usrLocInfo);
                }
                UsrLocInfoManger.this.a(usrLocInfo);
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(UsrLocInfo usrLocInfo) {
        int i = 0;
        while (true) {
            try {
                if (i >= this.b.size()) {
                    i = -1;
                    break;
                } else if (this.b.get(i).b() == usrLocInfo.b()) {
                    break;
                } else {
                    i++;
                }
            } catch (Exception unused) {
                return;
            }
        }
        if (i != -1 && i < this.b.size()) {
            this.b.remove(i);
        }
    }

    public boolean b() {
        return this.c;
    }

    public void a(final AsyncCallback<List<UsrLocInfo>, Error> asyncCallback) {
        if (!this.c) {
            RemoteFamilyApi.a().f(SHApplication.getAppContext(), (AsyncCallback<List<UsrLocInfo>, Error>) new AsyncCallback<List<UsrLocInfo>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<UsrLocInfo> list) {
                    if (list == null) {
                        UsrLocInfoManger.this.b.clear();
                    } else {
                        UsrLocInfoManger.this.a(list);
                    }
                    boolean unused = UsrLocInfoManger.this.c = true;
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(list);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess(this.b);
        }
    }

    public void b(final AsyncCallback<List<UsrLocInfo>, Error> asyncCallback) {
        RemoteFamilyApi.a().f(SHApplication.getAppContext(), (AsyncCallback<List<UsrLocInfo>, Error>) new AsyncCallback<List<UsrLocInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<UsrLocInfo> list) {
                if (list == null) {
                    UsrLocInfoManger.this.b.clear();
                } else {
                    UsrLocInfoManger.this.a(list);
                }
                boolean unused = UsrLocInfoManger.this.c = true;
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(list);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }
}
