package model;

//トランプの種類を列挙型 ランク（ACEなど）に対する値を持たせる

public enum Rank {

	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
	EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

//各ランクの値を格納するためのインスタンス変数valueを宣言 finalで変更させない

	private final int value;

//コンストラクタ
	Rank(int value){
		this.value = value;
	}
//ゲッダーを設定し、呼び出すことでランクの値が取れるように
	public int getValue() {
		return value;
	}
}
