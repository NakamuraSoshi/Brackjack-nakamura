package model;

import java.util.List;
//ブラックジャックの結果に関するクラス
public class BlackjackGameResult {

	//プレイヤーの手札とディーラーの手札からメッセージ出力
    public String determineResult(List<Card> playerHand, List<Card> dealerHand) {
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);

        if (playerValue > 21) {
            return "You bust! Dealer wins! 0x";
        } else if (dealerValue > 21 || playerValue > dealerValue) {
            return playerValue == 21 ? "Blackjack! You win! 2.5x" : "You win! 2x";
        } else if (playerValue < dealerValue) {
            return "Dealer wins! 0x";
        } else {
            return "It's a draw! 1x";
        }
    }

    //払い戻し計算
    public int calculatePayout(String result, int betAmount) {
        switch (result) {
            case "Blackjack! You win! 2.5x":
                return (int) (betAmount * 2.5);
            case "You win! 2x":
                return (int) (betAmount * 2);
            case "It's a draw! 1x":
                return betAmount;
            default:
                return 0;
        }
    }

    //手札計算
    private int calculateHandValue(List<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getRank().getValue();
            if (card.getRank().getValue() == 1) {
                aceCount++;
            }
        }

        while (value <= 11 && aceCount > 0) {
            value += 10;
            aceCount--;
        }

        return value;
    }
}
