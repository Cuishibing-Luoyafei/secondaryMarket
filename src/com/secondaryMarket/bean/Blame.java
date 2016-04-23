package com.secondaryMarket.bean;

public class Blame {
/*
 * -------------+---------+------+-----+---------+----------------+
| blameId     | int(11) | NO   | PRI | NULL    | auto_increment |
| userId      | int(11) | YES  |     | NULL    |                |
| blameCount  | int(11) | YES  |     | 0       |                |
| blameReason | text    | YES  |     | NULL    |
 * 
 * */
	private int blameId = 0;
	private int userId = -1;
	private int blameCount = 0;
	private String blameReason = "";
	private int honorRank = 0;
	
	public int getHonorRank() {
		return honorRank;
	}
	public void setHonorRank(int honorRank) {
		this.honorRank = honorRank;
	}
	public int getBlameId() {
		return blameId;
	}
	public void setBlameId(int blameId) {
		this.blameId = blameId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBlameCount() {
		return blameCount;
	}
	public void setBlameCount(int blameCount) {
		this.blameCount = blameCount;
	}
	public String getBlameReason() {
		return blameReason;
	}
	public void setBlameReason(String blameReason) {
		this.blameReason = blameReason;
	}
}
