package model;

//プレイヤーの動作
public class Player extends PlayerBase {

//drawCardメソッドで、Deckオブジェクトからカードを一枚引き、プレイヤーのhandに追加
	@Override
	public void drawCard(Deck deck) {
		hand.add(deck.drawCard());
	}

//drawInitialCardsメソッドの定義 ゲーム開始時に２枚引く
	public void drawInitialCards(Deck deck) {
		hand.add(deck.drawCard());
		hand.add(deck.drawCard());
	}

}
