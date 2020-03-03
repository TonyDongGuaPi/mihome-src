package com.tiqiaa.database;

import android.content.Context;
import android.database.Cursor;
import com.imi.fastjson.JSON;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.tiqiaa.icontrol.util.LogUtil;
import com.tiqiaa.remote.entity.Brand;
import com.tiqiaa.remote.entity.Infrared;
import com.tiqiaa.remote.entity.Key;
import com.tiqiaa.remote.entity.MatchKey;
import com.tiqiaa.remote.entity.Remote;
import com.tiqiaa.remote.entity.Room;
import com.tiqiaa.tv.entity.ChannelNum;
import com.tiqiaa.tv.entity.City;
import com.tiqiaa.tv.entity.CityProvider;
import com.tiqiaa.tv.entity.Province;
import com.tiqiaa.tv.entity.TvChannel;
import com.tiqiaa.tv.entity.TvForenotice;
import com.tiqiaa.tv.entity.TvProvider;
import com.tiqiaa.tv.entity.TvShow;
import com.tiqiaa.tv.entity.TvShowImg;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseManager {
    private static final int DATABASE_VERSION = 9;
    private static final String DB_NAME = "tv.db";
    private static final String DB_UPD_NAME = "tv_upd.db";
    private static final String TAG = "DataBaseManager";
    /* access modifiers changed from: private */
    public static Context context;
    private static DbUtils db;
    private static String dbPath;

    public static void init(Context context2) {
        context = context2;
        installDB();
        dbPath = context.getDatabasePath(DB_NAME).getAbsolutePath();
        db = DbUtils.a(context, dbPath, 9, new DbUtils.DbUpgradeListener() {
            public void onUpgrade(DbUtils dbUtils, int i, int i2) {
                LogUtil.e(DataBaseManager.TAG, "db upgrade from verion " + i + " to " + i2);
                if (i < 9) {
                    DataBaseManager.copyUpdDB();
                    DbUtils a2 = DbUtils.a(DataBaseManager.context, DataBaseManager.context.getDatabasePath(DataBaseManager.DB_UPD_NAME).getAbsolutePath(), i2, (DbUtils.DbUpgradeListener) null);
                    try {
                        dbUtils.a("DROP TABLE tb_city_provider");
                        dbUtils.a("DROP TABLE tb_province");
                        List<CityProvider> c = a2.c(CityProvider.class);
                        List<Province> c2 = a2.c(Province.class);
                        List<City> c3 = a2.c(City.class);
                        dbUtils.c((List<?>) c);
                        dbUtils.c((List<?>) c2);
                        dbUtils.a((List<?>) c3);
                        DataBaseManager.deleteUpdDb();
                    } catch (Exception e) {
                        LogUtil.e(DataBaseManager.TAG, "update db failed!" + e);
                    }
                }
            }
        });
        db.b(true);
    }

    private static class Singlenton {
        private static DataBaseManager instance = new DataBaseManager();

        private Singlenton() {
        }

        public static DataBaseManager getInstance() {
            return instance;
        }
    }

    public static DataBaseManager getInstance() {
        return Singlenton.getInstance();
    }

    private static void installDB() {
        FileOutputStream fileOutputStream;
        try {
            byte[] bArr = new byte[256];
            File file = new File(context.getDatabasePath(DB_NAME).getAbsolutePath());
            if (!file.exists()) {
                File file2 = new File(file.getParent());
                if ((file2.exists() || file2.mkdirs()) && file.createNewFile()) {
                    Throwable th = null;
                    try {
                        InputStream resourceAsStream = DataBaseManager.class.getResourceAsStream(DB_NAME);
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            while (true) {
                                int read = resourceAsStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                        } catch (Throwable th2) {
                            if (th == null) {
                                th = th2;
                            }
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (th != null) {
                            th = th;
                        }
                        throw th;
                    }
                }
            }
        } catch (IOException e) {
            LogUtil.e(TAG, "install db file failed!");
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void copyUpdDB() {
        FileOutputStream fileOutputStream;
        try {
            byte[] bArr = new byte[256];
            File file = new File(context.getDatabasePath(DB_UPD_NAME).getAbsolutePath());
            if (!file.exists() || file.delete()) {
                File file2 = new File(file.getParent());
                if ((file2.exists() || file2.mkdirs()) && file.createNewFile()) {
                    Throwable th = null;
                    try {
                        InputStream resourceAsStream = DataBaseManager.class.getResourceAsStream(DB_NAME);
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            while (true) {
                                int read = resourceAsStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.close();
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                        } catch (Throwable th2) {
                            if (th == null) {
                                th = th2;
                            }
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (th != null) {
                            th = th;
                        }
                        throw th;
                    }
                }
            }
        } catch (IOException e) {
            LogUtil.e(TAG, "install db file failed!");
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void deleteUpdDb() {
        File file = new File(context.getDatabasePath(DB_UPD_NAME).getAbsolutePath());
        if (file.exists() && file.delete()) {
        }
    }

    public List<Province> getProvinces() {
        try {
            return db.c(Province.class);
        } catch (Exception e) {
            LogUtil.e(TAG, "get provinces error");
            e.printStackTrace();
            return null;
        }
    }

    public List<City> getCities() {
        try {
            return db.c(City.class);
        } catch (Exception e) {
            LogUtil.e(TAG, "get cities error");
            e.printStackTrace();
            return null;
        }
    }

    public List<City> getProvinceCities(int i) {
        try {
            Selector a2 = Selector.a((Class<?>) City.class);
            a2.a(WhereBuilder.a("province_id", "=", Integer.valueOf(i)));
            return db.b(a2);
        } catch (Exception e) {
            LogUtil.e(TAG, "get cities error");
            e.printStackTrace();
            return null;
        }
    }

    public List<TvProvider> getProvidersFromCity(int i) {
        try {
            Selector a2 = Selector.a((Class<?>) CityProvider.class);
            a2.a(WhereBuilder.a("city_id", "=", Integer.valueOf(i)));
            List<CityProvider> b = db.b(a2);
            ArrayList arrayList = new ArrayList();
            for (CityProvider provider_id : b) {
                arrayList.add(getTvProvidersById(provider_id.getProvider_id()));
            }
            return arrayList;
        } catch (Exception e) {
            LogUtil.e(TAG, "get cities error");
            e.printStackTrace();
            return null;
        }
    }

    public TvProvider getTvProvidersById(int i) {
        try {
            return (TvProvider) db.b(TvProvider.class, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveTvForenotice(TvForenotice tvForenotice) {
        try {
            db.c((Object) tvForenotice);
            if (tvForenotice.getTvshow_img() != null) {
                getInstance().saveShowImg(tvForenotice.getTvshow_img());
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                db.a((Object) tvForenotice, "playDate");
            } catch (DbException e2) {
                e2.printStackTrace();
            }
        }
    }

    public List<TvForenotice> getCachedTvForenotices(String str) {
        try {
            Selector a2 = Selector.a((Class<?>) TvForenotice.class);
            a2.a(WhereBuilder.a("playDate", "=", str));
            a2.b(WhereBuilder.a("type", "=", "0"));
            return db.b(a2);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "getCachedTvForenotices failed!");
            return null;
        }
    }

    public List<ChannelNum> getChannelNums() {
        try {
            return db.c(ChannelNum.class);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "getChannelNums from db failed!");
            return null;
        }
    }

    public List<TvForenotice> getAllCachedTvForenotices() {
        try {
            return db.c(TvForenotice.class);
        } catch (Exception e) {
            LogUtil.e(TAG, "getAllCachedTvForenotices error:" + e.getLocalizedMessage());
            return null;
        }
    }

    public List<TvForenotice> getCachedTvForenotices(String str, int i) {
        try {
            Selector a2 = Selector.a((Class<?>) TvForenotice.class);
            a2.a(WhereBuilder.a("playDate", "=", str));
            a2.b(WhereBuilder.a("channel_id", "=", Integer.valueOf(i)));
            return db.b(a2);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "getCachedTvForenotices failed!");
            return null;
        }
    }

    public List<TvForenotice> getAppointedForenotices() {
        try {
            Selector a2 = Selector.a((Class<?>) TvForenotice.class);
            a2.a(WhereBuilder.a("type", "=", 1));
            return db.b(a2);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "getCachedTvForenotices failed!");
            return null;
        }
    }

    public void saveOrUpdateTvForenotice(TvForenotice tvForenotice) {
        try {
            db.c((Object) tvForenotice);
            db.c((Object) tvForenotice);
            if (tvForenotice.getTvshow_img() != null) {
                getInstance().saveShowImg(tvForenotice.getTvshow_img());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "save tvforenotice failed, try update!");
            try {
                db.a((Object) tvForenotice, "type");
                db.c((Object) tvForenotice);
                if (tvForenotice.getTvshow_img() != null) {
                    getInstance().saveShowImg(tvForenotice.getTvshow_img());
                }
            } catch (Exception unused) {
                e.printStackTrace();
                LogUtil.e(TAG, "saveOrUpdateTvForenotice failed!");
            }
        }
    }

    public void deleteTvForenotice(TvForenotice tvForenotice) {
        try {
            tvForenotice.setType(0);
            db.a((Object) tvForenotice, "type");
        } catch (Exception unused) {
            LogUtil.e(TAG, "deleteTvForenotice failed!");
        }
    }

    public void saveTvshow(TvShow tvShow) {
        try {
            db.c((Object) tvShow);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "save tvshow failed!");
        }
    }

    public List<TvShow> getAllSavedTvShows() {
        try {
            return db.c(TvShow.class);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "getAllSavedTvShows");
            return null;
        }
    }

    public List<TvShow> getAllFavoriteTvShows() {
        try {
            Selector a2 = Selector.a((Class<?>) TvShow.class);
            a2.a(WhereBuilder.a("type", "=", 1));
            return db.b(a2);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "getAllSavedTvShows");
            return null;
        }
    }

    public TvShow getSavedTvShowById(int i) {
        try {
            return (TvShow) db.b(TvShow.class, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveOrUpdateTvshow(TvShow tvShow) {
        try {
            db.c((Object) tvShow);
        } catch (Exception e) {
            try {
                db.a((Object) tvShow, "type");
            } catch (Exception unused) {
                e.printStackTrace();
                LogUtil.e(TAG, "saveOrUpdateTvshow tvshow failed!");
            }
        }
    }

    public List<TvForenotice> findTvForenoticeByshowid(int i) {
        try {
            Selector a2 = Selector.a((Class<?>) TvForenotice.class);
            a2.a(WhereBuilder.a("fid2", "=", Integer.valueOf(i)));
            a2.c(WhereBuilder.a("fid2", "=", 0).b("fid", "=", Integer.valueOf(i)));
            return db.b(a2);
        } catch (Exception unused) {
            LogUtil.e(TAG, "findTvForenoticeByshowid failed!");
            return null;
        }
    }

    public List<Room> getAllRooms() {
        try {
            return db.c(Room.class);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public List<Remote> getAllRemotesInRoom(Room room) {
        List<String> remote_ids;
        if (room == null || (remote_ids = room.getRemote_ids()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String remoteById : remote_ids) {
            Remote remoteById2 = getRemoteById(remoteById);
            if (remoteById2 != null) {
                arrayList.add(remoteById2);
            }
        }
        room.setRemotes(arrayList);
        return arrayList;
    }

    public Remote getRemoteById(String str) {
        try {
            Remote remote = (Remote) db.b(Remote.class, str);
            fillRemoteData(remote);
            return remote;
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    private void fillRemoteData(Remote remote) {
        remote.setBrand(getBrandById(remote.getBrand_id()));
        List<Key> keysByRemoteId = getKeysByRemoteId(remote.getId());
        if (keysByRemoteId != null) {
            for (Key next : keysByRemoteId) {
                next.setInfrareds(getInfraredsByKeyId(next.getId()));
            }
            remote.setKeys(keysByRemoteId);
        }
    }

    public Brand getBrandById(long j) {
        try {
            return (Brand) db.b(Brand.class, Long.valueOf(j));
        } catch (Exception e) {
            LogUtil.e(TAG, "获取brand失败");
            LogUtil.printException(e);
            return null;
        }
    }

    public List<Remote> getAllRemotes() {
        try {
            List<Remote> c = db.c(Remote.class);
            if (c != null && c.size() > 0) {
                for (Remote fillRemoteData : c) {
                    fillRemoteData(fillRemoteData);
                }
            }
            return c;
        } catch (Exception unused) {
            return null;
        }
    }

    public List<Remote> getRemotesByType(int i) {
        try {
            Selector a2 = Selector.a((Class<?>) Remote.class);
            a2.a(WhereBuilder.a("type", "=", Integer.valueOf(i)));
            List<Remote> b = db.b(a2);
            if (b != null && b.size() > 0) {
                for (Remote fillRemoteData : b) {
                    fillRemoteData(fillRemoteData);
                }
            }
            return b;
        } catch (Exception unused) {
            return null;
        }
    }

    public List<Infrared> getAllInfrareds() {
        try {
            return db.c(Infrared.class);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public boolean saveRemote(Remote remote) {
        try {
            db.c((Object) remote);
            List<Key> keys = remote.getKeys();
            if (keys == null) {
                return true;
            }
            saveOrUpdateAllKeys(keys);
            for (Key next : keys) {
                List<Infrared> infrareds = next.getInfrareds();
                List<Infrared> infraredsByKeyId = getInfraredsByKeyId(next.getId());
                if ((infraredsByKeyId == null || infraredsByKeyId.size() == 0) && infrareds != null) {
                    saveAllInfrareds(infrareds);
                }
            }
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    private void saveOrUpdateAllKeys(List<Key> list) {
        try {
            db.c((List<?>) list);
        } catch (Exception e) {
            LogUtil.printException(e);
            try {
                db.a((List<?>) list, "remoteId");
            } catch (Exception e2) {
                LogUtil.printException(e2);
            }
        }
    }

    private void saveAllInfrareds(List<Infrared> list) {
        try {
            db.c((List<?>) list);
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public boolean saveInfrared(Infrared infrared) {
        try {
            db.c((Object) infrared);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean saveOrUpdateRoom(Room room) {
        try {
            room.setRemote_ids_json(JSON.toJSONString(room.getRemote_ids()));
            db.a((Object) room);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public void updateRoomRemotes(Room room) {
        try {
            room.setRemote_ids_json(JSON.toJSONString(room.getRemote_ids()));
            db.a((Object) room, "remote_ids_json");
        } catch (Exception e) {
            LogUtil.e(TAG, "更新房间中遥控器数据失败");
            LogUtil.printException(e);
        }
    }

    public boolean deleteRemote(Remote remote) {
        try {
            db.e((Object) remote);
            delKeysByRemoteId(remote.getId());
            List<Key> keys = remote.getKeys();
            if (keys == null) {
                return true;
            }
            for (Key id : keys) {
                delInfraredsByKeyId(id.getId());
            }
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean deleteRemoteById(String str) {
        try {
            Remote remoteById = getRemoteById(str);
            if (remoteById == null) {
                return true;
            }
            deleteRemote(remoteById);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean saveOrUpdateRemote(Remote remote) {
        try {
            db.a((Object) remote);
            List<Key> keys = remote.getKeys();
            if (keys == null) {
                return true;
            }
            for (Key next : keys) {
                db.a((Object) next);
                List<Infrared> infrareds = next.getInfrareds();
                delInfraredsByKeyId(next.getId());
                if (infrareds != null) {
                    for (Infrared a2 : infrareds) {
                        db.a((Object) a2);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public List<Key> getKeysByRemoteId(String str) {
        try {
            Selector a2 = Selector.a((Class<?>) Key.class);
            a2.a(WhereBuilder.a("remoteId", "=", str));
            return db.b(a2);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public List<Infrared> getInfraredsByKeyId(long j) {
        try {
            Selector a2 = Selector.a((Class<?>) Infrared.class);
            a2.a(WhereBuilder.a("keyId", "=", Long.valueOf(j)));
            return db.b(a2);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public void delKeysByRemoteId(String str) {
        try {
            db.e((List<?>) getKeysByRemoteId(str));
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public void delInfraredsByKeyId(long j) {
        try {
            db.e((List<?>) getInfraredsByKeyId(j));
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public boolean saveRoom(Room room) {
        try {
            room.setRemote_ids_json(JSON.toJSONString(room.getRemote_ids()));
            db.c((Object) room);
            if (room.getRemotes() == null || room.getRemotes().size() <= 0) {
                return true;
            }
            for (Remote saveOrUpdateRemote : room.getRemotes()) {
                saveOrUpdateRemote(saveOrUpdateRemote);
            }
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean deleteRoom(Room room) {
        try {
            db.e((Object) room);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public void refreshRemoteInDB(String str, Remote remote) {
        try {
            saveRemote(remote);
            List<Room> allRooms = getAllRooms();
            if (allRooms == null) {
                return;
            }
            if (allRooms.size() != 0) {
                for (Room next : allRooms) {
                    if (next.getRemote_ids().contains(str)) {
                        next.getRemote_ids().remove(str);
                        next.getRemote_ids().add(remote.getId());
                    }
                    updateRoomRemotes(next);
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "刷新遥控器数据失败...");
            LogUtil.printException(e);
        }
    }

    public void deleteOverdueCachedForenotice(long j) {
        try {
            db.a((Class<?>) TvForenotice.class, WhereBuilder.a("cacheDate", "<", Long.valueOf(new Date().getTime() - j)).b("type", Operators.NOT_EQUAL2, 1));
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public void deleteOverdueCachedTvShow(long j) {
        try {
            db.a((Class<?>) TvShow.class, WhereBuilder.a("cacheDate", "<", Long.valueOf(new Date().getTime() - j)).b("type", Operators.NOT_EQUAL2, 1));
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public void deleteOverdueAppointedForenotice() {
        try {
            Date date = new Date();
            Selector a2 = Selector.a((Class<?>) TvForenotice.class);
            a2.a(WhereBuilder.a("pt", "<", date).b("type", "=", "1"));
            List<TvForenotice> b = db.b(a2);
            if (b != null) {
                db.e((List<?>) b);
                ArrayList arrayList = new ArrayList();
                for (TvForenotice tvForenotice : b) {
                    if (tvForenotice.getTvshow_img() != null) {
                        arrayList.add(tvForenotice.getTvshow_img());
                    }
                }
                if (arrayList.size() > 0) {
                    db.e((List<?>) arrayList);
                }
            }
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public void updateMatchKeyTable(List<MatchKey> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    dropMatchKey();
                    db.c((List<?>) list);
                    context.getSharedPreferences("match_key_update_date", 0).edit().putString("lastUpDateDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())).commit();
                }
            } catch (Exception e) {
                LogUtil.e(TAG, "更新遥控器匹配序列表失败。。。");
                LogUtil.printException(e);
            }
        }
    }

    public List<MatchKey> getMatchKeyByType(int i) {
        try {
            Selector a2 = Selector.a((Class<?>) MatchKey.class);
            a2.a(WhereBuilder.a("appliance_type", "=", Integer.valueOf(i)));
            return db.b(a2);
        } catch (Exception e) {
            LogUtil.e(TAG, "获取匹配按键失败。");
            LogUtil.printException(e);
            return null;
        }
    }

    public boolean dropMatchKey() {
        try {
            db.a((Class<?>) MatchKey.class);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean dropBrands() {
        try {
            db.a((Class<?>) Brand.class);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean dropProvinces() {
        try {
            db.a((Class<?>) Province.class);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean dropCities() {
        try {
            db.a((Class<?>) City.class);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean dropProviders() {
        try {
            db.a((Class<?>) TvProvider.class);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public boolean dropCityProviders() {
        try {
            db.g((Class<?>) CityProvider.class);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public void saveBrands(List<Brand> list) {
        if (list != null) {
            try {
                db.a((List<?>) list);
            } catch (Exception e) {
                LogUtil.printException(e);
            }
        }
    }

    public boolean saveBrand(Brand brand) {
        try {
            db.c((Object) brand);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public List<Brand> getBrands() {
        try {
            return db.c(Brand.class);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public boolean saveShowImg(TvShowImg tvShowImg) {
        try {
            db.c((Object) tvShowImg);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public TvShowImg getTvShowImgByshowId(int i) {
        try {
            Selector a2 = Selector.a((Class<?>) TvShowImg.class);
            a2.a(WhereBuilder.a("tvshow_id", "=", Integer.valueOf(i)));
            List b = db.b(a2);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (TvShowImg) b.get(0);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public boolean saveCities(List<City> list) {
        try {
            db.c((List<?>) list);
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "刷新城市表失败");
            LogUtil.printException(e);
            return true;
        }
    }

    public boolean saveProviders(List<TvProvider> list) {
        try {
            db.c((List<?>) list);
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "刷新运营商表失败");
            LogUtil.printException(e);
            return true;
        }
    }

    public boolean saveProvince(List<Province> list) {
        try {
            db.c((List<?>) list);
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "刷新省份表失败");
            LogUtil.printException(e);
            return true;
        }
    }

    public boolean saveCityProviders(List<CityProvider> list) {
        try {
            db.c((List<?>) list);
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "城市运营商映射表失败");
            LogUtil.printException(e);
            return true;
        }
    }

    public boolean saveAllTvChannels(List<TvChannel> list) {
        try {
            db.c((List<?>) list);
            return true;
        } catch (Exception e) {
            LogUtil.printException(e);
            return false;
        }
    }

    public TvChannel getChannelById(int i) {
        try {
            return (TvChannel) db.b(TvChannel.class, Integer.valueOf(i));
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public <T> List<T> getAllRecords(Selector selector) {
        try {
            return db.b(selector);
        } catch (Exception e) {
            LogUtil.e(TAG, "getAllRecords failed!" + e);
            return null;
        }
    }

    public City getCityByName(String str) {
        try {
            Cursor b = db.b("SELECT * FROM tb_city WHERE length(trim(city_name))>0 AND '" + str + "' LIKE trim(city_name) || '%';");
            if (b == null || b.getCount() <= 0) {
                b.close();
                return null;
            }
            b.moveToFirst();
            City city = new City();
            city.setCity_id(b.getInt(b.getColumnIndex("city_id")));
            city.setCity_name(b.getString(b.getColumnIndex("city_name")));
            city.setId(b.getInt(b.getColumnIndex("id")));
            city.setProvince_id(b.getInt(b.getColumnIndex("province_id")));
            b.close();
            return city;
        } catch (DbException unused) {
            return null;
        }
    }

    public Province getProvinceByName(String str) {
        try {
            Cursor b = db.b("SELECT * FROM tb_province WHERE length(trim(province_name))>0 AND '" + str + "' LIKE trim(province_name) || '%';");
            if (b == null || b.getCount() <= 0) {
                b.close();
                return null;
            }
            b.moveToFirst();
            Province province = new Province();
            province.setProvince_name(b.getString(b.getColumnIndex(Tags.MiHome.PROVINCE_NAME)));
            province.setId(b.getInt(b.getColumnIndex("id")));
            province.setProvince_id(b.getInt(b.getColumnIndex("province_id")));
            b.close();
            return province;
        } catch (DbException unused) {
            return null;
        }
    }

    public Cursor execQuery(String str) {
        try {
            return db.b(str);
        } catch (DbException unused) {
            return null;
        }
    }

    public void saveOrUpdataAll(List<Brand> list) {
        db.b(true);
        beginTransaction();
        try {
            db.a((List<?>) list);
        } catch (DbException e) {
            e.printStackTrace();
        }
        endTransaction();
    }

    public void beginTransaction() {
        try {
            Method declaredMethod = DbUtils.class.getDeclaredMethod("beginTransaction", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(db, new Object[0]);
        } catch (Exception e) {
            LogUtil.e(TAG, "beginTransaction got an exception!" + e.getClass());
            e.printStackTrace();
        }
    }

    public void endTransaction() {
        Class<DbUtils> cls = DbUtils.class;
        try {
            Method declaredMethod = cls.getDeclaredMethod("setTransactionSuccessful", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(db, new Object[0]);
            Method declaredMethod2 = cls.getDeclaredMethod("endTransaction", new Class[0]);
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(db, new Object[0]);
        } catch (Exception e) {
            LogUtil.e(TAG, "endTransaction got an exception!" + e.getClass());
            e.printStackTrace();
        }
    }
}
