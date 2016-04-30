package com.secondaryMarket.bean;

import java.sql.Timestamp;

public class Theme {
	private Integer themeId = -1;
	private String themeTitle = "";
	private String themeContent = "";
	private Timestamp themeTime = null;
	private Integer themeUserId = -1;
	private Integer themeCommodityId = -1;
	private Integer themeIsRead = 0;
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	public String getThemeTitle() {
		return themeTitle;
	}
	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}
	public String getThemeContent() {
		return themeContent;
	}
	public void setThemeContent(String themeContent) {
		this.themeContent = themeContent;
	}
	public String getThemeTime() {
		if(themeTime!=null)
			return themeTime.toString();
		else{
			themeTime = new Timestamp(System.currentTimeMillis());
			return themeTime.toString();
		}
		
	}
	public Timestamp getTimestamp(){
		getThemeTime();
		return themeTime;
	}
	public void setThemeTime(Timestamp themeTime) {
		this.themeTime = themeTime;
	}
	public Integer getThemeUserId() {
		return themeUserId;
	}
	public void setThemeUserId(Integer themeUserId) {
		this.themeUserId = themeUserId;
	}
	public Integer getThemeCommodityId() {
		return themeCommodityId;
	}
	public void setThemeCommodityId(Integer themeCommodityId) {
		this.themeCommodityId = themeCommodityId;
	}
	public Integer getThemeIsRead() {
		return themeIsRead;
	}
	public void setThemeIsRead(Integer themeIsRead) {
		this.themeIsRead = themeIsRead;
	}
}
