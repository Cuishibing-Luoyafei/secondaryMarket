package com.secondaryMarket.service.impl;

import java.util.List;

import com.secondaryMarket.bean.PublicMsg;
import com.secondaryMarket.dao.PublicMsgDao;
import com.secondaryMarket.factory.DaoFactory;
import com.secondaryMarket.service.PublicMsgService;

public class PublicMsgServiceImpl implements PublicMsgService{
	private PublicMsgDao pmd = DaoFactory.createPublicMsgDao();
	@Override
	public boolean insertPublicMsg(PublicMsg p) {
		// TODO Auto-generated method stub
		return pmd.insertPublicMsg(p);
	}

	@Override
	public boolean deletePublicMsg(PublicMsg p) {
		// TODO Auto-generated method stub
		return pmd.deletePublicMsg(p);
	}

	@Override
	public List<PublicMsg> getAllPublicMsg() {
		// TODO Auto-generated method stub
		return pmd.getAllPublicMsg();
	}
	
}
