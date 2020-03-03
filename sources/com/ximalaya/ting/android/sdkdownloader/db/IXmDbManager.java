package com.ximalaya.ting.android.sdkdownloader.db;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IXmDbManager {
    Track a(long j);

    List<Track> a();

    List<Track> a(Set<Long> set);

    void a(DownloadState downloadState);

    void a(DownloadState downloadState, DownloadState downloadState2);

    void a(DownloadState downloadState, DownloadState downloadState2, List<Long> list);

    boolean a(long j, String str);

    boolean a(long j, boolean z);

    boolean a(Track track);

    boolean a(Collection<Track> collection);

    boolean a(List<Long> list, boolean z);

    boolean a(Map<Long, String> map);

    void b();

    void b(Track track);

    void b(Map<Long, Integer> map);
}
