package com.amap.openapi;

import com.amap.location.common.model.CellStatus;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

public class q {

    /* renamed from: a  reason: collision with root package name */
    public byte f4740a;
    public String b;
    public ArrayList<r> c = new ArrayList<>();
    public List<CellStatus.HistoryCell> d = new ArrayList();

    public void a(byte b2, String str) {
        this.f4740a = b2;
        this.b = str;
        this.c.clear();
        this.d.clear();
    }

    public String toString() {
        return "Cell{mRadioType=" + this.f4740a + ", mOperator='" + this.b + Operators.SINGLE_QUOTE + ", mCellPart=" + this.c + ", mHistoryCellList=" + this.d + Operators.BLOCK_END;
    }
}
