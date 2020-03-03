package com.xiaomi.smarthome.shop.analytics;

import android.text.TextUtils;
import com.xiaomi.smarthome.miio.Miio;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Stat {
    public static String TAG = "ScriptEngine";
    private ArrayList<Node> actionStacks = new ArrayList<>();
    private Node currentView = null;
    private String header = "";
    private TimerTask interval = null;
    private ConcurrentLinkedQueue<Node> pipes = new ConcurrentLinkedQueue<>();
    private String session = "";
    private File sessionFile = null;
    private Timer timer = null;

    private class Node {
        String area;
        String event;
        String id;
        String iid;
        String method;
        String page;
        String ref;
        String session;
        long spend;
        long time;

        public Node(String str, String str2, String str3, long j, long j2, String str4, String str5) {
            this.page = str;
            this.id = str2;
            this.event = str3;
            this.time = j;
            this.spend = j2;
            this.ref = str4;
            this.session = str5;
        }
    }

    private class Session {
        String key;
        long time;

        private Session() {
        }
    }

    public long unixtime() {
        return System.currentTimeMillis();
    }

    public void updateHeader(String str, String str2) {
        this.header = str2;
    }

    private String buildRef(Node node) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        if (TextUtils.isEmpty(node.ref)) {
            str = "";
        } else {
            str = node.ref + "/";
        }
        if (TextUtils.isEmpty(node.id)) {
            str2 = "";
        } else {
            str2 = "&id=" + node.id;
        }
        if (TextUtils.isEmpty(node.event)) {
            str3 = "";
        } else {
            str3 = "&event=" + node.event;
        }
        if (node.time == 0) {
            str4 = "";
        } else {
            str4 = "&time=" + String.valueOf(node.time);
        }
        if (node.spend == 0) {
            str5 = "";
        } else {
            str5 = "&spend=" + String.valueOf(node.spend);
        }
        if (TextUtils.isEmpty(node.area)) {
            str6 = "";
        } else {
            str6 = "&area=" + node.area;
        }
        if (TextUtils.isEmpty(node.iid)) {
            str7 = "";
        } else {
            str7 = "&iid=" + node.iid;
        }
        if (TextUtils.isEmpty(node.method)) {
            str8 = "";
        } else {
            str8 = "&method=" + node.method;
        }
        return str + node.page + "?v=2" + str2 + str3 + str4 + str5 + str6 + str7 + str8;
    }

    private String getRefer() {
        StringBuilder sb = new StringBuilder();
        int size = this.actionStacks.size();
        for (int i = 0; i < size; i++) {
            sb.append(buildRef(this.actionStacks.get(i)));
            sb.append("/");
        }
        String sb2 = sb.toString();
        return sb2.substring(0, sb2.length() - 1);
    }

    public String Request(String str, String str2, String str3) {
        String str4;
        String str5 = str;
        String str6 = str2;
        long unixtime = unixtime();
        String str7 = str3 == null ? "" : str3;
        try {
            str4 = URLEncoder.encode(str7, "UTF-8");
        } catch (Exception unused) {
            str4 = str7;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -719524261:
                if (str5.equals(MIOTStat.PAYSUCCESS)) {
                    c = 3;
                    break;
                }
                break;
            case -68911194:
                if (str5.equals(MIOTStat.PAYFAIL)) {
                    c = 4;
                    break;
                }
                break;
            case 2634405:
                if (str5.equals(MIOTStat.VIEW)) {
                    c = 0;
                    break;
                }
                break;
            case 80013087:
                if (str5.equals(MIOTStat.TOUCH)) {
                    c = 2;
                    break;
                }
                break;
            case 1172216822:
                if (str5.equals(MIOTStat.VIEWEND)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Node node = new Node(str6.replace("com.xiaomi.smarthome.shop.", "").replace("com.xiaomi.smarthome.", ""), str4, str, unixtime, 0, "", this.session);
                this.currentView = node;
                this.pipes.offer(node);
                break;
            case 1:
                if (this.currentView != null) {
                    this.pipes.offer(new Node(this.currentView.page, this.currentView.id, MIOTStat.VIEWEND, unixtime, unixtime - this.currentView.time, "", this.session));
                    break;
                }
                break;
            case 2:
                if (this.currentView != null) {
                    Node node2 = r0;
                    Node node3 = new Node(this.currentView.page, this.currentView.id, str, unixtime, 0, "", this.session);
                    node2.area = str6;
                    node2.iid = str4;
                    if (!TextUtils.equals(str6, MIOTStat.VISIT)) {
                        int size = this.actionStacks.size();
                        String str8 = this.currentView.page;
                        int i = 0;
                        while (true) {
                            if (i < size) {
                                if (TextUtils.equals(this.actionStacks.get(i).page, str8)) {
                                    this.actionStacks = new ArrayList<>(this.actionStacks.subList(0, i));
                                } else {
                                    i++;
                                }
                            }
                        }
                        this.actionStacks.add(node2);
                    }
                    this.pipes.offer(node2);
                    break;
                } else {
                    Miio.h(TAG, "currentView is null");
                    break;
                }
            case 3:
            case 4:
                if (this.currentView != null) {
                    Node node4 = new Node(this.currentView.page, this.currentView.id, str, unixtime, 0, getRefer(), this.session);
                    node4.method = str6;
                    node4.iid = str4;
                    this.pipes.offer(node4);
                    break;
                } else {
                    Miio.h(TAG, "currentView is null");
                    break;
                }
        }
        return "RCV:{e:" + str5 + ",k:" + str6 + ",v:" + str4 + "}";
    }
}
