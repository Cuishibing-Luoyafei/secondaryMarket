package com.secondaryMarket.bean;

import java.sql.Timestamp;

public class Reply {

	/*
	 * replyId int primary key auto_increment,
		replyThemeId int references theme(themeId),
		replyUserId int references user(userId),
		replyContent text not null,
		replyTime datetime not null
	 * */
	
	private Integer replyId = -1;
	private Integer replyThemeId = -1;
	private Integer replyUserId = -1;
	private String replyContent = "";
	private Timestamp replyTime = null;
	
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getReplyThemeId() {
		return replyThemeId;
	}
	public void setReplyThemeId(Integer replyThemeId) {
		this.replyThemeId = replyThemeId;
	}
	public Integer getReplyUserId() {
		return replyUserId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}
	public Timestamp getReplyTime() {
		if(replyTime == null)
			replyTime = new Timestamp(System.currentTimeMillis());
		return replyTime;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

}
