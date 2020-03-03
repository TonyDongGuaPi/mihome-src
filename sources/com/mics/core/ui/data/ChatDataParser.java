package com.mics.core.ui.data;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.mics.core.business.ChatDataSource;
import com.mics.core.data.business.ChatParams;
import com.mics.core.data.response.MessageResponse;
import com.mics.core.ui.kit.Card;
import com.mics.core.ui.kit.CardMutli;
import com.mics.core.ui.kit.DividerHint;
import com.mics.core.ui.kit.Hint;
import com.mics.core.ui.kit.ImageLeft;
import com.mics.core.ui.kit.ImageRight;
import com.mics.core.ui.kit.TextLeft;
import com.mics.core.ui.kit.TextRight;
import com.mics.core.ui.kit.Time;
import com.mics.core.ui.widget.CardMultiView;
import com.mics.util.GsonUtil;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatDataParser {
    public static Object parse(ChatDataSource.Data data) {
        int i = -1;
        boolean z = false;
        if (data.f() == 1) {
            if (data.l()) {
                TextRight.Data data2 = new TextRight.Data();
                data2.a((CharSequence) data.h());
                data2.setTime(data.q());
                data2.setId(data.a());
                if (data.g() == 3 || data.g() == 5) {
                    i = 1;
                } else if (data.g() != 4) {
                    i = 0;
                }
                data2.a(i);
                return data2;
            }
            TextLeft.Data data3 = new TextLeft.Data();
            data3.a((CharSequence) data.h());
            data3.setTime(data.q());
            data3.setId(data.a());
            data3.a(data.j());
            data3.setExtra(data.p());
            if (data.g() == 6) {
                z = true;
            }
            data3.setHasChoose(z);
            if (!URLUtil.isNetworkUrl(data.h())) {
                return data3;
            }
            data3.a(true);
            return data3;
        } else if (data.f() == 2) {
            if (data.l()) {
                ImageRight.Data data4 = new ImageRight.Data();
                data4.a(data.i(), !URLUtil.isNetworkUrl(data.i()));
                data4.b(data.g());
                data4.setId(data.a());
                data4.setTime(data.q());
                data4.a(data.m());
                if (data.g() == 3 || data.g() == 5) {
                    i = 1;
                } else if (data.g() != 4) {
                    i = 0;
                }
                data4.a(i);
                return data4;
            }
            ImageLeft.Data data5 = new ImageLeft.Data();
            data5.b(data.j());
            data5.a(data.i());
            data5.setId(data.a());
            data5.setTime(data.q());
            return data5;
        } else if (data.f() == 4 || data.f() == 9) {
            TextLeft.Data data6 = new TextLeft.Data();
            data6.a(data.j());
            data6.setTime(data.q());
            data6.a((CharSequence) data.h());
            data6.setId(data.a());
            data6.a(true);
            if (data.g() == 6) {
                z = true;
            }
            data6.setHasChoose(z);
            if (data.f() == 9) {
                data6.b(data.p());
                return data6;
            }
            data6.setExtra(data.p());
            return data6;
        } else if (data.f() == 5) {
            List<MessageResponse.Data.RobotQuestion.Article> articles = ((MessageResponse.Data.RobotQuestion) GsonUtil.a(data.h(), (Type) MessageResponse.Data.RobotQuestion.class)).getArticles();
            if (articles == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (MessageResponse.Data.RobotQuestion.Article next : articles) {
                if (next != null) {
                    CardMultiView.Article article = new CardMultiView.Article();
                    article.b(next.getDescription());
                    article.c(next.getPicUrl());
                    article.a(next.getTitle());
                    article.d(next.getUrl());
                    article.e(next.getType());
                    arrayList.add(article);
                }
            }
            CardMutli.Data data7 = new CardMutli.Data();
            data7.a(arrayList);
            data7.setId(data.a());
            data7.setTime(data.q());
            data7.setContent(data.h());
            return data7;
        } else if (data.f() == 6) {
            String p = data.p();
            if (TextUtils.isEmpty(p)) {
                return null;
            }
            Card.Data data8 = new Card.Data();
            ChatParams chatParams = (ChatParams) GsonUtil.a(p, ChatParams.class);
            ChatParams.Goods goods = chatParams.getGoods();
            ChatParams.Order order = chatParams.getOrder();
            String orderId = order != null ? order.getOrderId() : null;
            if (ChatParams.Goods.isNull(goods)) {
                return null;
            }
            data8.a(goods.getCover());
            data8.a(goods.getPrice());
            data8.b(goods.getName());
            data8.c(goods.getUrl());
            if (!TextUtils.isEmpty(orderId)) {
                data8.d(orderId);
            }
            return data8;
        } else if (data.f() == 7) {
            if (data.q() <= 0) {
                return null;
            }
            String format = new SimpleDateFormat("MM月dd日 HH:mm:ss", Locale.getDefault()).format(Long.valueOf(data.q()));
            Time.Data data9 = new Time.Data();
            data9.a(format);
            return data9;
        } else if (data.f() == 3) {
            if (TextUtils.isEmpty(data.h())) {
                return null;
            }
            Hint.Data data10 = new Hint.Data();
            data10.a(data.a());
            data10.a((CharSequence) data.h());
            return data10;
        } else if (data.f() != 8 || TextUtils.isEmpty(data.h())) {
            return null;
        } else {
            DividerHint.Data data11 = new DividerHint.Data();
            data11.a(data.h());
            return data11;
        }
    }
}
