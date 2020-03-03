package com.google.android.exoplayer2.source.dash.manifest;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;

public class ProgramInformation {
    public final String copyright;
    public final String lang;
    public final String moreInformationURL;
    public final String source;
    public final String title;

    public ProgramInformation(String str, String str2, String str3, String str4, String str5) {
        this.title = str;
        this.source = str2;
        this.copyright = str3;
        this.moreInformationURL = str4;
        this.lang = str5;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProgramInformation programInformation = (ProgramInformation) obj;
        if (!Util.areEqual(this.title, programInformation.title) || !Util.areEqual(this.source, programInformation.source) || !Util.areEqual(this.copyright, programInformation.copyright) || !Util.areEqual(this.moreInformationURL, programInformation.moreInformationURL) || !Util.areEqual(this.lang, programInformation.lang)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((527 + (this.title != null ? this.title.hashCode() : 0)) * 31) + (this.source != null ? this.source.hashCode() : 0)) * 31) + (this.copyright != null ? this.copyright.hashCode() : 0)) * 31) + (this.moreInformationURL != null ? this.moreInformationURL.hashCode() : 0)) * 31;
        if (this.lang != null) {
            i = this.lang.hashCode();
        }
        return hashCode + i;
    }
}
