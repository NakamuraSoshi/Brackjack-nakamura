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

	public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

 // 画像パスを取得するメソッド
    public String getImagePath() {
        return "Cards/" + rank.name().toLowerCase() + "_of_" + suit.name().toLowerCase() + ".png";
    }
}
