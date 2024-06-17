package model;

import java.util.ArrayList;
import java.util.List;

//hand2に２つ目の手札、手札がstandしたか保持
public class Player extends PlayerBase {
	private List<Card> hand;
    private List<Card> hand2;
    private boolean hand1Stand = false;
    private boolean hand2Stand = false;

    //初期化で手札を空、スプリットしていない、hand1に設定
    public Player() {

    	hand = new ArrayList<>();
    	hand2 = new ArrayList<>();
    }

    //カードを引くメソッド
    @Override
	public void drawCard(Deck deck) {
		hand.add(deck.drawCard());
	}
    //2枚目の手札に追加
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

    //プレイヤーの手札２枚が同ランクでスプリット、手札の２枚目をhand2に移動
    public void split() {
        if (hand.size() == 2 && hand.get(0).getRank().getValue() == hand.get(1).getRank().getValue()) {
            hand2.add(hand.remove(1));
        }


    }


}