package com.secondaryMarket.dao;

import java.util.List;

import com.secondaryMarket.bean.PublicMsg;

public interface PublicMsgDao {
	public boolean insertPublicMsg(PublicMsg p);
	public boolean deletePublicMsg(PublicMsg p);
	public List<PublicMsg> getAllPublicMsg();
}
