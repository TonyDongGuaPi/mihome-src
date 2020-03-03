package com.tsmclient.smartcard.apdu;

import cn.com.fmsh.script.constants.ScriptToolsConst;

public class SelectCommand extends BaseCommand {
    public byte getIns() {
        return ScriptToolsConst.TagName.CommandMultiple;
    }

    public byte getP2() {
        return 0;
    }

    public void setCls(byte b) {
        super.setCls(b);
    }
}
