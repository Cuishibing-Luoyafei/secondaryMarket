package com.secondaryMarket.service.impl;

import com.secondaryMarket.bean.Blame;
import com.secondaryMarket.bean.User;
import com.secondaryMarket.dao.UserDao;
import com.secondaryMarket.factory.DaoFactory;

import cui.secondaryMarket.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao ud;
	public UserServiceImpl(){
		ud = DaoFactory.createUserDao();
	}
	@Override
	public User getUserInId(Integer userId) {
		// TODO Auto-generated method stub
		return ud.getUserInId(userId);
	}

	@Override
	public User getUserInName(String nackName) {
		// TODO Auto-generated method stub
		return ud.getUserInName(nackName);
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return ud.deleteUser(user);
	}

	@Override
	public boolean validateUserName(String nackName) {
		// TODO Auto-generated method stub
		return ud.validateUserName(nackName);
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return ud.insertUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return ud.updateUser(user);
	}

	@Override
	public boolean blameUser(Blame blame) {
		// TODO Auto-generated method stub
		return ud.blameUser(blame);
	}
	
}
