package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//カード保持のインスタンス変数cardsを定義
public class Deck {
	private List<Card> cards;

	//Deckクラスのコンストラクタ cardsの初期化
	//すべてのSuitとRankの組に対してループし、新しいカードを作成してリストに追加
	public Deck() {
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		//デッキをシャッフル
		shuffle();

	}
	//シャッフルのためのshuffleメソッドの定義 Collections.shuffleメソッドでシャッフル
	public void shuffle() {
		Collections.shuffle(cards);
	}

	//デッキからカードを引くdrawCardメソッドを定義
	//デッキが空ならスロー　そうでなければリストの最初のカードをデッキから削除
	public Card drawCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("Deck is Empty");
		}
		return cards.remove(0);
	}

}
