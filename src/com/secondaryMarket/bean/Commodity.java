package com.secondaryMarket.bean;

public class Commodity {
	
	/*
	 *  commodityId int primary key auto_increment,
		commodityName varchar(30) not null,
		commodityCategary varchar(30) not null,
		commodityStatus int default -1,
		commodityPicture varchar(255) not null,
		commodityDescribe text not null,
		commodityCount int default 1,
		commodityOldNewLevel int,
		commodityOldPrice varchar(11),
		commodityNewPrice varchar(11),
		commodityOwner int references user(userId),
		commodityDownDay int
		 * 
	 * */
	private Integer commodityId = -1;
	private String commodityName = "";
	private String commodityCategary = "";
	private Integer commodityStatus = -1;
	private String commodityPicture = "";
	private String commodityDescribe = "";
	private Integer commodityCount = -1;
	private Integer commodityOldNewLevel = -1;
	private String commodityOldPrice = "";
	private String commodityNewPrice = "";
	private Integer commodityOwner = -1;
	private Integer commodityDownDay = -1;
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityCategary() {
		return commodityCategary;
	}
	public void setCommodityCategary(String commodityCategary) {
		this.commodityCategary = commodityCategary;
	}
	public Integer getCommodityStatus() {
		return commodityStatus;
	}
	public void setCommodityStatus(Integer commodityStatus) {
		this.commodityStatus = commodityStatus;
	}
	public String getCommodityPicture() {
		return commodityPicture;
	}
	public void setCommodityPicture(String commodityPicture) {
		this.commodityPicture = commodityPicture;
	}
	public String getCommodityDescribe() {
		return commodityDescribe;
	}
	public void setCommodityDescribe(String commodityDescribe) {
		this.commodityDescribe = commodityDescribe;
	}
	public Integer getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(Integer commodityCount) {
		this.commodityCount = commodityCount;
	}
	public Integer getCommodityOldNewLevel() {
		return commodityOldNewLevel;
	}
	public void setCommodityOldNewLevel(Integer commodityOldNewLevel) {
		this.commodityOldNewLevel = commodityOldNewLevel;
	}
	public String getCommodityOldPrice() {
		return commodityOldPrice;
	}
	public void setCommodityOldPrice(String commodityOldPrice) {
		this.commodityOldPrice = commodityOldPrice;
	}
	public String getCommodityNewPrice() {
		return commodityNewPrice;
	}
	public void setCommodityNewPrice(String commodityNewPrice) {
		this.commodityNewPrice = commodityNewPrice;
	}
	public Integer getCommodityOwner() {
		return commodityOwner;
	}
	public void setCommodityOwner(Integer commodityOwner) {
		this.commodityOwner = commodityOwner;
	}
	public Integer getCommodityDownDay() {
		return commodityDownDay;
	}
	public void setCommodityDownDay(Integer commodityDownDay) {
		this.commodityDownDay = commodityDownDay;
	}
	

}
