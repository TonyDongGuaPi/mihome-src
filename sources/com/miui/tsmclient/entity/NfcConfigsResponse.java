package com.miui.tsmclient.entity;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.miui.tsmclient.common.data.CommonResponseInfo;
import com.miui.tsmclient.common.net.HttpClient;
import com.miui.tsmclient.net.request.NfcConfigsRequest;
import com.miui.tsmclient.util.IOUtils;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class NfcConfigsResponse extends CommonResponseInfo {
    private static final String FILE_NAME = "nfc_configs";
    private static final int MILLISECOND_OF_HOUR = 3600000;
    @SerializedName("data")
    private NfcConfigs mNfcConfigs;

    public NfcConfigs getNfcConfigs() {
        return this.mNfcConfigs;
    }

    public static class NfcConfigs {
        @SerializedName("inAppConfig")
        private InAppConfig mInAppConfig;
        @SerializedName("swipeCardConfig")
        private List<SwipeCardConfig> mSwipeCardConfigs;

        public String toString() {
            return new Gson().toJson((Object) this);
        }

        public static NfcConfigs fetchNfcConfigFromServer(Context context) {
            NfcConfigsResponse nfcConfigsResponse;
            long currentTimeMillis = System.currentTimeMillis();
            String str = "";
            try {
                str = new CardInfo(CardInfo.CARD_TYPE_DUMMY).getTerminal().getCPLC();
            } catch (IOException e) {
                LogUtils.e("failed to fetch nfc configs from server", e);
            } catch (InterruptedException unused) {
                LogUtils.d("failed to fetch nfc configs from server for operation has been interrupted");
            }
            if (TextUtils.isEmpty(str)) {
                LogUtils.d("failed to fetch nfc configs from server, cplc is empty");
                return null;
            }
            try {
                nfcConfigsResponse = (NfcConfigsResponse) HttpClient.getInstance(context).execute(new NfcConfigsRequest(str)).getResult();
            } catch (IOException e2) {
                LogUtils.e("fetchNfcConfigFromServer Exception occurred", e2);
                nfcConfigsResponse = null;
            }
            LogUtils.v("fetchNfcConfigFromServer time = " + (System.currentTimeMillis() - currentTimeMillis));
            if (nfcConfigsResponse == null) {
                return null;
            }
            NfcConfigs nfcConfigs = nfcConfigsResponse.getNfcConfigs();
            PrefUtils.putLong(context, PrefUtils.PREF_KEY_FETCH_NFC_CONFIG_TIME, System.currentTimeMillis());
            PrefUtils.putString(context, PrefUtils.PREF_KEY_SWIPE_CARD_CONFIG, nfcConfigs.getSwipeCardConfigs());
            nfcConfigs.saveToFile(context);
            return nfcConfigs;
        }

        public static NfcConfigs createConfigFromFile(Context context) {
            FileInputStream fileInputStream;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                fileInputStream = context.openFileInput(NfcConfigsResponse.FILE_NAME);
                try {
                    IOUtils.copy(fileInputStream, byteArrayOutputStream);
                    String str = new String(byteArrayOutputStream.toByteArray());
                    LogUtils.d("string from nfc config file: " + str);
                    NfcConfigs nfcConfigs = (NfcConfigs) new Gson().fromJson(str, NfcConfigs.class);
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    IOUtils.closeQuietly((InputStream) fileInputStream);
                    return nfcConfigs;
                } catch (FileNotFoundException unused) {
                    LogUtils.w("failed to open file: nfc_configs");
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    IOUtils.closeQuietly((InputStream) fileInputStream);
                    return null;
                } catch (IOException unused2) {
                    LogUtils.w("failed to write file: nfc_configs");
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    IOUtils.closeQuietly((InputStream) fileInputStream);
                    return null;
                }
            } catch (FileNotFoundException unused3) {
                fileInputStream = null;
                LogUtils.w("failed to open file: nfc_configs");
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return null;
            } catch (IOException unused4) {
                fileInputStream = null;
                LogUtils.w("failed to write file: nfc_configs");
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                throw th;
            }
        }

        public String getSwipeCardConfigs() {
            if (this.mSwipeCardConfigs != null) {
                return new Gson().toJson((Object) this.mSwipeCardConfigs);
            }
            return null;
        }

        public InAppConfig getInAppConfig() {
            return this.mInAppConfig;
        }

        public boolean isInAppConfigExpired(Context context) {
            return this.mInAppConfig == null || this.mInAppConfig.isExpired(context);
        }

        private void saveToFile(Context context) {
            String nfcConfigs = toString();
            if (!TextUtils.isEmpty(nfcConfigs)) {
                FileOutputStream fileOutputStream = null;
                try {
                    FileOutputStream openFileOutput = context.openFileOutput(NfcConfigsResponse.FILE_NAME, 0);
                    try {
                        openFileOutput.write(nfcConfigs.getBytes());
                        IOUtils.closeQuietly((OutputStream) openFileOutput);
                    } catch (FileNotFoundException unused) {
                        fileOutputStream = openFileOutput;
                        LogUtils.w("failed to open file: nfc_configs");
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                    } catch (IOException unused2) {
                        fileOutputStream = openFileOutput;
                        try {
                            LogUtils.w("failed to write file: nfc_configs");
                            IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        } catch (Throwable th) {
                            th = th;
                            IOUtils.closeQuietly((OutputStream) fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = openFileOutput;
                        IOUtils.closeQuietly((OutputStream) fileOutputStream);
                        throw th;
                    }
                } catch (FileNotFoundException unused3) {
                    LogUtils.w("failed to open file: nfc_configs");
                    IOUtils.closeQuietly((OutputStream) fileOutputStream);
                } catch (IOException unused4) {
                    LogUtils.w("failed to write file: nfc_configs");
                    IOUtils.closeQuietly((OutputStream) fileOutputStream);
                }
            }
        }
    }

    public static class SwipeCardConfig {
        @SerializedName("cardType")
        private String mCardType;
        @SerializedName("needKeyguardProtect")
        private boolean mNeedKeyguardProtect;
        @SerializedName("validHour")
        private int mValidHour;

        public String toString() {
            return new Gson().toJson((Object) this);
        }

        public boolean isExpired(Context context) {
            if (System.currentTimeMillis() <= PrefUtils.getLong(context, PrefUtils.PREF_KEY_FETCH_NFC_CONFIG_TIME, 0) + ((long) (this.mValidHour * 3600000))) {
                return false;
            }
            LogUtils.d("nfc config has expired");
            return true;
        }
    }

    public static class InAppConfig {
        @SerializedName("support")
        private boolean mIsSupportInappPay;
        @SerializedName("supportIssue")
        private boolean mIsSupportIssue;
        @SerializedName("maxCardNumber")
        private int mMaxCardQuantity;
        @SerializedName("validHour")
        private int mValidHour;

        public String toString() {
            return new Gson().toJson((Object) this);
        }

        public boolean isExpired(Context context) {
            if (System.currentTimeMillis() <= PrefUtils.getLong(context, PrefUtils.PREF_KEY_FETCH_NFC_CONFIG_TIME, 0) + ((long) (this.mValidHour * 3600000))) {
                return false;
            }
            LogUtils.d("nfc config has expired");
            return true;
        }

        public boolean isSupportInappPay() {
            return this.mIsSupportInappPay;
        }

        public boolean isSupportInappIssue() {
            return this.mIsSupportIssue;
        }
    }
}
