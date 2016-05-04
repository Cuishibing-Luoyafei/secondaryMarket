package com.secondaryMarket.service.impl;

import java.util.List;

import com.secondaryMarket.bean.Blame;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.dao.BlameDao;
import com.secondaryMarket.factory.DaoFactory;
import com.secondaryMarket.service.BlameService;

public class BlameServiceImpl implements BlameService{

	private BlameDao bmd =  DaoFactory.createBlameDao();
	
	@Override
	public boolean blameUser(Blame blame, Integer blameUserId) {
		// TODO Auto-generated method stub
		Blame b = bmd.getBlameInUserId(blameUserId);
		if(b!=null) {
			blame.setBlameId(b.getBlameId());
			return bmd.updateBlame(blame);
		}
		else
			return bmd.insertBlame(blame);
	}

	@Override
	public List<Blame> getAllBlame() {
		// TODO Auto-generated method stub
		return bmd.getAllBlame();
	}

	@Override
	public boolean isSb(User user) {
		// TODO Auto-generated method stub
		return bmd.isSb(user);
	}

	@Override
	public boolean relieveUser(Integer userId) {
		Blame b = bmd.getBlameInUserId(userId);
		if(b!=null){
			return bmd.deleteBlame(b);
		}else{
			return true;
		}
	}

	@Override
	public Blame getBlameInUser(Integer userId) {
		// TODO Auto-generated method stub
		return bmd.getBlameInUserId(userId);
	}

	
	
}
