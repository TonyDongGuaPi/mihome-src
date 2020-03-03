package com.ximalaya.ting.android.sdkdownloader;

import android.app.Application;
import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.widget.Toast;
import com.drew.metadata.iptc.IptcDirectory;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum;
import com.ximalaya.ting.android.opensdk.model.track.BatchTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.IXmCommonBusinessHandle;
import com.ximalaya.ting.android.player.MD5;
import com.ximalaya.ting.android.player.XMediaPlayerConstants;
import com.ximalaya.ting.android.sdkdownloader.db.IXmDbManager;
import com.ximalaya.ting.android.sdkdownloader.db.XmDbManagerImpl;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DoSomethingProgressWrapper;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.IDoSomethingProgress;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.IDownloadManager;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.ITransferFileProgress;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.IXmDownloadTrackCallBack;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.TransferFileProgressWrapper;
import com.ximalaya.ting.android.sdkdownloader.exception.AddDownloadException;
import com.ximalaya.ting.android.sdkdownloader.exception.BaseRuntimeException;
import com.ximalaya.ting.android.sdkdownloader.exception.DbException;
import com.ximalaya.ting.android.sdkdownloader.exception.TransferSavePathException;
import com.ximalaya.ting.android.sdkdownloader.http.DownloadTask;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.http.app.RequestTracker;
import com.ximalaya.ting.android.sdkdownloader.http.loader.FileLoader;
import com.ximalaya.ting.android.sdkdownloader.model.XmDownloadAlbum;
import com.ximalaya.ting.android.sdkdownloader.model.XmDownloadAlbumHaveTracks;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import com.ximalaya.ting.android.sdkdownloader.task.PriorityExecutor;
import com.ximalaya.ting.android.sdkdownloader.task.TaskController;
import com.ximalaya.ting.android.sdkdownloader.task.TaskControllerImpl;
import com.ximalaya.ting.android.sdkdownloader.util.FileUtil;
import com.ximalaya.ting.android.sdkdownloader.util.IOUtil;
import com.ximalaya.ting.android.sdkdownloader.util.XmUtils;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import miuipub.reflect.Field;

public final class XmDownloadManager implements IXmCommonBusinessHandle, IDownloadManager {
    private static final String f = "XmDownloadManager";
    /* access modifiers changed from: private */
    public static volatile XmDownloadManager g;
    /* access modifiers changed from: private */
    public final Application h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public final IXmDbManager j;
    private final TaskController k;
    /* access modifiers changed from: private */
    public final PriorityExecutor l;
    /* access modifiers changed from: private */
    public final long m;
    /* access modifiers changed from: private */
    public final int n;
    /* access modifiers changed from: private */
    public RequestTracker o;
    /* access modifiers changed from: private */
    public int p;
    /* access modifiers changed from: private */
    public Config q;
    /* access modifiers changed from: private */
    public final Map<Long, Track> r;
    /* access modifiers changed from: private */
    public final Map<Long, Track> s;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<Long, DownloadCallback> t;
    /* access modifiers changed from: private */
    public IDoSomethingProgress u;
    private List<Integer> v;
    private Map<Integer, Set<DownloadCallBackViewHolder>> w;
    /* access modifiers changed from: private */
    public List<IXmDownloadTrackCallBack> x;

    public void a() {
    }

    public void b(Track track) {
    }

    private XmDownloadManager(Builder builder) {
        this.r = Collections.synchronizedMap(new LinkedHashMap());
        this.s = Collections.synchronizedMap(new LinkedHashMap());
        this.t = new ConcurrentHashMap<>(5);
        this.v = new ArrayList();
        this.w = new ConcurrentHashMap();
        this.x = new CopyOnWriteArrayList();
        this.h = builder.b;
        this.i = builder.f2331a;
        this.l = new PriorityExecutor(builder.c, builder.j);
        this.k = TaskControllerImpl.a();
        this.j = new XmDbManagerImpl(this.h);
        this.m = builder.d;
        this.n = builder.g;
        this.o = builder.h;
        this.p = builder.i;
        this.q = new Config();
        this.q.k = builder.f;
        this.q.j = builder.e;
        XmPlayerManager.a((Context) this.h).a((IXmCommonBusinessHandle) this);
        List<Track> a2 = this.j.a();
        if (a2 != null) {
            for (Track next : a2) {
                if (next.at() == DownloadState.FINISHED.value()) {
                    this.s.put(Long.valueOf(next.a()), next);
                } else if (next.at() < DownloadState.FINISHED.value()) {
                    next.z(DownloadState.STOPPED.value());
                    this.r.put(Long.valueOf(next.a()), next);
                } else {
                    this.r.put(Long.valueOf(next.a()), next);
                }
            }
        }
    }

    public static XmDownloadManager b() {
        return g;
    }

    public Application c() {
        return this.h;
    }

    public IXmDbManager d() {
        return this.j;
    }

    public TaskController e() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public void c(Track track) throws DbException {
        this.j.b(track);
    }

