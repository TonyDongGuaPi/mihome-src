package cn.com.fmsh.nfcos.client.service.localdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.com.fmsh.nfcos.client.service.constants.Constants;
import cn.com.fmsh.tsm.business.LocalDataHandler;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.Notice;
import cn.com.fmsh.tsm.business.bean.StationInfo;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import com.taobao.weex.el.parse.Operators;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements LocalDataHandler {
    private static final String COLUMN_STATIONID = "id";
    private static final String COLUMN_STATION_NAME = "name";
    private static final String COLUMN_TIMESTAMP = "timestamp";
    private static final String COLUMN_VERSION = "vid";
    private static final String COLUNM_STATION_ENAME = "ename";
    private static final String DEFAULT_STATION = "未知站名";
    private static final String TABLE_STATION = "tstation";
    private static final String TABLE_VERSION = "tversion";
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private void createTable(SQLiteDatabase sQLiteDatabase) {
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DBHelper(Context context, int i) {
        super(context, Constants.DataBase.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, i);
    }

    private void createTablePtCard(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE");
        stringBuffer.append(" ");
        stringBuffer.append(Constants.DataBase.TableName.FM_NFC_PTCARD);
        stringBuffer.append(Operators.BRACKET_START_STR);
        stringBuffer.append("ID");
        stringBuffer.append(" ");
        stringBuffer.append("INTEGER PRIMARY KEY,");
        stringBuffer.append(Constants.DataBase.Column4Card.COLUMN_FACEID);
        stringBuffer.append(" ");
        stringBuffer.append("text,");
        stringBuffer.append(Constants.DataBase.Column4Card.COLUMN_AMOUNT);
        stringBuffer.append(" ");
        stringBuffer.append("INTEGER,");
        stringBuffer.append(Constants.DataBase.Column4Card.COLUMN_BALANCE);
        stringBuffer.append(" ");
        stringBuffer.append("INTEGER,");
        stringBuffer.append(Constants.DataBase.Column4Card.COLUMN_DATETIME);
        stringBuffer.append(" ");
        stringBuffer.append("text,");
        stringBuffer.append(Constants.DataBase.Column4Card.COLUMN_TERMINAL_TRADETYPE);
        stringBuffer.append(" ");
        stringBuffer.append("INTEGER,");
        stringBuffer.append(Constants.DataBase.Column4Card.COLUMN_DEVICE_NO);
        stringBuffer.append(" ");
        stringBuffer.append("text,");
        stringBuffer.append(");");
        sQLiteDatabase.execSQL(stringBuffer.toString());
    }

    private void createTableRecharge(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE");
        stringBuffer.append(" ");
        stringBuffer.append(Constants.DataBase.TableName.FM_RECHARGE_RECORD);
        stringBuffer.append(Operators.BRACKET_START_STR);
        stringBuffer.append(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_CARD_NO);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT PRIMARY KEY,");
        stringBuffer.append(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_ORDER_NO);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TRADE_TIME);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_SERIAL_NO);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TAC);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append("STATUS");
        stringBuffer.append(" ");
        stringBuffer.append("INTEGER,");
        stringBuffer.append(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TERMINAL_NO);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(");");
        sQLiteDatabase.execSQL(stringBuffer.toString());
    }

    private void createTableNotice(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE");
        stringBuffer.append(" ");
        stringBuffer.append(Constants.DataBase.TableName.FM_NOTICE);
        stringBuffer.append(Operators.BRACKET_START_STR);
        stringBuffer.append("ID");
        stringBuffer.append(" ");
        stringBuffer.append("INTEGER PRIMARY KEY,");
        stringBuffer.append("TITLE");
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(Constants.DataBase.Column4Notice.COLUMN_NOTICE_BODY);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(Constants.DataBase.Column4Notice.COLUMN_NOTICE_S_DATE);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(Constants.DataBase.Column4Notice.COLUMN_NOTICE_E_DATE);
        stringBuffer.append(" ");
        stringBuffer.append("TEXT,");
        stringBuffer.append(");");
        sQLiteDatabase.execSQL(stringBuffer.toString());
    }

    public void insertHistory(String str, int i, int i2, String str2, int i3, int i4, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_FACEID, str);
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_AMOUNT, Integer.valueOf(i));
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_BALANCE, Integer.valueOf(i2));
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_DATETIME, str2);
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_TERMINAL_TRADETYPE, Integer.valueOf(i3));
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_ORI_TRADETYPE, Integer.valueOf(i4));
        contentValues.put(Constants.DataBase.Column4Card.COLUMN_DEVICE_NO, str3);
        writableDatabase.insert(Constants.DataBase.TableName.FM_NFC_PTCARD, (String) null, contentValues);
        writableDatabase.close();
    }

    public void deleteHistoryByFaceId(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Constants.DataBase.TableName.FM_NFC_PTCARD, "FACEID = '" + str + "'", (String[]) null);
        writableDatabase.close();
    }

    public void deleteHistoryById(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Constants.DataBase.TableName.FM_NFC_PTCARD, "ID= '" + str + "'", (String[]) null);
        writableDatabase.close();
    }

    public String[] findAllFaceId() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select distinct FACEID from FM_NFC_PTCARD", (String[]) null);
        ArrayList arrayList = new ArrayList();
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            String string = rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Card.COLUMN_FACEID));
            if (string != null) {
                arrayList.add(string);
            }
            rawQuery.moveToNext();
        }
        readableDatabase.close();
        if (arrayList.size() == 0) {
            return new String[0];
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public void insertTrade(String str, String str2, String str3, String str4, String str5, int i, String str6) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_CARD_NO, str);
        contentValues.put(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_ORDER_NO, str2);
        contentValues.put(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TRADE_TIME, str3);
        contentValues.put(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_SERIAL_NO, str4);
        contentValues.put(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TAC, str5);
        contentValues.put("STATUS", Integer.valueOf(i));
        contentValues.put(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TERMINAL_NO, str6);
        writableDatabase.insert(Constants.DataBase.TableName.FM_RECHARGE_RECORD, (String) null, contentValues);
        writableDatabase.close();
    }

    public void deleteTradeByOrder(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Constants.DataBase.TableName.FM_RECHARGE_RECORD, "ORDER_NO = '" + str + "'", (String[]) null);
        writableDatabase.close();
    }

    public BusinessOrder findRechargeRecordByInfo(String str, String str2, String str3, String str4) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from ");
        stringBuffer.append(Constants.DataBase.TableName.FM_RECHARGE_RECORD);
        stringBuffer.append(" where SERIAL_NO = '");
        stringBuffer.append(str2);
        stringBuffer.append("' and TRADE_TIME = '");
        stringBuffer.append(str3);
        stringBuffer.append("' and CARD_NO = '");
        stringBuffer.append(str);
        stringBuffer.append("' and TERMINAL_NO = '");
        stringBuffer.append(str4);
        stringBuffer.append("'");
        BusinessOrder businessOrder = null;
        Cursor rawQuery = readableDatabase.rawQuery(stringBuffer.toString(), (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            businessOrder = new BusinessOrder();
            businessOrder.setCardNo(FM_Bytes.hexStringToBytes(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_CARD_NO))));
            businessOrder.setOrder(FM_Bytes.hexStringToBytes(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_ORDER_NO))));
            String string = rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TRADE_TIME));
            if (string.length() >= 8) {
                businessOrder.setTradeDate(string.substring(0, 8));
            }
            if (string.length() >= 14) {
                businessOrder.setTradeTime(string.substring(8, 14));
            }
            String string2 = rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_SERIAL_NO));
            if (string2 != null && string2.length() > 1) {
                businessOrder.setSerialNo(FM_Bytes.bytesToInt(FM_Bytes.hexStringToBytes(string2)));
            }
            businessOrder.setTac(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TAC)));
            businessOrder.setTradeState(EnumOrderStatus.getOrderStatus4ID(rawQuery.getInt(rawQuery.getColumnIndex("STATUS"))));
            businessOrder.setTerminalNo(FM_Bytes.hexStringToBytes(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TERMINAL_NO))));
            rawQuery.moveToNext();
        }
        readableDatabase.close();
        return businessOrder;
    }

    public BusinessOrder findRechargeRecordByOrder(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from ");
        stringBuffer.append(Constants.DataBase.TableName.FM_RECHARGE_RECORD);
        stringBuffer.append(" where  ORDER_NO  = '");
        stringBuffer.append(str);
        stringBuffer.append("'");
        String stringBuffer2 = stringBuffer.toString();
        BusinessOrder businessOrder = null;
        Cursor rawQuery = readableDatabase.rawQuery(stringBuffer2, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            businessOrder = new BusinessOrder();
            businessOrder.setCardNo(FM_Bytes.hexStringToBytes(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_CARD_NO))));
            businessOrder.setOrder(FM_Bytes.hexStringToBytes(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_ORDER_NO))));
            String string = rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TRADE_TIME));
            if (string.length() >= 8) {
                businessOrder.setTradeDate(string.substring(0, 8));
            }
            if (string.length() >= 14) {
                businessOrder.setTradeTime(string.substring(8, 14));
            }
            String string2 = rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_SERIAL_NO));
            if (string2 != null && string2.length() > 1) {
                businessOrder.setSerialNo(FM_Bytes.bytesToInt(FM_Bytes.hexStringToBytes(string2)));
            }
            businessOrder.setTac(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TAC)));
            businessOrder.setTradeState(EnumOrderStatus.getOrderStatus4ID(rawQuery.getInt(rawQuery.getColumnIndex("STATUS"))));
            businessOrder.setTerminalNo(FM_Bytes.hexStringToBytes(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Recharge.COLUMN_RECHARGE_TERMINAL_NO))));
            rawQuery.moveToNext();
        }
        readableDatabase.close();
        return businessOrder;
    }

    public void insertNotice(Notice notice) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", Integer.valueOf(notice.getNo()));
        contentValues.put("TITLE", notice.getTitle());
        contentValues.put(Constants.DataBase.Column4Notice.COLUMN_NOTICE_BODY, notice.getContent());
        contentValues.put(Constants.DataBase.Column4Notice.COLUMN_NOTICE_S_DATE, notice.getStartDate());
        contentValues.put(Constants.DataBase.Column4Notice.COLUMN_NOTICE_E_DATE, notice.getEndDate());
        writableDatabase.insert(Constants.DataBase.TableName.FM_NOTICE, (String) null, contentValues);
        writableDatabase.close();
    }

    public ArrayList<Notice> findAllNotice() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from ");
        stringBuffer.append(Constants.DataBase.TableName.FM_NOTICE);
        stringBuffer.append(" order by ");
        stringBuffer.append("ID");
        stringBuffer.append(" desc ");
        Cursor rawQuery = readableDatabase.rawQuery(stringBuffer.toString(), (String[]) null);
        ArrayList<Notice> arrayList = new ArrayList<>();
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Notice notice = new Notice();
            notice.setNo(rawQuery.getInt(rawQuery.getColumnIndex("ID")));
            notice.setTitle(rawQuery.getString(rawQuery.getColumnIndex("TITLE")));
            notice.setContent(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Notice.COLUMN_NOTICE_BODY)));
            notice.setStartDate(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Notice.COLUMN_NOTICE_S_DATE)));
            notice.setEndDate(rawQuery.getString(rawQuery.getColumnIndex(Constants.DataBase.Column4Notice.COLUMN_NOTICE_E_DATE)));
            arrayList.add(notice);
            rawQuery.moveToNext();
        }
        readableDatabase.close();
        return arrayList;
    }

    public void deleteNotiecById(long j) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Constants.DataBase.TableName.FM_NOTICE, "ID= '" + j + "'", (String[]) null);
        writableDatabase.close();
    }

    public int getMaxNoticeId() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from ");
        stringBuffer.append(Constants.DataBase.TableName.FM_NOTICE);
        stringBuffer.append(" order by ");
        stringBuffer.append("ID");
        stringBuffer.append(" desc ");
        Cursor rawQuery = writableDatabase.rawQuery(stringBuffer.toString(), (String[]) null);
        rawQuery.moveToFirst();
        int i = !rawQuery.isAfterLast() ? rawQuery.getInt(rawQuery.getColumnIndex("ID")) : 0;
        writableDatabase.close();
        return i;
    }

    public String getVersion4StationInfo() {
        System.out.println("DBHelper.getVersion4StationInfo");
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteDatabase sQLiteDatabase = readableDatabase;
        Cursor query = sQLiteDatabase.query(TABLE_VERSION, new String[]{COLUMN_VERSION, "timestamp"}, "", new String[0], (String) null, (String) null, (String) null);
        String str = "";
        if (query.moveToNext()) {
            str = query.getString(query.getColumnIndex(COLUMN_VERSION));
        }
        readableDatabase.close();
        PrintStream printStream = System.out;
        printStream.println("DBHelper.getVersion4StationInfo:" + str);
        return str;
    }

    public int updateStationInfo(String str, List<StationInfo> list) {
        PrintStream printStream = System.out;
        printStream.println("DBHelper.updateStationInfo(" + str + "," + list.size() + Operators.BRACKET_END_STR);
        if (deleteStations() && insertStations(list) && updateVersion(str)) {
            return 0;
        }
        return -1;
    }

    private boolean deleteStations() {
        System.out.println("DBHelper.deleteStations");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        int delete = writableDatabase.delete(TABLE_STATION, (String) null, (String[]) null);
        writableDatabase.close();
        PrintStream printStream = System.out;
        printStream.println("DBHelper.deleteStations:" + delete);
        return true;
    }

    private boolean insertStations(List<StationInfo> list) {
        PrintStream printStream = System.out;
        printStream.println("DBHelper.insertStations.size(" + list.size() + Operators.BRACKET_END_STR);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        for (StationInfo next : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", next.getId());
            contentValues.put("name", next.getName());
            contentValues.put(COLUNM_STATION_ENAME, next.getEname());
            long insert = writableDatabase.insert(TABLE_STATION, (String) null, contentValues);
            PrintStream printStream2 = System.out;
            printStream2.println("UPDATE(" + insert + "):" + next.getId() + "|" + next.getName() + "|" + next.getEname());
        }
        writableDatabase.close();
        return true;
    }

    private boolean updateVersion(String str) {
        PrintStream printStream = System.out;
        printStream.println("DBHelper.updateVersion(" + str + Operators.BRACKET_END_STR);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Date date = new Date();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_VERSION, str);
        contentValues.put("timestamp", dateFormatter.format(date));
        writableDatabase.update(TABLE_VERSION, contentValues, (String) null, (String[]) null);
        writableDatabase.close();
        return true;
    }

    public String getStation(String str) {
        String str2;
        int parseInt;
        System.out.println("DBHelper.getStation(" + str + Operators.BRACKET_END_STR);
        if (str.length() <= 2 || (parseInt = Integer.parseInt(str.substring(0, 2))) <= 0) {
            str2 = null;
        } else {
            str2 = parseInt + "号线 ";
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor query = readableDatabase.query(TABLE_STATION, new String[]{"id", "name"}, "id=?", new String[]{str}, (String) null, (String) null, (String) null);
        if (query.moveToNext()) {
            str2 = String.valueOf(str2) + query.getString(query.getColumnIndex("name"));
        }
        readableDatabase.close();
        if (str2 == null || str2.length() == 0) {
            str2 = DEFAULT_STATION;
        }
        System.out.println("DBHelper.getStation:" + str2);
        return str2;
    }
}
