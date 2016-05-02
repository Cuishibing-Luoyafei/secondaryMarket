package com.secondaryMarket.dao;

import java.util.List;

import com.secondaryMarket.bean.Blame;
import com.secondaryMarket.bean.User;

public interface BlameDao {
	public boolean insertBlame(Blame blame);
	public Blame getBlameInId(Integer blameId);
	public Blame getBlameInUser(User user);
	public Blame getBlameInUserId(Integer userId);
	public boolean deleteBlame(Blame blame);
	public boolean updateBlame(Blame blame);
	public List<Blame> getAllBlame();
	public boolean isSb(User user);
}