    private String g(Track track) {
        if (this.i.endsWith(File.separator)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.i);
            sb.append(track.a());
            sb.append(MD5.a(track.ak()));
            sb.append(track.w() ? XMediaPlayerConstants.u : "");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.i);
        sb2.append(File.separator);
        sb2.append(track.a());
        sb2.append(MD5.a(track.ak()));
        sb2.append(track.w() ? XMediaPlayerConstants.u : "");
        return sb2.toString();
    }

    public void a(long j2, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress) {
        a(j2, true, iDoSomethingProgress);
    }

    public void a(final long j2, boolean z, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress) {
        a((List<Long>) new ArrayList<Long>() {
            {
                add(Long.valueOf(j2));
            }
        }, z, iDoSomethingProgress);
    }

    public void a(List<Long> list, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress) {
        a(list, true, iDoSomethingProgress);
    }

    public void a(List<Long> list, boolean z, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        if (iDoSomethingProgress != null) {
            doSomethingProgressWrapper.a();
        } else if (this.u != null) {
            this.u.a();
        }
        if (list == null || list.isEmpty()) {
            a(AddDownloadException.CODE_NULL, "参数不能为空", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
        } else if (((long) list.size()) > this.m) {
            a(AddDownloadException.CODE_MAX_OVER, "批量下载最大不能超过50条", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("ids", XmUtils.a((Collection) list));
            final List<Long> list2 = list;
            final boolean z2 = z;
            final IDoSomethingProgress<AddDownloadException> iDoSomethingProgress2 = iDoSomethingProgress;
            CommonRequest.m(hashMap, new IDataCallBack<BatchTrackList>() {
                public void a(BatchTrackList batchTrackList) {
                    XmDownloadManager.this.a(batchTrackList, doSomethingProgressWrapper, list2, z2, iDoSomethingProgress2);
                }

                public void a(int i, String str) {
                    XmDownloadManager.this.a(i, str, (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
                }
            });
        }
    }

    public void b(List<Long> list, boolean z, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        if (iDoSomethingProgress != null) {
            doSomethingProgressWrapper.a();
        } else if (this.u != null) {
            this.u.a();
        }
        if (list == null || list.isEmpty()) {
            a(AddDownloadException.CODE_NULL, "参数不能为空", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
        } else if (((long) list.size()) > this.m) {
            a(AddDownloadException.CODE_MAX_OVER, "批量下载最大不能超过50条", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("ids", XmUtils.a((Collection) list));
            final List<Long> list2 = list;
            final boolean z2 = z;
            final IDoSomethingProgress<AddDownloadException> iDoSomethingProgress2 = iDoSomethingProgress;
            CommonRequest.aJ(hashMap, new IDataCallBack<BatchTrackList>() {
                public void a(BatchTrackList batchTrackList) {
                    XmDownloadManager.this.a(batchTrackList, doSomethingProgressWrapper, list2, z2, iDoSomethingProgress2);
                }

                public void a(int i, String str) {
                    XmDownloadManager.this.a(i, str, (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(BatchTrackList batchTrackList, DoSomethingProgressWrapper doSomethingProgressWrapper, List<Long> list, boolean z, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress) {
        if (batchTrackList == null || batchTrackList.a() == null || batchTrackList.a().isEmpty()) {
            a(AddDownloadException.CODE_NOT_FIND_TRACK, "不能找到相应的声音", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
            return;
        }
        final ArrayList<Track> arrayList = new ArrayList<>();
        for (Track next : batchTrackList.a()) {
            if (next.aL()) {
                arrayList.add(next);
            }
        }
        if (list.isEmpty()) {
            a(AddDownloadException.CODE_NOT_FIND_TRACK, "不能找到相应的声音", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
            return;
        }
        for (Track track : arrayList) {
            if (track.w() && !track.D() && !track.aP() && !track.B()) {
                a(AddDownloadException.CODE_NO_PAY_SOUND, "下载的付费音频中有没有支付", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
                return;
            }
        }
        if (this.r.size() + arrayList.size() > 500) {
            a(AddDownloadException.CODE_MAX_DOWNLOADING_COUNT, "同时下载的音频个数不能超过500", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper);
            return;
        }
        final DoSomethingProgressWrapper doSomethingProgressWrapper2 = doSomethingProgressWrapper;
        final boolean z2 = z;
        final IDoSomethingProgress<AddDownloadException> iDoSomethingProgress2 = iDoSomethingProgress;
        e().c(new Runnable() {
            public void run() {
                long j = 0;
                for (Track al : arrayList) {
                    j += al.al();
                }
                if (!FileUtil.a(j, XmDownloadManager.this.i)) {
                    XmDownloadManager.this.a(AddDownloadException.CODE_DISK_OVER, "磁盘已满", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper2);
                    XmDownloadManager.this.a((IDoSomethingProgress) null);
                } else if (XmDownloadManager.this.m == Long.MAX_VALUE || XmDownloadManager.this.i() + j <= XmDownloadManager.this.m) {
                    XmDownloadManager.this.a((Collection<Track>) arrayList, z2);
                    XmDownloadManager.this.e().b((Runnable) new Runnable() {
                        public void run() {
                            if (iDoSomethingProgress2 != null) {
                                doSomethingProgressWrapper2.b();
                            } else if (XmDownloadManager.this.u != null) {
                                XmDownloadManager.this.u.b();
                            }
                        }
                    });
                } else {
                    XmDownloadManager.this.a(AddDownloadException.CODE_MAX_SPACE_OVER, "下载的音频所占空间已经超过了最大值", (DoSomethingProgressWrapper<AddDownloadException>) doSomethingProgressWrapper2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final int i2, final String str, final DoSomethingProgressWrapper<AddDownloadException> doSomethingProgressWrapper) {
        e().a((Runnable) new Runnable() {
            public void run() {
                if (doSomethingProgressWrapper != null) {
                    doSomethingProgressWrapper.a(new AddDownloadException(i2, str));
                } else if (XmDownloadManager.this.u != null) {
                    XmDownloadManager.this.u.a(new AddDownloadException(i2, str));
                } else {
                    XmDownloadManager.this.d(str);
                }
            }
        });
    }

    public void a(final long j2) {
        a((Collection<Long>) new ArrayList<Long>() {
            {
                add(Long.valueOf(j2));
            }
        }, (IDoSomethingProgress) null);
    }

    public void a(final Collection<Long> collection, IDoSomethingProgress iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        doSomethingProgressWrapper.a();
        if (collection == null || collection.isEmpty()) {
            doSomethingProgressWrapper.a(new BaseRuntimeException("参数不能为null"));
        } else {
            e().c(new Runnable() {
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    for (Long l : collection) {
                        DownloadCallback downloadCallback = (DownloadCallback) XmDownloadManager.this.t.get(l);
                        if (downloadCallback != null) {
                            if (downloadCallback.j()) {
                                downloadCallback.d();
                            } else {
                                downloadCallback.i();
                                downloadCallback.e();
                                arrayList.add(l);
                                ((Track) XmDownloadManager.this.r.get(l)).z(DownloadState.STOPPED.value());
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        XmDownloadManager.this.d().a(DownloadState.WAITING, DownloadState.STOPPED, arrayList);
                    }
                    doSomethingProgressWrapper.b();
                }
            });
        }
    }

    public void b(long j2, IDoSomethingProgress iDoSomethingProgress) {
        a((Collection<Long>) a(j2, this.r), iDoSomethingProgress);
    }

    public void a(IDoSomethingProgress iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        doSomethingProgressWrapper.a();
        e().d(new Runnable() {
            public void run() {
                XmDownloadManager.this.l.b();
                for (DownloadCallback downloadCallback : XmDownloadManager.this.t.values()) {
                    if (downloadCallback.j()) {
                        downloadCallback.d();
                    } else {
                        downloadCallback.e();
                    }
                }
                Collection<Track> values = XmDownloadManager.this.r.values();
                int value = DownloadState.STOPPED.value();
                synchronized (XmDownloadManager.this.r) {
                    for (Track z : values) {
                        z.z(value);
                    }
                }
                XmDownloadManager.this.d().a(DownloadState.WAITING, DownloadState.STOPPED);
                doSomethingProgressWrapper.b();
            }
        });
    }

    public void b(final long j2) {
        b((List<Long>) new ArrayList<Long>() {
            {
                add(Long.valueOf(j2));
            }
        }, (IDoSomethingProgress) null);
    }

    public void b(List<Long> list, IDoSomethingProgress iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        doSomethingProgressWrapper.a();
        if (list == null || list.isEmpty()) {
            doSomethingProgressWrapper.a(new BaseRuntimeException("参数不能为null"));
        } else if (this.r != null && !this.r.isEmpty()) {
            final ArrayList arrayList = new ArrayList();
            for (Long l2 : list) {
                Track track = this.r.get(l2);
                if (track != null) {
                    arrayList.add(track);
                }
            }
            e().d(new Runnable() {
                public void run() {
                    XmDownloadManager.this.a((Collection<Track>) arrayList, true, false);
                    doSomethingProgressWrapper.b();
                }
            });
        }
    }

    public void c(long j2, IDoSomethingProgress iDoSomethingProgress) {
        b(a(j2, this.r), iDoSomethingProgress);
    }

    public void b(IDoSomethingProgress iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        doSomethingProgressWrapper.a();
        e().c(new Runnable() {
            public void run() {
                XmDownloadManager.this.a((Collection<Track>) XmDownloadManager.this.r.values(), true, false);
                doSomethingProgressWrapper.b();
            }
        });
    }

    public void c(final long j2) {
        c((List<Long>) new ArrayList<Long>() {
            {
                add(Long.valueOf(j2));
            }
        }, (IDoSomethingProgress) null);
    }

    public void c(final List<Long> list, IDoSomethingProgress iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        doSomethingProgressWrapper.a();
        if (list == null || list.isEmpty()) {
            doSomethingProgressWrapper.a(new BaseRuntimeException("参数不能为null"));
        } else {
            e().d(new Runnable() {
                public void run() {
                    boolean z = true;
                    if (list.size() != 1) {
                        z = false;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Long l : list) {
                        DownloadCallback downloadCallback = (DownloadCallback) XmDownloadManager.this.t.get(l);
                        if (downloadCallback == null) {
                            Track track = (Track) XmDownloadManager.this.r.get(l);
                            if (track != null) {
                                IOUtil.a(XmUtils.a(track.aq()));
                                if (z) {
                                    XmDownloadManager.this.a(track, new Callback.RemovedException("removed by user"));
                                } else {
                                    XmDownloadManager.this.r.remove(l);
                                    arrayList.add(l);
                                }
                            }
                        } else if (downloadCallback.j()) {
                            downloadCallback.h();
                        } else {
                            downloadCallback.i();
                            downloadCallback.e();
                            arrayList.add(l);
                            XmDownloadManager.this.r.remove(l);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        XmDownloadManager.this.d().a((List<Long>) arrayList, false);
                    }
                    doSomethingProgressWrapper.b();
                }
            });
        }
    }

    public void d(long j2, IDoSomethingProgress iDoSomethingProgress) {
        c(a(j2, this.r), iDoSomethingProgress);
    }

    public void c(IDoSomethingProgress iDoSomethingProgress) {
        c(a(this.r), iDoSomethingProgress);
    }

    public DownloadState d(long j2) {
        Track track = this.r.get(Long.valueOf(j2));
        if (track != null) {
            return DownloadState.valueOf(track.at());
        }
        if (this.s.containsKey(Long.valueOf(j2))) {
            return DownloadState.FINISHED;
        }
        return DownloadState.NOADD;
    }

    public Map<Long, DownloadState> a(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return XmUtils.c();
        }
        HashMap hashMap = new HashMap();
        if (!this.r.isEmpty() || !this.s.isEmpty()) {
            for (Long next : list) {
                Track track = this.r.get(next);
                if (track != null) {
                    hashMap.put(Long.valueOf(track.a()), DownloadState.valueOf(track.at()));
                } else if (this.s.containsKey(next)) {
                    hashMap.put(next, DownloadState.FINISHED);
                } else {
                    hashMap.put(next, DownloadState.NOADD);
                }
            }
            return hashMap;
        }
        for (Long put : list) {
            hashMap.put(put, DownloadState.NOADD);
        }
        return hashMap;
    }

    public Map<Long, DownloadState> e(long j2) {
        List<Long> a2 = a(j2, this.r);
        if (a2 == null) {
            a2 = new ArrayList<>();
        }
        a2.addAll(a(j2, this.s));
        return a(a2);
    }

    public Map<Long, DownloadState> f() {
        List<Long> a2 = a(this.r);
        if (a2 != null) {
            a2.addAll(a(this.s));
        }
        return a(a2);
    }

    public List<XmDownloadAlbum> a(boolean z) {
        Map<Long, Track> map = z ? this.s : this.r;
        if (map.isEmpty()) {
            return XmUtils.b();
        }
        Collection<Track> values = map.values();
        int value = DownloadState.FINISHED.value();
        HashMap hashMap = new HashMap();
        synchronized ((z ? this.s : this.r)) {
            for (Track next : values) {
                SubordinatedAlbum ao = next.ao();
                if (z) {
                    if (next.at() == value) {
                    }
                } else if (next.at() != value) {
                    if (ao == null) {
                    }
                }
                if (hashMap.containsKey(Long.valueOf(ao.d()))) {
                    XmDownloadAlbum xmDownloadAlbum = (XmDownloadAlbum) hashMap.get(Long.valueOf(ao.d()));
                    xmDownloadAlbum.a(xmDownloadAlbum.f() + 1);
                    xmDownloadAlbum.b(xmDownloadAlbum.g() + next.al());
                } else {
                    XmDownloadAlbum a2 = XmDownloadAlbum.a(next);
                    if (a2 != null) {
                        hashMap.put(Long.valueOf(ao.d()), a2);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hashMap.values());
        return arrayList;
    }

    public List<Track> a(long j2, boolean z) {
        List<Long> a2 = a(j2, z ? this.s : this.r);
        if (a2 == null || a2.isEmpty()) {
            return XmUtils.b();
        }
        return XmUtils.a(a2, z ? this.s : this.r);
    }

    public List<Track> b(boolean z) {
        return new ArrayList((z ? this.s : this.r).values());
    }

    public int c(boolean z) {
        return (z ? this.s : this.r).size();
    }

    public Track b(long j2, boolean z) {
        return (z ? this.s : this.r).get(Long.valueOf(j2));
    }

    public void f(final long j2) {
        d((List<Long>) new ArrayList<Long>() {
            {
                add(Long.valueOf(j2));
            }
        }, (IDoSomethingProgress) null);
    }

    public void d(final List<Long> list, IDoSomethingProgress iDoSomethingProgress) {
        final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
        doSomethingProgressWrapper.a();
        if (list == null || list.isEmpty()) {
            doSomethingProgressWrapper.a(new BaseRuntimeException("参数不能为null"));
        } else {
            e().d(new Runnable() {
                public void run() {
                    synchronized (XmDownloadManager.class) {
                        XmDownloadManager.this.j.a((List<Long>) list, true);
                    }
                    for (Long remove : list) {
                        Track track = (Track) XmDownloadManager.this.s.remove(remove);
                        if (track != null) {
                            IOUtil.a(track.aq());
                        }
                    }
                    XmDownloadManager.this.e().a((Runnable) new Runnable() {
                        public void run() {
                            XmDownloadManager.this.j();
                            doSomethingProgressWrapper.b();
                        }
                    });
                }
            });
        }
    }

    public void e(long j2, IDoSomethingProgress iDoSomethingProgress) {
        d(a(j2, this.s), iDoSomethingProgress);
    }

    public void d(IDoSomethingProgress iDoSomethingProgress) {
        d(a(this.s), iDoSomethingProgress);
    }

    public String a(@IDownloadManager.DownloadSizePrecision int i2) {
        long i3 = i();
        String str = i3 + "";
        switch (i2) {
            case 1:
                return ((((((float) i3) * 1.0f) / 1024.0f) / 1024.0f) / 1024.0f) + "G";
            case 2:
                return ((((float) i3) * 1.0f) / 1024.0f) + "KB";
            case 3:
                return (((((float) i3) * 1.0f) / 1024.0f) / 1024.0f) + "MB";
            case 4:
                return i3 + Field.b;
            case 5:
                return XmUtils.a((double) i3);
            default:
                return str;
        }
    }

    public boolean g() {
        if (this.r.isEmpty()) {
            return false;
        }
        Collection<Track> values = this.r.values();
        synchronized (this.r) {
            for (Track at : values) {
                if (at.at() == DownloadState.STARTED.value()) {
                    return true;
                }
            }
            return false;
        }
    }

    public void h() {
        a((IDoSomethingProgress) new IDoSomethingProgress() {
            public void a() {
            }

            public void a(BaseRuntimeException baseRuntimeException) {
            }

            public void b() {
                XmDownloadManager.this.j.b();
                XmDownloadManager.this.t.clear();
                XmDownloadManager.this.r.clear();
                XmDownloadManager.this.s.clear();
                XmDownloadManager unused = XmDownloadManager.g = null;
            }
        });
        XmPlayerManager.a((Context) this.h).a((IXmCommonBusinessHandle) null);
        this.v.clear();
        this.w.clear();
        this.x.clear();
    }

    public void a(Config config) {
        this.q = config;
    }

    public void a(@NonNull String str) {
        c(str);
        this.i = str;
    }

    /* access modifiers changed from: private */
    public static void c(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("savePath 不能为null");
        } else if (!FileUtil.b(str).canWrite()) {
            throw new IllegalStateException("savePath 不可写!");
        }
    }

    public void a(@NonNull final String str, @NonNull ITransferFileProgress iTransferFileProgress) {
        if (iTransferFileProgress == null) {
            d("transferProgress 不能为null");
            return;
        }
        final TransferFileProgressWrapper transferFileProgressWrapper = new TransferFileProgressWrapper(iTransferFileProgress);
        transferFileProgressWrapper.a();
        try {
            c(str);
            a((IDoSomethingProgress) new IDoSomethingProgress() {
                public void a() {
                }

                public void b() {
                    XmDownloadManager.this.e().c(new Runnable() {
                        public void run() {
                            for (Track track : XmDownloadManager.this.r.values()) {
                                String a2 = XmUtils.a(track.aq());
                                String a3 = XmDownloadManager.b(a2, str, track);
                                if (new File(a2).exists()) {
                                    if (!TextUtils.isEmpty(a3) && !a3.equals(track.aq())) {
                                        try {
                                            if (!FileUtil.a(a2, a3)) {
                                                throw new TransferSavePathException(TransferSavePathException.CODE_FILE_TRANSFER_ERROR, "文件转移失败", track);
                                            } else if (XmDownloadManager.this.d().a(track.a(), a3)) {
                                                IOUtil.a(a2);
                                                track.u(a3);
                                            } else {
                                                throw new TransferSavePathException(TransferSavePathException.CODE_DB_ERROR, "数据库保存失败", track);
                                            }
                                        } catch (TransferSavePathException e) {
                                            IOUtil.a(a3);
                                            transferFileProgressWrapper.a(e);
                                        } catch (Throwable th) {
                                            IOUtil.a(a3);
                                            TransferFileProgressWrapper transferFileProgressWrapper = transferFileProgressWrapper;
                                            int i = TransferSavePathException.CODE_FILE_TRANSFER_ERROR;
                                            transferFileProgressWrapper.a(new TransferSavePathException(i, "文件转移失败:" + th.getLocalizedMessage(), track));
                                        }
                                    }
                                } else if (XmDownloadManager.this.d().a(track.a(), a3)) {
                                    track.u(a3);
                                } else {
                                    transferFileProgressWrapper.a(new TransferSavePathException(TransferSavePathException.CODE_FILE_TRANSFER_ERROR, "数据库保存失败", track));
                                }
                            }
                            int size = XmDownloadManager.this.s.size();
                            int i2 = 0;
                            long j = 0;
                            for (Track track2 : XmDownloadManager.this.s.values()) {
                                String a4 = XmDownloadManager.b(track2.aq(), str, track2);
                                if (!TextUtils.isEmpty(a4) && !a4.equals(track2.aq())) {
                                    try {
                                        if (!FileUtil.a(track2.aq(), a4)) {
                                            throw new TransferSavePathException(TransferSavePathException.CODE_FILE_TRANSFER_ERROR, "文件转移失败", track2);
                                        } else if (XmDownloadManager.this.d().a(track2.a(), a4)) {
                                            IOUtil.a(track2.aq());
                                            track2.u(a4);
                                            i2++;
                                            if (System.currentTimeMillis() - j > 300 || i2 == size) {
                                                long currentTimeMillis = System.currentTimeMillis();
                                                try {
                                                    transferFileProgressWrapper.a(i2, size, track2);
                                                    j = currentTimeMillis;
                                                } catch (TransferSavePathException e2) {
                                                    e = e2;
                                                    j = currentTimeMillis;
                                                    IOUtil.a(a4);
                                                    transferFileProgressWrapper.a(e);
                                                } catch (Throwable th2) {
                                                    long j2 = currentTimeMillis;
                                                    th = th2;
                                                    j = j2;
                                                    IOUtil.a(a4);
                                                    TransferFileProgressWrapper transferFileProgressWrapper2 = transferFileProgressWrapper;
                                                    int i3 = TransferSavePathException.CODE_FILE_TRANSFER_ERROR;
                                                    transferFileProgressWrapper2.a(new TransferSavePathException(i3, "文件转移失败:" + th.getLocalizedMessage(), track2));
                                                }
                                            }
                                        } else {
                                            throw new TransferSavePathException(TransferSavePathException.CODE_DB_ERROR, "数据库保存失败", track2);
                                        }
                                    } catch (TransferSavePathException e3) {
                                        e = e3;
                                        IOUtil.a(a4);
                                        transferFileProgressWrapper.a(e);
                                    } catch (Throwable th3) {
                                        th = th3;
                                        IOUtil.a(a4);
                                        TransferFileProgressWrapper transferFileProgressWrapper22 = transferFileProgressWrapper;
                                        int i32 = TransferSavePathException.CODE_FILE_TRANSFER_ERROR;
                                        transferFileProgressWrapper22.a(new TransferSavePathException(i32, "文件转移失败:" + th.getLocalizedMessage(), track2));
                                    }
                                }
                            }
                            XmDownloadManager.this.a(str);
                            transferFileProgressWrapper.b();
                        }
                    });
                }

                public void a(BaseRuntimeException baseRuntimeException) {
                    transferFileProgressWrapper.a(new TransferSavePathException(IptcDirectory.Z, baseRuntimeException.getMessage()));
                }
            });
        } catch (Exception e) {
            transferFileProgressWrapper.a(new TransferSavePathException(TransferSavePathException.CODE_SAVE_PATH_NO_SAVE, e.getLocalizedMessage()));
        }
    }

    public void a(@NonNull final Map<Long, Integer> map, IDoSomethingProgress iDoSomethingProgress) {
        if (map != null && !map.isEmpty()) {
            final DoSomethingProgressWrapper doSomethingProgressWrapper = new DoSomethingProgressWrapper(iDoSomethingProgress);
            doSomethingProgressWrapper.a();
            e().d(new Runnable() {
                public void run() {
                    for (Track track : XmDownloadManager.this.s.values()) {
                        track.n(((Integer) map.get(Long.valueOf(track.a()))).intValue());
                    }
                    XmDownloadManager.this.d().b((Map<Long, Integer>) map);
                    doSomethingProgressWrapper.b();
                }
            });
        }
    }

    public List<XmDownloadAlbumHaveTracks> d(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (XmDownloadAlbum next : a(z)) {
            arrayList.add(new XmDownloadAlbumHaveTracks(next, a(next.a(), z)));
        }
        return arrayList;
    }

    public boolean g(long j2) {
        List<Long> a2 = a(this.r);
        if (a2 == null || a2.isEmpty()) {
            return false;
        }
        for (Long longValue : a2) {
            if (this.r.get(Long.valueOf(longValue.longValue())).at() == DownloadState.STARTED.value()) {
                return true;
            }
        }
        return false;
    }

    public static Builder a(@NonNull Application application) throws IllegalStateException {
        if (application != null) {
            return new Builder(application);
        }
        throw new IllegalArgumentException("application 不能为null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r4 = r3.s.get(java.lang.Long.valueOf(r4.a()));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(com.ximalaya.ting.android.opensdk.model.track.Track r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0019
            java.util.Map<java.lang.Long, com.ximalaya.ting.android.opensdk.model.track.Track> r0 = r3.s
            long r1 = r4.a()
            java.lang.Long r4 = java.lang.Long.valueOf(r1)
            java.lang.Object r4 = r0.get(r4)
            com.ximalaya.ting.android.opensdk.model.track.Track r4 = (com.ximalaya.ting.android.opensdk.model.track.Track) r4
            if (r4 == 0) goto L_0x0019
            java.lang.String r4 = r4.aq()
            return r4
        L_0x0019:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.sdkdownloader.XmDownloadManager.a(com.ximalaya.ting.android.opensdk.model.track.Track):java.lang.String");
    }

    public static final class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f2331a;
        /* access modifiers changed from: private */
        public Application b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public RequestTracker h;
        /* access modifiers changed from: private */
        public int i;
        /* access modifiers changed from: private */
        public boolean j;

        private Builder(@NonNull Application application) {
            this.c = 1;
            this.d = Long.MAX_VALUE;
            this.e = 30000;
            this.f = 30000;
            this.g = 2;
            this.i = 800;
            this.j = false;
            this.b = application;
            File externalFilesDir = application.getExternalFilesDir("xmdownload");
            if (externalFilesDir == null) {
                this.f2331a = new File(application.getFilesDir(), "xmdownload").getAbsolutePath();
            } else {
                this.f2331a = externalFilesDir.getAbsolutePath();
            }
        }

        public Builder a(String str) {
            XmDownloadManager.c(str);
            this.f2331a = str;
            return this;
        }

        public Builder a(@IntRange(from = 1, to = 3) int i2) {
            this.c = i2;
            return this;
        }

        public Builder a(long j2) {
            this.d = j2;
            return this;
        }

        public Builder b(int i2) {
            this.e = i2;
            return this;
        }

        public Builder c(int i2) {
            this.f = i2;
            return this;
        }

        public Builder d(int i2) {
            this.g = i2;
            return this;
        }

        public Builder a(RequestTracker requestTracker) {
            this.h = requestTracker;
            return this;
        }

        public Builder e(int i2) {
            this.i = i2;
            return this;
        }

        public Builder a(boolean z) {
            this.j = z;
            return this;
        }

        public XmDownloadManager a() {
            if (XmDownloadManager.g == null) {
                synchronized (XmDownloadManager.class) {
                    if (XmDownloadManager.g == null) {
                        XmDownloadManager unused = XmDownloadManager.g = new XmDownloadManager(this);
                    }
                }
            }
            return XmDownloadManager.g;
        }
    }

    private synchronized void a(final Track track, boolean z) throws DbException {
        a((Collection<Track>) new ArrayList<Track>() {
            {
                add(track);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ad, code lost:
        return;
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.util.Collection<com.ximalaya.ting.android.opensdk.model.track.Track> r7, boolean r8, boolean r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r9 == 0) goto L_0x00ae
            if (r7 == 0) goto L_0x00ac
            boolean r0 = r7.isEmpty()     // Catch:{ all -> 0x0117 }
            if (r0 == 0) goto L_0x000d
            goto L_0x00ac
        L_0x000d:
            r0 = 0
            boolean r0 = r7.remove(r0)     // Catch:{ all -> 0x0117 }
            if (r0 == 0) goto L_0x0015
            goto L_0x000d
        L_0x0015:
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x0117 }
            r0.<init>()     // Catch:{ all -> 0x0117 }
            java.util.Map<java.lang.Long, com.ximalaya.ting.android.opensdk.model.track.Track> r1 = r6.r     // Catch:{ all -> 0x0117 }
            monitor-enter(r1)     // Catch:{ all -> 0x0117 }
            java.util.Iterator r2 = r7.iterator()     // Catch:{ all -> 0x00a9 }
        L_0x0021:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00a9 }
            if (r3 == 0) goto L_0x0040
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00a9 }
            com.ximalaya.ting.android.opensdk.model.track.Track r3 = (com.ximalaya.ting.android.opensdk.model.track.Track) r3     // Catch:{ all -> 0x00a9 }
            java.lang.String r4 = r6.g((com.ximalaya.ting.android.opensdk.model.track.Track) r3)     // Catch:{ all -> 0x00a9 }
            r3.u((java.lang.String) r4)     // Catch:{ all -> 0x00a9 }
            long r3 = r3.a()     // Catch:{ all -> 0x00a9 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00a9 }
            r0.add(r3)     // Catch:{ all -> 0x00a9 }
            goto L_0x0021
        L_0x0040:
            monitor-exit(r1)     // Catch:{ all -> 0x00a9 }
            com.ximalaya.ting.android.sdkdownloader.db.IXmDbManager r1 = r6.j     // Catch:{ all -> 0x0117 }
            java.util.List r0 = r1.a((java.util.Set<java.lang.Long>) r0)     // Catch:{ all -> 0x0117 }
            if (r0 == 0) goto L_0x00a3
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0117 }
            r1.<init>()     // Catch:{ all -> 0x0117 }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x0117 }
        L_0x0052:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0117 }
            if (r3 == 0) goto L_0x007d
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.opensdk.model.track.Track r3 = (com.ximalaya.ting.android.opensdk.model.track.Track) r3     // Catch:{ all -> 0x0117 }
            int r4 = r3.at()     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState r5 = com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState.FINISHED     // Catch:{ all -> 0x0117 }
            int r5 = r5.value()     // Catch:{ all -> 0x0117 }
            if (r4 != r5) goto L_0x0052
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x0117 }
            java.lang.String r5 = r3.aq()     // Catch:{ all -> 0x0117 }
            r4.<init>(r5)     // Catch:{ all -> 0x0117 }
            boolean r4 = r4.exists()     // Catch:{ all -> 0x0117 }
            if (r4 == 0) goto L_0x0052
            r1.add(r3)     // Catch:{ all -> 0x0117 }
            goto L_0x0052
        L_0x007d:
            boolean r1 = r7.removeAll(r1)     // Catch:{ all -> 0x0117 }
            if (r1 == 0) goto L_0x0095
            int r1 = r7.size()     // Catch:{ all -> 0x0117 }
            if (r1 != 0) goto L_0x0090
            java.lang.String r7 = "已下载"
            r6.d((java.lang.String) r7)     // Catch:{ all -> 0x0117 }
            monitor-exit(r6)
            return
        L_0x0090:
            java.lang.String r1 = "部分已下载"
            r6.d((java.lang.String) r1)     // Catch:{ all -> 0x0117 }
        L_0x0095:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0117 }
            r1.<init>(r7)     // Catch:{ all -> 0x0117 }
            r1.removeAll(r0)     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.sdkdownloader.db.IXmDbManager r0 = r6.j     // Catch:{ all -> 0x0117 }
            r0.a((java.util.Collection<com.ximalaya.ting.android.opensdk.model.track.Track>) r1)     // Catch:{ all -> 0x0117 }
            goto L_0x00ae
        L_0x00a3:
            com.ximalaya.ting.android.sdkdownloader.db.IXmDbManager r0 = r6.j     // Catch:{ all -> 0x0117 }
            r0.a((java.util.Collection<com.ximalaya.ting.android.opensdk.model.track.Track>) r7)     // Catch:{ all -> 0x0117 }
            goto L_0x00ae
        L_0x00a9:
            r7 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00a9 }
            throw r7     // Catch:{ all -> 0x0117 }
        L_0x00ac:
            monitor-exit(r6)
            return
        L_0x00ae:
            int r0 = r7.size()     // Catch:{ all -> 0x0117 }
            r1 = 1
            if (r0 != r1) goto L_0x00b6
            goto L_0x00b7
        L_0x00b6:
            r1 = 0
        L_0x00b7:
            if (r1 != 0) goto L_0x00c2
            com.ximalaya.ting.android.sdkdownloader.db.IXmDbManager r0 = r6.d()     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState r2 = com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState.WAITING     // Catch:{ all -> 0x0117 }
            r0.a((com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState) r2)     // Catch:{ all -> 0x0117 }
        L_0x00c2:
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0117 }
        L_0x00c6:
            boolean r0 = r7.hasNext()     // Catch:{ all -> 0x0117 }
            if (r0 == 0) goto L_0x0115
            java.lang.Object r0 = r7.next()     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.opensdk.model.track.Track r0 = (com.ximalaya.ting.android.opensdk.model.track.Track) r0     // Catch:{ all -> 0x0117 }
            if (r8 == 0) goto L_0x0105
            com.ximalaya.ting.android.sdkdownloader.XmDownloadManager$ParamAndCallBack r2 = new com.ximalaya.ting.android.sdkdownloader.XmDownloadManager$ParamAndCallBack     // Catch:{ all -> 0x0117 }
            r2.<init>(r6, r0)     // Catch:{ all -> 0x0117 }
            if (r1 == 0) goto L_0x00e0
            com.ximalaya.ting.android.sdkdownloader.task.Callback$Cancelable r3 = r6.a((com.ximalaya.ting.android.sdkdownloader.XmDownloadManager.ParamAndCallBack) r2)     // Catch:{ all -> 0x0117 }
            goto L_0x00ed
        L_0x00e0:
            com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState r3 = com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState.WAITING     // Catch:{ all -> 0x0117 }
            int r3 = r3.value()     // Catch:{ all -> 0x0117 }
            r0.z((int) r3)     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.sdkdownloader.task.Callback$Cancelable r3 = r6.b((com.ximalaya.ting.android.sdkdownloader.XmDownloadManager.ParamAndCallBack) r2)     // Catch:{ all -> 0x0117 }
        L_0x00ed:
            com.ximalaya.ting.android.sdkdownloader.DownloadCallback r4 = r2.b     // Catch:{ all -> 0x0117 }
            r4.a((com.ximalaya.ting.android.sdkdownloader.task.Callback.Cancelable) r3)     // Catch:{ all -> 0x0117 }
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.ximalaya.ting.android.sdkdownloader.DownloadCallback> r3 = r6.t     // Catch:{ all -> 0x0117 }
            long r4 = r0.a()     // Catch:{ all -> 0x0117 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0117 }
            com.ximalaya.ting.android.sdkdownloader.DownloadCallback r2 = r2.b     // Catch:{ all -> 0x0117 }
            r3.put(r4, r2)     // Catch:{ all -> 0x0117 }
        L_0x0105:
            if (r9 == 0) goto L_0x00c6
            java.util.Map<java.lang.Long, com.ximalaya.ting.android.opensdk.model.track.Track> r2 = r6.r     // Catch:{ all -> 0x0117 }
            long r3 = r0.a()     // Catch:{ all -> 0x0117 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0117 }
            r2.put(r3, r0)     // Catch:{ all -> 0x0117 }
            goto L_0x00c6
        L_0x0115:
            monitor-exit(r6)
            return
        L_0x0117:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.sdkdownloader.XmDownloadManager.a(java.util.Collection, boolean, boolean):void");
    }

    private static class ParamAndCallBack {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public RequestParams f2332a;
        /* access modifiers changed from: private */
        public DownloadCallback b;

        public ParamAndCallBack(XmDownloadManager xmDownloadManager, Track track) {
            this.b = new DownloadCallback(track);
            this.b.a(xmDownloadManager);
            this.f2332a = new RequestParams(track.ak(), track.w() ? FileLoader.d : FileLoader.c);
            Config j = xmDownloadManager.q;
            if (!j.d) {
                this.f2332a.a((Proxy) null);
            } else if (TextUtils.isEmpty(j.f) || j.g <= 0) {
                this.f2332a.a((Proxy) null);
            } else {
                this.f2332a.a(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(j.f, j.g)));
            }
            this.f2332a.a(j.j);
            this.f2332a.d(j.k);
            this.f2332a.a(true);
            this.f2332a.b(xmDownloadManager.n);
            this.f2332a.a(xmDownloadManager.o);
            this.f2332a.c(xmDownloadManager.p);
            this.f2332a.b(track.aq());
            this.f2332a.a((Executor) xmDownloadManager.l);
            this.f2332a.b(true);
            this.f2332a.a(track.a());
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public synchronized void a(Collection<Track> collection, boolean z) {
        a(collection, z, true);
    }

    /* access modifiers changed from: private */
    public void d(final String str) {
        e().a((Runnable) new Runnable() {
            public void run() {
                Toast.makeText(XmDownloadManager.this.h, str, 0).show();
            }
        });
    }

    private <T> Callback.Cancelable a(ParamAndCallBack paramAndCallBack) {
        return this.k.a(new DownloadTask(paramAndCallBack.f2332a, paramAndCallBack.b, paramAndCallBack.b));
    }

    private <T> Callback.Cancelable b(ParamAndCallBack paramAndCallBack) {
        return this.k.b(new DownloadTask(paramAndCallBack.f2332a, paramAndCallBack.b, paramAndCallBack.b));
    }

    public long i() {
        long j2;
        long ar;
        synchronized (this.s) {
            j2 = 0;
            for (Track next : this.s.values()) {
                if (next.ar() == 0) {
                    j2 += next.al();
                } else {
                    j2 += next.ar();
                }
            }
        }
        synchronized (this.r) {
            for (Track next2 : this.r.values()) {
                if (next2.ar() == 0) {
                    ar = j2 + next2.al();
                } else {
                    ar = j2 + next2.ar();
                }
            }
        }
        return j2;
    }

    private List<Long> a(long j2, Map<Long, Track> map) {
        if (map == null || map.isEmpty()) {
            return XmUtils.b();
        }
        ArrayList arrayList = new ArrayList();
        synchronized (map) {
            for (Map.Entry<Long, Track> value : map.entrySet()) {
                Track track = (Track) value.getValue();
                if (!(track == null || track.ao() == null || track.ao().d() != j2)) {
                    arrayList.add(Long.valueOf(track.a()));
                }
            }
        }
        return arrayList;
    }

    private List<Long> a(Map<Long, Track> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        synchronized (map) {
            arrayList.addAll(map.keySet());
        }
        return arrayList;
    }

    public void e(IDoSomethingProgress iDoSomethingProgress) {
        this.u = iDoSomethingProgress;
    }

    public void a(int i2, @NonNull DownloadCallBackViewHolder downloadCallBackViewHolder) {
        if (i2 != 0) {
            this.v.add(Integer.valueOf(i2));
            Set set = this.w.get(Integer.valueOf(i2));
            if (set == null) {
                set = new CopyOnWriteArraySet();
                this.w.put(Integer.valueOf(i2), set);
            }
            set.add(downloadCallBackViewHolder);
            return;
        }
        throw new IllegalArgumentException("pageCode 不能为0");
    }

    public void b(int i2) {
        if (i2 != 0) {
            this.v.remove(new Integer(i2));
            this.w.remove(Integer.valueOf(i2));
            return;
        }
        throw new IllegalArgumentException("pageCode 不能为0");
    }

    public void c(int i2) {
        if (i2 != 0) {
            int indexOf = this.v.indexOf(new Integer(i2));
            if (indexOf >= 0 && indexOf != this.v.size() - 1) {
                Collections.swap(this.v, indexOf, this.v.size() - 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("pageCode 不能为0");
    }

    public void d(int i2) {
        if (i2 != 0) {
            int indexOf = this.v.indexOf(new Integer(i2));
            if (indexOf == this.v.size() - 1 && this.v.size() > 2) {
                Collections.swap(this.v, indexOf, this.v.size() - 2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("pageCode 不能为0");
    }

    public void a(IXmDownloadTrackCallBack iXmDownloadTrackCallBack) {
        if (!this.x.contains(iXmDownloadTrackCallBack)) {
            this.x.add(iXmDownloadTrackCallBack);
        }
    }

    public void b(IXmDownloadTrackCallBack iXmDownloadTrackCallBack) {
        if (this.x != null) {
            this.x.remove(iXmDownloadTrackCallBack);
        }
    }

    /* access modifiers changed from: protected */
    public void d(Track track) {
        for (DownloadCallBackViewHolder b : l()) {
            b.b(track);
        }
        for (IXmDownloadTrackCallBack b2 : this.x) {
            b2.b(track);
        }
    }

    /* access modifiers changed from: protected */
    public void e(Track track) {
        for (DownloadCallBackViewHolder c : l()) {
            c.c(track);
        }
        for (IXmDownloadTrackCallBack c2 : this.x) {
            c2.c(track);
        }
    }

    /* access modifiers changed from: protected */
    public void f(Track track) {
        Track remove = this.r.remove(Long.valueOf(track.a()));
        if (remove != null) {
            this.s.put(Long.valueOf(remove.a()), remove);
            this.t.remove(Long.valueOf(remove.a()));
        }
        for (DownloadCallBackViewHolder d : l()) {
            d.d(track);
        }
        for (IXmDownloadTrackCallBack d2 : this.x) {
            d2.d(track);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Track track, Throwable th) {
        for (DownloadCallBackViewHolder a2 : l()) {
            a2.a(track, th);
        }
        for (IXmDownloadTrackCallBack a3 : this.x) {
            a3.a(track, th);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Track track, Callback.CancelledException cancelledException) {
        for (DownloadCallBackViewHolder a2 : l()) {
            a2.a(track, cancelledException);
        }
        for (IXmDownloadTrackCallBack a3 : this.x) {
            a3.a(track, cancelledException);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Track track, long j2, long j3) {
        for (DownloadCallBackViewHolder a2 : l()) {
            a2.a(track, j2, j3);
        }
        for (IXmDownloadTrackCallBack a3 : this.x) {
            a3.a(track, j2, j3);
        }
    }

    /* access modifiers changed from: protected */
    public void a(final Track track, Callback.RemovedException removedException) {
        e().d(new Runnable() {
            public void run() {
                Track track = (Track) XmDownloadManager.this.r.remove(Long.valueOf(track.a()));
                if (track != null) {
                    XmDownloadManager.this.t.remove(Long.valueOf(track.a()));
                    XmDownloadManager.this.j.a(track.a(), false);
                }
                XmDownloadManager.this.e().a((Runnable) new Runnable() {
                    public void run() {
                        for (DownloadCallBackViewHolder b : XmDownloadManager.this.l()) {
                            b.b();
                        }
                        for (IXmDownloadTrackCallBack b2 : XmDownloadManager.this.x) {
                            b2.b();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public Set<DownloadCallBackViewHolder> l() {
        if (this.v == null || this.v.size() < 1) {
            return XmUtils.a();
        }
        Set<DownloadCallBackViewHolder> set = this.w.get(this.v.get(this.v.size() - 1));
        return set == null ? XmUtils.a() : set;
    }

    /* access modifiers changed from: protected */
    public void j() {
        Set<DownloadCallBackViewHolder> l2 = l();
        if (l2 != null) {
            for (DownloadCallBackViewHolder b : l2) {
                b.b();
            }
        }
        for (IXmDownloadTrackCallBack b2 : this.x) {
            b2.b();
        }
    }

    /* access modifiers changed from: private */
    public static String b(String str, String str2, Track track) {
        int lastIndexOf;
        if (str == null || (lastIndexOf = str.lastIndexOf(File.separator)) <= 0) {
            return null;
        }
        return str2 + str.substring(lastIndexOf);
    }
}
