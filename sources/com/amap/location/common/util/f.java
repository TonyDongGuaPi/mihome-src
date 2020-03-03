package com.amap.location.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.amap.location.common.model.CellStatus;
import java.util.List;

public class f {
    public static int a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return -1;
            }
            int type = activeNetworkInfo.getType();
            if (type == 1 || type == 0) {
                return type;
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static long a(String str) {
        long j;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i = 0;
        long j2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            long charAt = (long) str.charAt(length);
            if (charAt < 48 || charAt > 57) {
                long j3 = 97;
                if (charAt < 97 || charAt > 102) {
                    j3 = 65;
                    if (charAt < 65 || charAt > 70) {
                        if (!(charAt == 58 || charAt == 124)) {
                            return 0;
                        }
                    }
                }
                j = (charAt - j3) + 10;
            } else {
                j = charAt - 48;
            }
            j2 += j << i;
            i += 4;
        }
        if (i != 48) {
            return 0;
        }
        return j2;
    }

    public static String a(long j) {
        if (j < 0 || j > 281474976710655L) {
            return null;
        }
        return b.a(b.a(j, 6, true), (String) null, ":");
    }

    public static void a(CellStatus.HistoryCell historyCell, List<CellStatus.HistoryCell> list, int i) {
        if (historyCell != null && list != null) {
            int size = list.size();
            if (size == 0) {
                list.add(historyCell);
                return;
            }
            long j = Long.MAX_VALUE;
            int i2 = 0;
            int i3 = -1;
            int i4 = -1;
            while (true) {
                if (i2 >= size) {
                    i3 = i4;
                    break;
                }
                CellStatus.HistoryCell historyCell2 = list.get(i2);
                if (!historyCell.equals(historyCell2)) {
                    j = Math.min(j, historyCell2.h);
                    if (j == historyCell2.h) {
                        i4 = i2;
                    }
                    i2++;
                } else if (historyCell.b != historyCell2.b) {
                    historyCell2.h = historyCell.h;
                    historyCell2.b = historyCell.b;
                }
            }
            if (i3 < 0) {
                return;
            }
            if (size < i) {
                list.add(historyCell);
            } else if (historyCell.h > j && i3 < size) {
                list.remove(i3);
                list.add(historyCell);
            }
        }
    }

    public static boolean a(int i) {
        return i >= 0 && i <= 65535;
    }

    public static boolean b(int i) {
        return i >= 0 && i <= 268435455;
    }

    public static boolean c(int i) {
        return i > 0 && i <= 32767;
    }

    public static boolean d(int i) {
        return i >= 0 && i <= 65535;
    }

    public static boolean e(int i) {
        return i >= 0 && i <= 65535;
    }
}
