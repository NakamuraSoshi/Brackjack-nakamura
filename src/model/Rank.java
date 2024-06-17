package model;

public enum Rank {

    ACE(1, "ACE"), TWO(2, "TWO"), THREE(3, "THREE"), FOUR(4, "FOUR"), FIVE(5, "FIVE"), SIX(6, "SIX"), SEVEN(7, "SEVEN"),
    EIGHT(8, "EIGHT"), NINE(9, "NINE"), TEN(10, "TEN"), JACK(10, "JACK"), QUEEN(10, "QUEEN"), KING(10, "KING");

    //各ランクの値を格納するためのインスタンス変数valueを宣言 finalで変更させない
    private final int value;
    private final String name;

    //コンストラクタ
    Rank(int value, String name) {
        this.value = value;
        this.name = name;
    }

    //ゲッダーを設定し、呼び出すことでランクの値が取れるように
    public int getValue() {
        return value;
    }

    //名前を取得するためのゲッダー
    public String getName() {
        return name;
    }
}
