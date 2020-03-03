package com.ximalaya.ting.android.sdkdownloader.downloadutil.viewholder;

import android.content.Context;
import android.view.View;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder;
import java.util.List;

public abstract class HaveDownloadCallBackHolderAdapter extends AbstractAdapter<Track> {
    private int d;

    public abstract DownloadCallBackViewHolder a(Track track, View view);

    public abstract void a(DownloadCallBackViewHolder downloadCallBackViewHolder, Track track, int i);

    public abstract int c();

    public HaveDownloadCallBackHolderAdapter(Context context, List<Track> list, int i) {
        super(context, list);
        this.d = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r4, android.view.View r5, android.view.ViewGroup r6) {
        /*
            r3 = this;
            java.util.List r6 = r3.b
            java.lang.Object r6 = r6.get(r4)
            com.ximalaya.ting.android.opensdk.model.track.Track r6 = (com.ximalaya.ting.android.opensdk.model.track.Track) r6
            java.util.List r0 = r3.b
            r1 = 0
            if (r0 == 0) goto L_0x003c
            java.util.List r0 = r3.b
            int r0 = r0.size()
            if (r4 >= r0) goto L_0x003c
            if (r5 != 0) goto L_0x0032
            android.view.LayoutInflater r5 = r3.c
            int r0 = r3.c()
            android.view.View r5 = r5.inflate(r0, r1)
            com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder r1 = r3.a((com.ximalaya.ting.android.opensdk.model.track.Track) r6, (android.view.View) r5)
            com.ximalaya.ting.android.sdkdownloader.XmDownloadManager r0 = com.ximalaya.ting.android.sdkdownloader.XmDownloadManager.b()
            int r2 = r3.d
            r0.a((int) r2, (com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder) r1)
            r5.setTag(r1)
            goto L_0x003c
        L_0x0032:
            java.lang.Object r0 = r5.getTag()
            r1 = r0
            com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder r1 = (com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadCallBackViewHolder) r1
            r1.a(r6)
        L_0x003c:
            r3.a(r1, r6, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.sdkdownloader.downloadutil.viewholder.HaveDownloadCallBackHolderAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public final void a(View view, int i) {
        if (view != null && i >= 0) {
            a((DownloadCallBackViewHolder) view.getTag(), (Track) this.b.get(i), i);
        }
    }
}
