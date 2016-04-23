package com.secondaryMarket.test;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.DaoFactory;

public class Test {
	private User u = null;
	public Test() {
		u = new User();
		u.setUserId(2);
		u.setUserNackName("罗亚飞sdfsefews");
		u.setUserRealName("罗亚飞00111");
		u.setUserEmail("12311@sdf");
		u.setUserPassword("l1uo");
		u.setUserQQ("14123");
		u.setUserRole(1);
		u.setUserSchool("科1大");
		u.setUserTel("2314");
	}
	
	public boolean insert() {
		return DaoFactory.createUserDao().insertUser(u);
	}
	
	public boolean delete() {
		return DaoFactory.createUserDao().deleteUser(u);
	}
	
	public boolean update() {
		return DaoFactory.createUserDao().updateUser(u);
	}
	
}

