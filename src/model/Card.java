package model;
//カードを表現するモデルクラス
public class Card {
	private Rank rank;
	private Suit suit;

	//コンストラクタ
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	//カードのランクから値を返すgetValueメソッド
	public int getValue() {
		return rank.getValue();
	}

	//toStringメソッドで、カードのランクとスートの組み合わせた文字列を返す
	@Override
	public String toString() {
		return rank + "of" + suit;
	}
}
