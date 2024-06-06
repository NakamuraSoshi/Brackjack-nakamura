package exception;


//このシステム内で発生した例外を示すクラス

public class loginException extends Exception {

//画面に表示するメッセージ用に引数で受け取った値をセットします

	public loginException(String message) {
		super(message);
	}
}