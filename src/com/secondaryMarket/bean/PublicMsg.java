package com.secondaryMarket.bean;

import java.sql.Timestamp;

public class PublicMsg {
	Integer publicMsgId = -1;
	String publicMsgTheme = "";
	String publicMsgContent = "";
	Timestamp publicMsgTime = null;
	public Integer getPublicMsgId() {
		return publicMsgId;
	}
	public void setPublicMsgId(Integer publicMsgId) {
		this.publicMsgId = publicMsgId;
	}
	public String getPublicMsgTheme() {
		return publicMsgTheme;
	}
	public void setPublicMsgTheme(String publicMsgTheme) {
		this.publicMsgTheme = publicMsgTheme;
	}
	public String getPublicMsgContent() {
		return publicMsgContent;
	}
	public void setPublicMsgContent(String publicMsgContent) {
		this.publicMsgContent = publicMsgContent;
	}
	public String getPublicMsgTime() {
		if(publicMsgTime!=null)
			return publicMsgTime.toString();
		else{
			publicMsgTime = new Timestamp(System.currentTimeMillis());
			return publicMsgTime.toString();
		}
	}
	public Timestamp getTimestamp(){
		getPublicMsgTime();
		return publicMsgTime;
	}
	public void setPublicMsgTime(Timestamp publicMsgTime) {
		this.publicMsgTime = publicMsgTime;
	}
	
	
}
