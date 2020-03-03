package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.exception.AddDownloadException;
import com.ximalaya.ting.android.sdkdownloader.model.XmDownloadAlbum;
import com.ximalaya.ting.android.sdkdownloader.model.XmDownloadAlbumHaveTracks;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IDownloadManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2345a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;

    String a(@DownloadSizePrecision int i);

    List<Track> a(long j, boolean z);

    List<XmDownloadAlbum> a(boolean z);

    Map<Long, DownloadState> a(List<Long> list);

    void a(long j);

    void a(long j, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress);

    void a(long j, boolean z, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress);

    void a(Config config);

    void a(IDoSomethingProgress iDoSomethingProgress);

    void a(String str);

    void a(String str, ITransferFileProgress iTransferFileProgress);

    void a(Collection<Long> collection, IDoSomethingProgress iDoSomethingProgress);

    void a(List<Long> list, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress);

    void a(List<Long> list, boolean z, IDoSomethingProgress<AddDownloadException> iDoSomethingProgress);

    void a(Map<Long, Integer> map, IDoSomethingProgress iDoSomethingProgress);

    Track b(long j, boolean z);

    List<Track> b(boolean z);

    void b(long j);

    void b(long j, IDoSomethingProgress iDoSomethingProgress);

    void b(IDoSomethingProgress iDoSomethingProgress);

    void b(List<Long> list, IDoSomethingProgress iDoSomethingProgress);

    int c(boolean z);

    void c(long j);

    void c(long j, IDoSomethingProgress iDoSomethingProgress);

    void c(IDoSomethingProgress iDoSomethingProgress);

    void c(List<Long> list, IDoSomethingProgress iDoSomethingProgress);

    DownloadState d(long j);

    List<XmDownloadAlbumHaveTracks> d(boolean z);

    void d(long j, IDoSomethingProgress iDoSomethingProgress);

    void d(IDoSomethingProgress iDoSomethingProgress);

    void d(List<Long> list, IDoSomethingProgress iDoSomethingProgress);

    Map<Long, DownloadState> e(long j);

    void e(long j, IDoSomethingProgress iDoSomethingProgress);

    void e(IDoSomethingProgress iDoSomethingProgress);

    Map<Long, DownloadState> f();

    void f(long j);

    boolean g();

    boolean g(long j);

    void h();
}
