package model;

public class Chip {
	private String userId;
	private int chipCount;

	public Chip(String userId, int chipCount) {
		this.userId = userId;
		this.chipCount = chipCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getChipCount() {
		return chipCount;
	}

	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
	}
}
