package com.xiaomi.mobilestats.common;

import com.taobao.weex.annotation.JSMethod;
import java.io.File;
import java.util.Comparator;

public class FileNameComparator implements Comparator {
    public int compare(File file, File file2) {
        String name = file.getName();
        String substring = name.substring(0, name.indexOf(JSMethod.NOT_SET));
        String name2 = file2.getName();
        try {
            return Long.valueOf(Long.parseLong(substring)).longValue() > Long.valueOf(Long.parseLong(name2.substring(0, name2.indexOf(JSMethod.NOT_SET)))).longValue() ? 1 : -1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
