package com.secondaryMarket.service;

import java.util.List;

import com.secondaryMarket.bean.PublicMsg;

public interface PublicMsgService {
	public boolean insertPublicMsg(PublicMsg p);
	public boolean deletePublicMsg(PublicMsg p);
	public List<PublicMsg> getAllPublicMsg();
}
