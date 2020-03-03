package com.ximalaya.ting.android.opensdk.player.service;

import android.os.RemoteException;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.constants.PlayerConstants;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmPlayListControl {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2200a = "positive_seq";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static final String e = "XmPlayListControl";
    private int f = 1;
    private CommonRequest g = CommonRequest.a();
    /* access modifiers changed from: private */
    public List<Track> h = new ArrayList();
    private PlayableModel i;
    private PlayableModel j;
    private Map<String, String> k;
    private int l;
    private int m;
    private int n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public volatile int p = -1;
    private int q = -1;
    private PlayMode r = PlayMode.PLAY_MODEL_LIST;
    /* access modifiers changed from: private */
    public int s;
    /* access modifiers changed from: private */
    public boolean t = false;
    private boolean u = true;
    /* access modifiers changed from: private */
    public boolean v = true;
    /* access modifiers changed from: private */
    public IXmDataCallback w;

    private int u() {
        return -1;
    }

    private int v() {
        return -1;
    }

    static /* synthetic */ int b(XmPlayListControl xmPlayListControl) {
        int i2 = xmPlayListControl.m;
        xmPlayListControl.m = i2 - 1;
        return i2;
    }

    static /* synthetic */ int f(XmPlayListControl xmPlayListControl) {
        int i2 = xmPlayListControl.l;
        xmPlayListControl.l = i2 + 1;
        return i2;
    }

    public boolean a() {
        return this.t;
    }

    public PlayMode a(PlayMode playMode) {
        this.r = playMode;
        return playMode;
    }

    public PlayMode b() {
        return this.r;
    }

    public int c() {
        if (n() == null) {
            return 1;
        }
        Track track = (Track) n();
        if (TextUtils.isEmpty(track.b())) {
            return 1;
        }
        if ("radio".endsWith(track.b())) {
            this.f = 3;
        } else if ("track".endsWith(track.b())) {
            this.f = 2;
        } else if ("schedule".endsWith(track.b())) {
            String str = track.ax() + "-" + track.ay();
            if (BaseUtil.a(str) == 0) {
                this.f = 3;
            } else if (BaseUtil.a(str) == -1) {
                this.f = 2;
            }
        }
        return this.f;
    }

    public List<Track> d() {
        return this.h;
    }

    public Map<String, String> e() {
        if (this.k == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.k);
        hashMap.put(DTransferConstants.Q, this.m + "");
        hashMap.put("page", this.l + "");
        hashMap.put(f2200a, this.v + "");
        hashMap.put(DTransferConstants.P, this.o + "");
        hashMap.put(DTransferConstants.aQ, this.u + "");
        return hashMap;
    }

    public Radio f() {
        if (this.j instanceof Radio) {
            return (Radio) this.j;
        }
        return null;
    }

    public Radio g() {
        if (this.i instanceof Radio) {
            return (Radio) this.i;
        }
        return null;
    }

    public void a(Radio radio) {
        if (radio != null) {
            if (this.f != 3) {
                h();
                this.f = 3;
            }
            if (!radio.equals(this.i)) {
                this.j = this.i;
                this.i = radio;
            }
        }
    }

    public void h() {
        synchronized (this.h) {
            this.k = null;
            this.h.clear();
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = -1;
            this.s = 0;
            this.q = -1;
            this.i = null;
            this.j = null;
            this.v = true;
        }
    }

    public void a(Map<String, String> map, List<Track> list) {
        if (map != null) {
            if (map.containsKey(DTransferConstants.aQ)) {
                this.u = Boolean.parseBoolean(map.remove(DTransferConstants.aQ));
            } else {
                if (map.containsKey("asc")) {
                    this.u = Boolean.parseBoolean(map.get("asc"));
                }
                if (map.containsKey("isAsc")) {
                    this.u = Boolean.parseBoolean(map.get("isAsc"));
                }
            }
            if (!map.containsKey(DTransferConstants.K)) {
                map = null;
            }
        }
        this.f = 2;
        h();
        synchronized (this.h) {
            this.o = 0;
            this.k = map;
            if (this.k != null) {
                if (map.containsKey("trackId")) {
                    map.remove("trackId");
                }
                String remove = this.k.remove(f2200a);
                if (!TextUtils.isEmpty(remove)) {
                    this.v = Boolean.valueOf(remove).booleanValue();
                }
                if (!this.k.containsKey(DTransferConstants.P) || this.k.get(DTransferConstants.P) == null) {
                    this.o = 0;
                } else {
                    this.o = Integer.valueOf(this.k.remove(DTransferConstants.P)).intValue() + 1;
                }
                if (!this.k.containsKey("count") || this.k.get("count") == null) {
                    this.n = 0;
                } else {
                    this.n = Integer.valueOf(this.k.get("count")).intValue();
                }
                if (this.n <= 0) {
                    this.n = this.g.q();
                }
                if (!this.k.containsKey("page") || this.k.get("page") == null) {
                    this.l = 0;
                } else {
                    this.l = Integer.valueOf(this.k.get("page")).intValue();
                }
                if (this.l <= 0) {
                    this.l = list.size() / this.n;
                }
                if (!this.k.containsKey(DTransferConstants.Q) || this.k.get(DTransferConstants.Q) == null) {
                    this.m = 0;
                } else {
                    this.m = Integer.valueOf(this.k.get(DTransferConstants.Q)).intValue();
                    if (this.m < 0) {
                        this.m = 0;
                    }
                }
            } else {
                this.n = 0;
                this.l = 0;
                this.m = 0;
            }
            this.h.clear();
            this.h.addAll(list);
            this.s = this.h.size();
            if (this.h.contains(this.i)) {
                this.p = this.h.indexOf(this.i);
            } else {
                this.p = -1;
            }
        }
    }

    public void a(List<Track> list) {
        synchronized (this.h) {
            if (this.h == null) {
                this.h = new ArrayList();
            }
            this.h.addAll(list);
            this.s = this.h.size();
            if (this.h.contains(this.i)) {
                this.p = this.h.indexOf(this.i);
            }
        }
    }

    public void b(List<Track> list) {
        synchronized (this.h) {
            if (this.h == null) {
                this.h = new ArrayList();
            }
            this.h.addAll(0, list);
            this.s = this.h.size();
            if (this.h.contains(this.i)) {
                this.p = this.h.indexOf(this.i);
            }
        }
    }

    public int i() {
        return this.s;
    }

    public void a(int i2, Track track) {
        d().set(i2, track);
        if (this.i != null && track != null && this.i.a() == track.a()) {
            this.i = track;
        }
    }

    public int a(boolean z) {
        if (this.f != 3 && this.f == 2) {
            return d(z);
        }
        return -1;
    }

    public Track j() {
        if (this.p < 0 || this.p + 1 >= this.h.size()) {
            return null;
        }
        return this.h.get(this.p + 1);
    }

    public int k() {
        return w();
    }

    public int l() {
        return this.p;
    }

    public void a(int i2) {
        if (i2 != this.p) {
            this.q = this.p;
            this.p = i2;
            this.j = this.i;
        }
        this.i = b(this.p);
        if (e(i2 + 1)) {
            t();
        }
        if (d(i2 - 1)) {
            s();
        }
    }

    private boolean d(int i2) {
        if (this.k == null || i2 - PlayerConstants.b > 0 || !q()) {
            return false;
        }
        Logger.c(e, "needLoadNextPage currPage:" + this.l + ", currPageSize:" + this.n + ", next:" + i2);
        return true;
    }

    public int m() {
        return this.q;
    }

    public PlayableModel n() {
        return this.i;
    }

    public PlayableModel b(int i2) {
        if (this.h == null || this.h.size() <= 0 || i2 < 0 || i2 >= this.h.size()) {
            return null;
        }
        return this.h.get(i2);
    }

    public boolean o() {
        return this.u;
    }

    public synchronized boolean p() {
        this.u = !this.u;
        this.v = !this.v;
        if (this.h != null && this.h.size() > 0) {
            Collections.reverse(this.h);
            if (this.i != null) {
                this.p = this.h.indexOf(this.i);
            }
            if (this.j != null) {
                this.q = this.h.indexOf(this.j);
            }
        }
        return true;
    }

    public void b(boolean z) {
        if (!this.v && !z) {
            c(true);
        } else if (!r()) {
            if (this.w != null) {
                try {
                    this.w.onDataReady((List<Track>) null, false, this.v);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (this.k == null || this.t) {
            a(400, "加载失败", this.v);
        } else {
            t();
        }
    }

    public void c(boolean z) {
        if (!this.v && !z) {
            b(true);
        } else if (!q()) {
            if (this.w != null) {
                try {
                    this.w.onDataReady((List<Track>) null, false, true ^ this.v);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (this.k == null || this.t) {
            a(400, "加载失败", true ^ this.v);
        } else {
            s();
        }
    }

    /* access modifiers changed from: protected */
    public boolean q() {
        if (this.o > 0 && this.m < this.o && this.m > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean r() {
        if (this.o > 0 && this.l < this.o) {
            return true;
        }
        return false;
    }

    public void a(IXmDataCallback iXmDataCallback) {
        this.w = iXmDataCallback;
    }

    private void s() {
        Logger.c(e, "loadPrePageSync");
        if (this.t || this.k == null) {
            a(400, "加载失败", true ^ this.v);
            return;
        }
        this.t = true;
        Map<String, String> map = this.k;
        map.put("page", "" + this.m);
        if (!this.k.containsKey("count")) {
            Map<String, String> map2 = this.k;
            map2.put("count", "" + this.g.q());
        }
        CommonRequest.aj(this.k, new IDataCallBack<CommonTrackList>() {
            public void a(CommonTrackList commonTrackList) {
                Logger.c(XmPlayListControl.e, "CommonRequest.getTrackList 获取播放器下一页数据");
                boolean unused = XmPlayListControl.this.t = false;
                if (commonTrackList == null) {
                    XmPlayListControl.this.a(400, "加载失败", !XmPlayListControl.this.v);
                    return;
                }
                int unused2 = XmPlayListControl.this.o = commonTrackList.f();
                List c = commonTrackList.c();
                if (c == null || c.size() == 0) {
                    XmPlayListControl.this.a(400, "加载失败", !XmPlayListControl.this.v);
                    return;
                }
                XmPlayListControl.b(XmPlayListControl.this);
                synchronized (XmPlayListControl.this.h) {
                    if (XmPlayListControl.this.v) {
                        XmPlayListControl.this.h.addAll(0, c);
                        int unused3 = XmPlayListControl.this.p = XmPlayListControl.this.p + c.size();
                    } else {
                        Collections.reverse(c);
                        XmPlayListControl.this.h.addAll(c);
                    }
                }
                int unused4 = XmPlayListControl.this.s = XmPlayListControl.this.h.size();
                boolean q = XmPlayListControl.this.q();
                if (XmPlayListControl.this.w != null) {
                    try {
                        XmPlayListControl.this.w.onDataReady(c, q, !XmPlayListControl.this.v);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        XmPlayListControl.this.a(400, "加载失败", XmPlayListControl.this.v);
                    }
                }
            }

            public void a(int i, String str) {
                Logger.c(XmPlayListControl.e, "CommonRequest.getTrackList 获取播放器下一页数据 onError " + i + ", " + str);
                boolean unused = XmPlayListControl.this.t = false;
                XmPlayListControl.this.a(i, str, XmPlayListControl.this.v ^ true);
            }
        });
    }

    private void t() {
        Logger.c(e, "loadNextPageSync");
        if (this.t || this.k == null) {
            a(400, "加载失败", this.v);
            return;
        }
        this.t = true;
        Map<String, String> map = this.k;
        map.put("page", "" + (this.l + 1));
        if (!this.k.containsKey("count")) {
            Map<String, String> map2 = this.k;
            map2.put("count", "" + this.g.q());
        }
        CommonRequest.aj(this.k, new IDataCallBack<CommonTrackList>() {
            public void a(CommonTrackList commonTrackList) {
                boolean unused = XmPlayListControl.this.t = false;
                Logger.c(XmPlayListControl.e, "CommonRequest.getTrackList 获取播放器下一页数据");
                if (commonTrackList == null) {
                    XmPlayListControl.this.a(400, "加载失败", XmPlayListControl.this.v);
                    return;
                }
                int unused2 = XmPlayListControl.this.o = commonTrackList.f();
                List c = commonTrackList.c();
                if (c == null || c.size() == 0) {
                    XmPlayListControl.this.a(400, "加载失败", XmPlayListControl.this.v);
                    return;
                }
                XmPlayListControl.f(XmPlayListControl.this);
                synchronized (XmPlayListControl.this.h) {
                    if (XmPlayListControl.this.v) {
                        XmPlayListControl.this.h.addAll(c);
                    } else {
                        Collections.reverse(c);
                        XmPlayListControl.this.h.addAll(0, c);
                        int unused3 = XmPlayListControl.this.p = XmPlayListControl.this.p + c.size();
                    }
                    int unused4 = XmPlayListControl.this.s = XmPlayListControl.this.h.size();
                }
                boolean r = XmPlayListControl.this.r();
                if (XmPlayListControl.this.w != null) {
                    try {
                        XmPlayListControl.this.w.onDataReady(c, r, XmPlayListControl.this.v);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        XmPlayListControl.this.a(400, "加载失败", XmPlayListControl.this.v);
                    }
                }
            }

            public void a(int i, String str) {
                Logger.c(XmPlayListControl.e, "CommonRequest.getTrackList 获取播放器下一页数据 onError " + i + ", " + str);
                boolean unused = XmPlayListControl.this.t = false;
                XmPlayListControl.this.a(i, str, XmPlayListControl.this.v);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2, String str, boolean z) {
        if (this.w != null) {
            try {
                this.w.onError(i2, str, z);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean e(int i2) {
        if (this.k == null || PlayerConstants.b + i2 < this.s || !r()) {
            return false;
        }
        Logger.c(e, "needLoadNextPage currPage:" + this.l + ", currPageSize:" + this.n + ", next:" + i2);
        return true;
    }

    private boolean a(Map<String, String> map) {
        if (map == null || this.k == null) {
            return false;
        }
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            z &= ((String) next.getValue()).equals(this.k.get((String) next.getKey()));
        }
        return z;
    }

    private int d(boolean z) {
        int i2;
        PlayMode playMode = this.r;
        if (z && playMode == PlayMode.PLAY_MODEL_SINGLE_LOOP) {
            playMode = PlayMode.PLAY_MODEL_LIST;
        }
        switch (playMode) {
            case PLAY_MODEL_LIST:
                i2 = this.p + 1;
                if (e(i2)) {
                    t();
                }
                if (i2 >= this.s) {
                    return -1;
                }
                break;
            case PLAY_MODEL_LIST_LOOP:
                i2 = this.p + 1;
                if (e(i2)) {
                    t();
                }
                if (i2 >= this.s) {
                    return 0;
                }
                break;
            case PLAY_MODEL_SINGLE_LOOP:
                return this.p;
            case PLAY_MODEL_RANDOM:
                double random = Math.random();
                double d2 = (double) this.s;
                Double.isNaN(d2);
                int i3 = (int) (random * d2);
                int i4 = this.p;
                return i3;
            default:
                return -1;
        }
        return i2;
    }

    private int w() {
        int i2;
        PlayMode playMode = this.r;
        if (playMode == PlayMode.PLAY_MODEL_SINGLE_LOOP) {
            playMode = PlayMode.PLAY_MODEL_LIST;
        }
        switch (playMode) {
            case PLAY_MODEL_LIST:
                i2 = this.p - 1;
                if (d(i2)) {
                    s();
                }
                if (i2 < 0) {
                    return -1;
                }
                break;
            case PLAY_MODEL_LIST_LOOP:
                i2 = this.p - 1;
                if (d(i2)) {
                    s();
                }
                if (i2 < 0) {
                    i2 = this.s - 1;
                    break;
                }
                break;
            case PLAY_MODEL_SINGLE_LOOP:
                return this.p;
            case PLAY_MODEL_RANDOM:
                double random = Math.random();
                double d2 = (double) this.s;
                Double.isNaN(d2);
                int i3 = (int) (random * d2);
                int i4 = this.p;
                return i3;
            default:
                return -1;
        }
        return i2;
    }

    public void c(int i2) {
        if (this.h != null && this.h.size() >= i2) {
            this.h.remove(i2);
            switch (this.r) {
                case PLAY_MODEL_LIST:
                case PLAY_MODEL_LIST_LOOP:
                    if (i2 <= this.p) {
                        this.p--;
                        break;
                    }
                    break;
            }
            this.s--;
        }
    }

    public enum PlayMode {
        PLAY_MODEL_SINGLE,
        PLAY_MODEL_SINGLE_LOOP,
        PLAY_MODEL_LIST,
        PLAY_MODEL_LIST_LOOP,
        PLAY_MODEL_RANDOM;

        public static PlayMode getIndex(int i) {
            if (i == PLAY_MODEL_SINGLE.ordinal()) {
                return PLAY_MODEL_SINGLE;
            }
            if (i == PLAY_MODEL_SINGLE_LOOP.ordinal()) {
                return PLAY_MODEL_SINGLE_LOOP;
            }
            if (i == PLAY_MODEL_LIST.ordinal()) {
                return PLAY_MODEL_LIST;
            }
            if (i == PLAY_MODEL_LIST_LOOP.ordinal()) {
                return PLAY_MODEL_LIST_LOOP;
            }
            if (i == PLAY_MODEL_RANDOM.ordinal()) {
                return PLAY_MODEL_RANDOM;
            }
            return PLAY_MODEL_LIST;
        }
    }
}
