package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.enums.EnumIssueProcess;

public interface IssuerProcessHandler {
    void handle(EnumIssueProcess enumIssueProcess);
}
