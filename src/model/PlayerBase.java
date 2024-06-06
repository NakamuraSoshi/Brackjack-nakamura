package model;

import java.util.ArrayList;
import java.util.List;

//プレイヤーとディーラーの抽象クラス

public abstract class PlayerBase {

//プレイヤーの手札を格納するhandリストの宣言 protectedでサブクラスのみアクセス可
    protected List<Card> hand = new ArrayList<>();

//手札取得メソッド
    public List<Card> getHand() {
        return hand;
    }

//カード引くメソッド 中身はサブクラスにある
    public abstract void drawCard(Deck deck);

//手札の合計値を計算して返すgetHandValueメソッドの宣言　戻り値はint
    public int getHandValue() {

//手札の合計値を保持する変数value エースの数を数えるaceCount 0初期化
        int value = 0;
        int aceCount = 0;

//手札のカードをループ handリストの各要素をcard変数に代入してループ
        for (Card card : hand) {
            int cardValue = card.getValue();

//カードがAかチェック Aならカウントを１増やす cardValueに1を代入
            if (cardValue == 1) {
                aceCount++;
                cardValue = 11;
            }
//現在のカードの値を合計値valueに加える
            value += cardValue;
        }

//手札の合計値が21を超えているときAが１枚以上含まれるならwhileループ開始
        while (value > 21 && aceCount > 0) {

//エースを11から1に変更しエースカウントも減らす
            value -= 10;
            aceCount--;
        }
//最後に手札の合計値を返す
        return value;
    }

//バーストを判定するメソッド　getHandValueで手札の合計値を取得し、21超えでtrue
    public boolean isBust() {
        return getHandValue() > 21;
    }
}