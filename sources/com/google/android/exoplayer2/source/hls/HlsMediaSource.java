package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.List;

public final class HlsMediaSource extends BaseMediaSource implements HlsPlaylistTracker.PrimaryPlaylistListener {
    private final boolean allowChunklessPreparation;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Uri manifestUri;
    @Nullable
    private TransferListener mediaTransferListener;
    private final HlsPlaylistTracker playlistTracker;
    @Nullable
    private final Object tag;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.hls");
    }

    public static final class Factory implements AdsMediaSource.MediaSourceFactory {
        private boolean allowChunklessPreparation;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private HlsExtractorFactory extractorFactory;
        private final HlsDataSourceFactory hlsDataSourceFactory;
        private boolean isCreateCalled;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private HlsPlaylistParserFactory playlistParserFactory;
        private HlsPlaylistTracker.Factory playlistTrackerFactory;
        @Nullable
        private Object tag;

        public Factory(DataSource.Factory factory) {
            this((HlsDataSourceFactory) new DefaultHlsDataSourceFactory(factory));
        }

        public Factory(HlsDataSourceFactory hlsDataSourceFactory2) {
            this.hlsDataSourceFactory = (HlsDataSourceFactory) Assertions.checkNotNull(hlsDataSourceFactory2);
            this.playlistParserFactory = new DefaultHlsPlaylistParserFactory();
            this.playlistTrackerFactory = DefaultHlsPlaylistTracker.FACTORY;
            this.extractorFactory = HlsExtractorFactory.DEFAULT;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory setTag(Object obj) {
            Assertions.checkState(!this.isCreateCalled);
            this.tag = obj;
            return this;
        }

        public Factory setExtractorFactory(HlsExtractorFactory hlsExtractorFactory) {
            Assertions.checkState(!this.isCreateCalled);
            this.extractorFactory = (HlsExtractorFactory) Assertions.checkNotNull(hlsExtractorFactory);
            return this;
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            Assertions.checkState(!this.isCreateCalled);
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            return this;
        }

        @Deprecated
        public Factory setMinLoadableRetryCount(int i) {
            Assertions.checkState(!this.isCreateCalled);
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy(i);
            return this;
        }

        public Factory setPlaylistParserFactory(HlsPlaylistParserFactory hlsPlaylistParserFactory) {
            Assertions.checkState(!this.isCreateCalled);
            this.playlistParserFactory = (HlsPlaylistParserFactory) Assertions.checkNotNull(hlsPlaylistParserFactory);
            return this;
        }

        public Factory setPlaylistTrackerFactory(HlsPlaylistTracker.Factory factory) {
            Assertions.checkState(!this.isCreateCalled);
            this.playlistTrackerFactory = (HlsPlaylistTracker.Factory) Assertions.checkNotNull(factory);
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.compositeSequenceableLoaderFactory = (CompositeSequenceableLoaderFactory) Assertions.checkNotNull(compositeSequenceableLoaderFactory2);
            return this;
        }

        public Factory setAllowChunklessPreparation(boolean z) {
            Assertions.checkState(!this.isCreateCalled);
            this.allowChunklessPreparation = z;
            return this;
        }

        public HlsMediaSource createMediaSource(Uri uri) {
            this.isCreateCalled = true;
            return new HlsMediaSource(uri, this.hlsDataSourceFactory, this.extractorFactory, this.compositeSequenceableLoaderFactory, this.loadErrorHandlingPolicy, this.playlistTrackerFactory.createTracker(this.hlsDataSourceFactory, this.loadErrorHandlingPolicy, this.playlistParserFactory), this.allowChunklessPreparation, this.tag);
        }

        @Deprecated
        public HlsMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            HlsMediaSource createMediaSource = createMediaSource(uri);
            if (!(handler == null || mediaSourceEventListener == null)) {
                createMediaSource.addEventListener(handler, mediaSourceEventListener);
            }
            return createMediaSource;
        }

        public int[] getSupportedTypes() {
            return new int[]{2};
        }
    }

    @Deprecated
    public HlsMediaSource(Uri uri, DataSource.Factory factory, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, 3, handler, mediaSourceEventListener);
    }

    @Deprecated
    public HlsMediaSource(Uri uri, DataSource.Factory factory, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, new DefaultHlsDataSourceFactory(factory), HlsExtractorFactory.DEFAULT, i, handler, mediaSourceEventListener, new HlsPlaylistParser());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HlsMediaSource(android.net.Uri r13, com.google.android.exoplayer2.source.hls.HlsDataSourceFactory r14, com.google.android.exoplayer2.source.hls.HlsExtractorFactory r15, int r16, android.os.Handler r17, com.google.android.exoplayer2.source.MediaSourceEventListener r18, com.google.android.exoplayer2.upstream.ParsingLoadable.Parser<com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist> r19) {
        /*
            r12 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory r7 = new com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory
            r7.<init>()
            com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy r8 = new com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy
            r8.<init>(r0)
            com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistTracker r9 = new com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistTracker
            com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy r3 = new com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy
            r3.<init>(r0)
            r0 = r14
            r4 = r19
            r9.<init>((com.google.android.exoplayer2.source.hls.HlsDataSourceFactory) r14, (com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy) r3, (com.google.android.exoplayer2.upstream.ParsingLoadable.Parser<com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist>) r4)
            r10 = 0
            r11 = 0
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            if (r1 == 0) goto L_0x002f
            if (r2 == 0) goto L_0x002f
            r0 = r12
            r12.addEventListener(r1, r2)
            goto L_0x0030
        L_0x002f:
            r0 = r12
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaSource.<init>(android.net.Uri, com.google.android.exoplayer2.source.hls.HlsDataSourceFactory, com.google.android.exoplayer2.source.hls.HlsExtractorFactory, int, android.os.Handler, com.google.android.exoplayer2.source.MediaSourceEventListener, com.google.android.exoplayer2.upstream.ParsingLoadable$Parser):void");
    }

    private HlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, HlsPlaylistTracker hlsPlaylistTracker, boolean z, @Nullable Object obj) {
        this.manifestUri = uri;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.extractorFactory = hlsExtractorFactory;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.playlistTracker = hlsPlaylistTracker;
        this.allowChunklessPreparation = z;
        this.tag = obj;
    }

    @Nullable
    public Object getTag() {
        return this.tag;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        this.mediaTransferListener = transferListener;
        this.playlistTracker.start(this.manifestUri, createEventDispatcher((MediaSource.MediaPeriodId) null), this);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new HlsMediaPeriod(this.extractorFactory, this.playlistTracker, this.dataSourceFactory, this.mediaTransferListener, this.loadErrorHandlingPolicy, createEventDispatcher(mediaPeriodId), allocator, this.compositeSequenceableLoaderFactory, this.allowChunklessPreparation);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((HlsMediaPeriod) mediaPeriod).release();
    }

    public void releaseSourceInternal() {
        this.playlistTracker.stop();
    }

    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist) {
        SinglePeriodTimeline singlePeriodTimeline;
        long j;
        long j2;
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
        long usToMs = hlsMediaPlaylist2.hasProgramDateTime ? C.usToMs(hlsMediaPlaylist2.startTimeUs) : -9223372036854775807L;
        long j3 = (hlsMediaPlaylist2.playlistType == 2 || hlsMediaPlaylist2.playlistType == 1) ? usToMs : -9223372036854775807L;
        long j4 = hlsMediaPlaylist2.startOffsetUs;
        if (this.playlistTracker.isLive()) {
            long initialStartTimeUs = hlsMediaPlaylist2.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            long j5 = hlsMediaPlaylist2.hasEndTag ? initialStartTimeUs + hlsMediaPlaylist2.durationUs : -9223372036854775807L;
            List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist2.segments;
            if (j4 == C.TIME_UNSET) {
                if (list.isEmpty()) {
                    j2 = 0;
                } else {
                    j2 = list.get(Math.max(0, list.size() - 3)).relativeStartTimeUs;
                }
                j = j2;
            } else {
                j = j4;
            }
            singlePeriodTimeline = new SinglePeriodTimeline(j3, usToMs, j5, hlsMediaPlaylist2.durationUs, initialStartTimeUs, j, true, !hlsMediaPlaylist2.hasEndTag, this.tag);
        } else {
            singlePeriodTimeline = new SinglePeriodTimeline(j3, usToMs, hlsMediaPlaylist2.durationUs, hlsMediaPlaylist2.durationUs, 0, j4 == C.TIME_UNSET ? 0 : j4, true, false, this.tag);
        }
        refreshSourceInfo(singlePeriodTimeline, new HlsManifest(this.playlistTracker.getMasterPlaylist(), hlsMediaPlaylist2));
    }
}
