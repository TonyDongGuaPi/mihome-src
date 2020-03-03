package com.xiaomi.smarthome.framework.page.rndebug;

import android.content.Context;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RnDebugFileUtil {
    public static List<JSONObject> a(Context context) {
        ArrayList arrayList = new ArrayList();
        String b = b(context);
        File file = new File(b);
        if (!file.exists()) {
            RnPluginLog.b(b + " is not exist!");
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                arrayList2.add(readLine);
            }
            bufferedReader.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            RnPluginLog.b(e.toString());
        } catch (IOException e2) {
            RnPluginLog.b(e2.toString());
        }
        for (int i = 0; i < arrayList2.size(); i++) {
            RnPluginLog.a((String) arrayList2.get(i));
            try {
                arrayList.add(new JSONObject((String) arrayList2.get(i)));
            } catch (JSONException e3) {
                RnPluginLog.b(e3.toString());
            }
        }
        return arrayList;
    }

    public static void a(Context context, List<JSONObject> list) {
        if (list == null) {
            RnPluginLog.b("rn debug list data is null");
            return;
        }
        File file = new File(b(context));
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            RnPluginLog.b(e.toString());
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int i = 0; i < list.size(); i++) {
                bufferedWriter.write(list.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (FileNotFoundException e2) {
            RnPluginLog.b(e2.toString());
        } catch (IOException e3) {
            RnPluginLog.b(e3.toString());
        }
    }

    public static String b(Context context) {
        return context.getFilesDir() + File.separator + "rn_debug_list_data.txt";
    }

    public static JSONObject a(String str) {
        for (JSONObject next : a(SHApplication.getAppContext())) {
            String optString = next.optString(RnDebugConstant.b);
            boolean optBoolean = next.optBoolean(RnDebugConstant.c);
            if (str.equals(optString) && optBoolean) {
                return next;
            }
        }
        return null;
    }
}
