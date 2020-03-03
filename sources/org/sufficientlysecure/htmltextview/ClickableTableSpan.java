package org.sufficientlysecure.htmltextview;

import android.text.style.ClickableSpan;

public abstract class ClickableTableSpan extends ClickableSpan {

    /* renamed from: a  reason: collision with root package name */
    protected String f4182a;

    public abstract ClickableTableSpan a();

    public void a(String str) {
        this.f4182a = str;
    }

    public String b() {
        return this.f4182a;
    }
}
