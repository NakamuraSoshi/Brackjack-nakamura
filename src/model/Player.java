package model;

import java.util.ArrayList;
import java.util.List;

//プレイヤーの動作
public class Player extends PlayerBase {
	private List<Card> hand2 = new ArrayList<>();
	 private boolean hand1Stand = false;
	 private boolean hand2Stand = false;

//drawCardメソッドで、Deckオブジェクトからカードを一枚引き、プレイヤーのhandに追加
	@Override
	public void drawCard(Deck deck) {
		hand.add(deck.drawCard());
	}
	public void drawCard1(Deck deck) {
		hand.add(deck.drawCard());
	}

	public void drawCard2(Deck deck) {
		hand2.add(deck.drawCard());
	}

//drawInitialCardsメソッドの定義 ゲーム開始時に２枚引く
	public void drawInitialCards(Deck deck) {
		hand.add(deck.drawCard());
		hand.add(deck.drawCard());
	}

	//１つの手札をスタンド
 	 public void standHand1() {
        hand1Stand = true;
    }

 	 //２つの手札をスタンド
    public void standHand2() {
        hand2Stand = true;
    }

    //両方の手札をスタンドしたか確認
    public boolean isBothHandsStand() {
        return hand1Stand && hand2Stand;
    }

    public List<Card> getHand2() {
        return hand2;
    }

    public void split() {

            // 手札を分割して新しい手札を作成
            hand2.add(hand.remove(1)); // 最初の手札から1枚移動して新しい手札に追加

    }

    @Override
    public boolean isBust() {
        return getHandValue() > 21 || getHand2Value() > 21; // 両方の手札をチェック
    }

    public int getHand2Value() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand2) {
            int cardValue = card.getValue();
            if (cardValue == 1) {
                aceCount++;
                cardValue = 11;
            }
            value += cardValue;
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }
}