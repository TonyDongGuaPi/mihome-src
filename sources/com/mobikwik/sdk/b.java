package com.mobikwik.sdk;

import com.mobikwik.sdk.lib.model.SavedCardResponse;
import com.mobikwik.sdk.lib.tasks.GetSavedCards;

class b implements GetSavedCards.GetSavedCardsListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f8360a;

    b(a aVar) {
        this.f8360a = aVar;
    }

    public void onSavedCaredLoded(SavedCardResponse.CardDetails[] cardDetailsArr) {
        if (this.f8360a.e.b != null && this.f8360a.e.b.isShowing()) {
            this.f8360a.e.b.dismiss();
        }
        this.f8360a.e.a(this.f8360a.b, cardDetailsArr);
    }
}
