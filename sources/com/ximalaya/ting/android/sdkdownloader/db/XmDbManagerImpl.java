package com.ximalaya.ting.android.sdkdownloader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.smarthome.family.FamilyMemberData;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.downloadutil.DownloadState;
import com.ximalaya.ting.android.sdkdownloader.exception.DbException;
import com.ximalaya.ting.android.sdkdownloader.util.XmUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mp4parser.boxes.iso14496.part12.FreeBox;

public class XmDbManagerImpl implements IXmDbManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2333a = XmSqLiteHelper.a("Track");
    public static final String b = XmSqLiteHelper.a("dataId");
    public static final String c = XmSqLiteHelper.a("announcerId");
    public static final String d = XmSqLiteHelper.a(DTransferConstants.ad);
    public static final int e = DownloadState.FINISHED.value();
    private XmSqLiteHelper f;
    private SQLiteDatabase g;

    public XmDbManagerImpl(Context context) {
        this.f = new XmSqLiteHelper(context);
    }

    private SQLiteDatabase c() {
        if (this.g == null || !this.g.isOpen() || this.g.isReadOnly()) {
            this.g = this.f.getWritableDatabase();
        }
        return this.g;
    }

    public Track a(long j) {
        Cursor cursor;
        List<Track> list;
        try {
            cursor = c().query(f2333a, (String[]) null, b + " = ?", new String[]{j + ""}, (String) null, (String) null, (String) null);
        } catch (Exception e2) {
            e2.printStackTrace();
            cursor = null;
        }
        try {
            list = a(cursor);
        } catch (DbException e3) {
            e3.printStackTrace();
            list = null;
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Track> a(Set<Long> set) {
        Cursor cursor;
        List<Track> list;
        try {
            String a2 = XmUtils.a((Collection) set);
            cursor = c().query(f2333a, (String[]) null, b + " in (" + a2 + ") ", (String[]) null, (String) null, (String) null, (String) null);
        } catch (Exception e2) {
            e2.printStackTrace();
            cursor = null;
        }
        try {
            list = a(cursor);
        } catch (DbException e3) {
            e3.printStackTrace();
            list = null;
        }
        if (list == null || list.isEmpty()) {
            return XmUtils.b();
        }
        return list;
    }

    public boolean a(Collection<Track> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        c().beginTransaction();
        try {
            for (Track next : collection) {
                if (next != null) {
                    c().insert(f2333a, (String) null, a(next, false));
                }
            }
            c().setTransactionSuccessful();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            c().endTransaction();
        }
    }

    public boolean a(final Track track) {
        return a((Collection<Track>) new ArrayList<Track>() {
            {
                add(track);
            }
        });
    }

    public List<Track> a() {
        try {
            try {
                return a(c().query(f2333a, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null));
            } catch (Exception e2) {
                e2.printStackTrace();
                return XmUtils.b();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public boolean a(long j, boolean z) {
        int i;
        if (z) {
            try {
                i = c().delete(f2333a, b + " = ? and downloadstatus = ? ", new String[]{j + "", DownloadState.FINISHED.value() + ""});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            i = c().delete(f2333a, b + " = ? and downloadstatus <> ? ", new String[]{j + "", DownloadState.FINISHED.value() + ""});
        }
        return i > 0;
    }

    public boolean a(List<Long> list, boolean z) {
        int i;
        try {
            String a2 = XmUtils.a((Collection) list);
            if (TextUtils.isEmpty(a2)) {
                c().endTransaction();
                return false;
            }
            c().beginTransaction();
            if (z) {
                i = c().delete(f2333a, b + " in (" + a2 + ") and downloadstatus = ? ", new String[]{DownloadState.FINISHED.value() + ""});
            } else {
                i = c().delete(f2333a, b + " in (" + a2 + ") and downloadstatus <> ? ", new String[]{DownloadState.FINISHED.value() + ""});
            }
            if (i > 0) {
                c().setTransactionSuccessful();
                c().endTransaction();
                return true;
            }
            c().endTransaction();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            c().endTransaction();
            throw th;
        }
    }

    public void b(Track track) {
        if (track != null) {
            try {
                ContentValues a2 = a(track, true);
                if (c().update(f2333a, a2, b + " = ?", new String[]{track.a() + ""}) <= 0) {
                    a(track);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean a(Map<Long, String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        try {
            c().beginTransaction();
            for (Map.Entry next : map.entrySet()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("downloadedsavefilepath", (String) next.getValue());
                c().update(f2333a, contentValues, b + " = ?", new String[]{next.getKey() + ""});
            }
            c().setTransactionSuccessful();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            c().endTransaction();
        }
    }

    public boolean a(long j, String str) {
        if (j == 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("downloadedsavefilepath", str);
            c().update(f2333a, contentValues, b + " = ?", new String[]{j + ""});
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void b(Map<Long, Integer> map) {
        if (map != null && !map.isEmpty()) {
            try {
                c().beginTransaction();
                for (Map.Entry next : map.entrySet()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("orderpositon", (Integer) next.getValue());
                    c().update(f2333a, contentValues, b + " = ? and downloadstatus = ? ", new String[]{next.getKey() + "", DownloadState.FINISHED.value() + ""});
                }
                c().setTransactionSuccessful();
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                c().endTransaction();
                throw th;
            }
            c().endTransaction();
        }
    }

    public void a(DownloadState downloadState, DownloadState downloadState2) {
        if (!downloadState.equals(downloadState2)) {
            int value = downloadState.value();
            int value2 = downloadState2.value();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("downloadstatus", Integer.valueOf(value2));
                SQLiteDatabase c2 = c();
                String str = f2333a;
                c2.update(str, contentValues, "downloadstatus = ?", new String[]{value + ""});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(DownloadState downloadState, DownloadState downloadState2, List<Long> list) {
        if (!downloadState.equals(downloadState2)) {
            int value = downloadState.value();
            int value2 = downloadState2.value();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("downloadstatus", Integer.valueOf(value2));
                c().update(f2333a, contentValues, b + " in (?) and downloadstatus = ?", new String[]{XmUtils.a((Collection) list), value + ""});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(DownloadState downloadState) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("downloadstatus", Integer.valueOf(downloadState.value()));
            SQLiteDatabase c2 = c();
            String str = f2333a;
            c2.update(str, contentValues, "downloadstatus <> ?", new String[]{DownloadState.FINISHED.value() + ""});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b() {
        try {
            this.g.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private ContentValues a(Track track, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("downloadedsavefilepath", track.aq());
        contentValues.put("downloadstatus", Integer.valueOf(track.at()));
        contentValues.put("downloadsize", Long.valueOf(track.al()));
        contentValues.put("downloadedsize", Long.valueOf(track.ar()));
        if (track.at() == e) {
            contentValues.put("finishdownloadtime", Long.valueOf(System.currentTimeMillis()));
        }
        if (z) {
            return contentValues;
        }
        contentValues.put("playsize32", Integer.valueOf(track.ac()));
        contentValues.put("downloadurl", track.ak());
        contentValues.put("coverurllarge", track.V());
        contentValues.put("coverurlmiddle", track.T());
        contentValues.put("playurl32", track.ab());
        contentValues.put("trackintro", track.S());
        contentValues.put("playurl64", track.ad());
        contentValues.put("downloadcount", Integer.valueOf(track.aa()));
        contentValues.put("playurl64m4a", track.ah());
        contentValues.put("ordernum", Integer.valueOf(track.aj()));
        contentValues.put("playsize64", Integer.valueOf(track.ae()));
        contentValues.put("lastplayedmills", Integer.valueOf(track.c()));
        contentValues.put("playcount", Integer.valueOf(track.X()));
        contentValues.put("islike", Integer.valueOf(track.aG() ? 1 : 0));
        contentValues.put("tracktitle", track.N());
        contentValues.put("coverurlsmall", track.T());
        contentValues.put("tracktags", track.R());
        contentValues.put("kind", track.b());
        contentValues.put("createdat", Long.valueOf(track.ap()));
        contentValues.put("downloadtime", Long.valueOf(track.Q()));
        contentValues.put("playsize64m4a", track.ai());
        contentValues.put("dataid", Long.valueOf(track.a()));
        contentValues.put("commentcount", Integer.valueOf(track.Z()));
        contentValues.put("updatedat", Long.valueOf(track.an()));
        contentValues.put("duration", Integer.valueOf(track.W()));
        contentValues.put("playurl24m4a", track.af());
        contentValues.put("source", Integer.valueOf(track.am()));
        contentValues.put("favoritecount", Integer.valueOf(track.Y()));
        contentValues.put("playsize24m4a", track.ag());
        contentValues.put("uid", Long.valueOf(track.v()));
        contentValues.put("price", Double.valueOf(track.z()));
        contentValues.put("discountedprice", Double.valueOf(track.A()));
        contentValues.put(FreeBox.f3848a, Boolean.valueOf(track.B()));
        contentValues.put("authorized", Boolean.valueOf(track.D()));
        contentValues.put("ispaid", Boolean.valueOf(track.w()));
        SubordinatedAlbum ao = track.ao();
        if (ao != null) {
            contentValues.put("uptodatetime", Long.valueOf(ao.c()));
            contentValues.put("album_coverurllarge", ao.h());
            contentValues.put("album_coverurlmiddle", ao.g());
            contentValues.put("album_coverurlsmall", ao.f());
            contentValues.put("albumtitle", ao.e());
            contentValues.put("albumid", Long.valueOf(ao.d()));
        }
        Announcer I = track.I();
        if (I != null) {
            contentValues.put("followingcount", Long.valueOf(I.k()));
            contentValues.put("vsignature", I.h());
            contentValues.put("releasedtrackcount", Long.valueOf(I.m()));
            contentValues.put("vcategoryid", Long.valueOf(I.f()));
            contentValues.put(FamilyMemberData.d, I.b());
            contentValues.put("announcerposition", I.i());
            contentValues.put("verified", Integer.valueOf(I.d() ? 1 : 0));
            contentValues.put("vdesc", I.g());
            contentValues.put("releasedalbumcount", Long.valueOf(I.l()));
            contentValues.put("avatarurl", I.c());
            contentValues.put("followercount", Long.valueOf(I.j()));
            contentValues.put("annoucer_kind", I.e());
            contentValues.put("announcerid", Long.valueOf(I.a()));
        }
        return contentValues;
    }

    public List<Track> a(Cursor cursor) throws DbException {
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() != 0) {
            ArrayList arrayList = new ArrayList();
            do {
                try {
                    Track track = new Track();
                    track.u(cursor.getInt(cursor.getColumnIndex("playsize32")));
                    track.j(cursor.getLong(cursor.getColumnIndex("downloadsize")));
                    track.t(cursor.getString(cursor.getColumnIndex("downloadurl")));
                    track.m(cursor.getString(cursor.getColumnIndex("coverurllarge")));
                    track.l(cursor.getString(cursor.getColumnIndex("coverurlmiddle")));
                    track.n(cursor.getString(cursor.getColumnIndex("playurl32")));
                    track.u(cursor.getString(cursor.getColumnIndex("downloadedsavefilepath")));
                    track.z(cursor.getInt(cursor.getColumnIndex("downloadstatus")));
                    track.j(cursor.getString(cursor.getColumnIndex("trackintro")));
                    track.o(cursor.getString(cursor.getColumnIndex("playurl64")));
                    track.t(cursor.getInt(cursor.getColumnIndex("downloadcount")));
                    track.r(cursor.getString(cursor.getColumnIndex("playurl64m4a")));
                    track.w(cursor.getInt(cursor.getColumnIndex("ordernum")));
                    track.v(cursor.getInt(cursor.getColumnIndex("playsize64")));
                    track.a(cursor.getInt(cursor.getColumnIndex("lastplayedmills")));
                    track.q(cursor.getInt(cursor.getColumnIndex("playcount")));
                    boolean z = false;
                    track.i(cursor.getInt(cursor.getColumnIndex("islike")) == 1);
                    track.h(cursor.getString(cursor.getColumnIndex("tracktitle")));
                    track.k(cursor.getString(cursor.getColumnIndex("coverurlsmall")));
                    track.i(cursor.getString(cursor.getColumnIndex("tracktags")));
                    track.a(cursor.getString(cursor.getColumnIndex("kind")));
                    track.m(cursor.getLong(cursor.getColumnIndex("createdat")));
                    track.i(cursor.getLong(cursor.getColumnIndex("downloadtime")));
                    track.s(cursor.getString(cursor.getColumnIndex("playsize64m4a")));
                    track.n((long) cursor.getInt(cursor.getColumnIndex("downloadedsize")));
                    track.a(cursor.getLong(cursor.getColumnIndex("dataid")));
                    track.s(cursor.getInt(cursor.getColumnIndex("commentcount")));
                    track.l(cursor.getLong(cursor.getColumnIndex("updatedat")));
                    track.p(cursor.getInt(cursor.getColumnIndex("duration")));
                    track.p(cursor.getString(cursor.getColumnIndex("playurl24m4a")));
                    track.x(cursor.getInt(cursor.getColumnIndex("source")));
                    track.r(cursor.getInt(cursor.getColumnIndex("favoritecount")));
                    track.q(cursor.getString(cursor.getColumnIndex("playsize24m4a")));
                    if (cursor.getColumnIndex("playpathhq") >= 0) {
                        track.C(cursor.getString(cursor.getColumnIndex("playpathhq")));
                    }
                    if (cursor.getColumnIndex("orderpositon") >= 0) {
                        track.n(cursor.getInt(cursor.getColumnIndex("orderpositon")));
                    }
                    track.f(cursor.getLong(cursor.getColumnIndex("uid")));
                    track.a(cursor.getDouble(cursor.getColumnIndex("price")));
                    track.b(cursor.getDouble(cursor.getColumnIndex("discountedprice")));
                    track.e(cursor.getInt(cursor.getColumnIndex(FreeBox.f3848a)) == 1);
                    track.f(cursor.getInt(cursor.getColumnIndex("authorized")) == 1);
                    track.c(cursor.getInt(cursor.getColumnIndex("ispaid")) == 1);
                    Announcer announcer = new Announcer();
                    announcer.d((long) cursor.getInt(cursor.getColumnIndex("followingcount")));
                    announcer.e(cursor.getString(cursor.getColumnIndex("vsignature")));
                    announcer.f((long) cursor.getInt(cursor.getColumnIndex("releasedtrackcount")));
                    announcer.b(cursor.getLong(cursor.getColumnIndex("vcategoryid")));
                    announcer.a(cursor.getString(cursor.getColumnIndex(FamilyMemberData.d)));
                    announcer.f(cursor.getString(cursor.getColumnIndex("announcerposition")));
                    if (cursor.getInt(cursor.getColumnIndex("verified")) == 1) {
                        z = true;
                    }
                    announcer.a(z);
                    announcer.d(cursor.getString(cursor.getColumnIndex("vdesc")));
                    announcer.e(cursor.getLong(cursor.getColumnIndex("releasedalbumcount")));
                    announcer.b(cursor.getString(cursor.getColumnIndex("avatarurl")));
                    announcer.c(cursor.getLong(cursor.getColumnIndex("followercount")));
                    announcer.c(cursor.getString(cursor.getColumnIndex("annoucer_kind")));
                    announcer.a(cursor.getLong(cursor.getColumnIndex("announcerid")));
                    track.a(announcer);
                    SubordinatedAlbum subordinatedAlbum = new SubordinatedAlbum();
                    subordinatedAlbum.a(cursor.getLong(cursor.getColumnIndex("uptodatetime")));
                    subordinatedAlbum.f(cursor.getString(cursor.getColumnIndex("album_coverurllarge")));
                    subordinatedAlbum.e(cursor.getString(cursor.getColumnIndex("album_coverurlmiddle")));
                    subordinatedAlbum.d(cursor.getString(cursor.getColumnIndex("album_coverurlsmall")));
                    subordinatedAlbum.c(cursor.getString(cursor.getColumnIndex("albumtitle")));
                    subordinatedAlbum.b(cursor.getLong(cursor.getColumnIndex("albumid")));
                    track.a(subordinatedAlbum);
                    track.r(cursor.getLong(cursor.getColumnIndex("finishdownloadtime")));
                    arrayList.add(track);
                } finally {
                    try {
                        cursor.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } while (cursor.moveToNext());
            return arrayList;
        } else if (cursor == null) {
            return null;
        } else {
            try {
                cursor.close();
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }
}
