package model;

//ディーラーの動作
public class Dealer extends PlayerBase {

	//drawCardメソッドでDeckオブジェクトからカードを一枚引き、ディーラーの手札に追加
	@Override
	public void drawCard(Deck deck) {
		hand.add(deck.drawCard());
		}

	//drawInitialCardsでゲーム開始時に２枚引く
	public  void drawInitialCards(Deck deck) {
		hand.add(deck.drawCard());
		hand.add(deck.drawCard());
	}

	//drawCardsメソッドの定義 ディーラの手札合計値が１７以上になるまで引く
	public void drawCards(Deck deck) {
		while (getHandValue() < 17) {
			drawCard(deck);
		}
	}

}
