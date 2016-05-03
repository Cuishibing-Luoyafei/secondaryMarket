package com.secondaryMarket.service;

import java.util.List;

import com.secondaryMarket.bean.Blame;
import com.secondaryMarket.bean.User;

public interface BlameService {
	public boolean blameUser(Blame blame, Integer blameUserId);
	public List<Blame> getAllBlame();
	public boolean isSb(User user);
	public boolean relieveUser(Integer userId);
}
