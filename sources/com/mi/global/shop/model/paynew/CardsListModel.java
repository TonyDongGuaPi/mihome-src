package com.mi.global.shop.model.paynew;

import com.mi.global.shop.buy.model.CreditCardItem;
import com.mi.global.shop.model.common.UserCardsType;
import com.mi.global.shop.model.user.CardsList;
import java.util.ArrayList;

public class CardsListModel {
    public ArrayList<CreditCardItem> cardList;

    public static CardsListModel parseCardsList(CardsList cardsList) {
        CardsListModel cardsListModel = new CardsListModel();
        cardsListModel.cardList = new ArrayList<>();
        for (UserCardsType a2 : cardsList.user_cards) {
            cardsListModel.cardList.add(CreditCardItem.a(a2));
        }
        return cardsListModel;
    }
}
