package com.miui.tsmclient.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseConstants {
    public static final String AUTHORITY = ProviderAuthorities.AUTHORITY;
    public static final String CACHE_KEY_PRODUCT_PREFFIX = "product_";
    public static final Uri CONTENT_URI = ProviderAuthorities.CONTENT_URI;
    public static final Uri CONTENT_URI_BANK_BIN = Uri.parse(CONTENT_URI + "/" + TABLE_BANK_BIN);
    public static final Uri CONTENT_URI_BANK_INFO = Uri.parse(CONTENT_URI + "/" + TABLE_BANK_INFO);
    public static final Uri CONTENT_URI_CACHE = Uri.parse(CONTENT_URI + "/" + "cache");
    public static final Uri CONTENT_URI_DATA_STAT = Uri.parse(CONTENT_URI + "/" + TABLE_DATA_STAT);
    public static final Uri CONTENT_URI_NO_PROMPT_BULLETIN = Uri.parse(CONTENT_URI + "/" + TABLE_NO_PROMPT_BULLETIN);
    public static final Uri CONTENT_URI_TRANS_CARD_INFO = Uri.parse(CONTENT_URI + "/" + TABLE_TRANS_CARD_INFO);
    public static final String PRODUCT_NAME = "product_name";
    public static final String[] PROJECTION_BANK_NAME_AND_LOGO = {"bank_info.bank_name", BankInfoTable.COLUMN_BANK_LOGO};
    public static final String[] PROJECTION_CACHE = {"key", "value"};
    public static final String[] PROJECTION_DATA_STAT_INFO = {DataStatTable.COLUMN_DATA_ID, DataStatTable.COLUMN_DATA_VALUE, DataStatTable.COLUMN_DATA_TIME};
    public static final String[] PROJECTION_TRANS_CARD_INFO = {"card_name", TransCardInfoTable.COLUMN_CARD_LOGO, TransCardInfoTable.COLUMN_ISSUABLE, TransCardInfoTable.COLUMN_CARD_CODE};
    public static final Uri PUBLIC_CONTENT_URI = ProviderAuthorities.PUBLIC_CONTENT_URI;
    public static final String TABLE_BANK_BIN = "bank_bin";
    public static final String TABLE_BANK_INFO = "bank_info";
    public static final String TABLE_CACHE = "cache";
    public static final String TABLE_DATA_STAT = "data_stat";
    public static final String TABLE_NO_PROMPT_BULLETIN = "no_prompt_bulletin";
    public static final String TABLE_TRANS_CARD_INFO = "trans_card_info";

    public static final class BankBinTable implements BaseColumns {
        public static final String COLUMN_BANK_NAME = "bank_name";
        public static final String COLUMN_BIN_CODE = "bin_code";
        public static final String COLUMN_CARD_TYPE = "card_type";
    }

    public static final class BankInfoTable implements BaseColumns {
        public static final String COLUMN_BANK_CODE = "bank_code";
        public static final String COLUMN_BANK_ID = "bank_id";
        public static final String COLUMN_BANK_LOGO = "bank_logo";
        public static final String COLUMN_BANK_NAME = "bank_name";
    }

    public static final class CacheTable implements BaseColumns {
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_VALUE = "value";
    }

    public static final class DataStatTable implements BaseColumns {
        public static final String COLUMN_DATA_ID = "data_id";
        public static final String COLUMN_DATA_TIME = "data_time";
        public static final String COLUMN_DATA_VALUE = "data_value";
    }

    public static final class NoPromptBulletinTable implements BaseColumns {
        public static final String COLUMN_BULLETIN_ID = "bulletin_id";
    }

    public static final class TransCardInfoTable implements BaseColumns {
        public static final String COLUMN_CARD_CODE = "card_code";
        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_CARD_LOGO = "card_logo";
        public static final String COLUMN_CARD_NAME = "card_name";
        public static final String COLUMN_ISSUABLE = "issuable";
    }
}
