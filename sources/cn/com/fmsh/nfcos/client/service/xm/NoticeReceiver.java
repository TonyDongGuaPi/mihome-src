package cn.com.fmsh.nfcos.client.service.xm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NoticeReceiver extends BroadcastReceiver {
    private final String messageKey = "cn.com.fmsh.tsm.sptc.broadcast.message.key";

    public void onReceive(Context context, Intent intent) {
        System.out.println("=======================receive brodcast=================");
        int i = ((BroadCastParameter) intent.getExtras().get("cn.com.fmsh.tsm.sptc.broadcast.message.key")).broadcastType;
    }
}
