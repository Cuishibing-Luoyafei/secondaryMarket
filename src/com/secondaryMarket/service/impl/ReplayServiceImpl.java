package com.secondaryMarket.service.impl;

import java.util.List;

import com.secondaryMarket.bean.Reply;
import com.secondaryMarket.dao.ReplyDao;
import com.secondaryMarket.factory.DaoFactory;

import cui.secondaryMarket.service.ReplayService;

public class ReplayServiceImpl implements ReplayService{
	private ReplyDao rd;
	public ReplayServiceImpl() {
		rd = DaoFactory.createReplyDao();
	}
	@Override
	public Reply getReplyInId(Integer replyId) {
		// TODO Auto-generated method stub
		return rd.getReplyInId(replyId);
	}

	@Override
	public List<Reply> getReplys(Integer start, Integer size) {
		// TODO Auto-generated method stub
		return rd.getReplys(start, size);
	}

	@Override
	public boolean insertReply(Reply reply) {
		// TODO Auto-generated method stub
		return rd.insertReply(reply);
	}

	@Override
	public boolean deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		return rd.deleteReply(reply);
	}
	
}
