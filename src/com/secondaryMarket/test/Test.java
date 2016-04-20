package com.secondaryMarket.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.secondaryMarket.bean.User;
import com.secondaryMarket.factory.ConnectionFactory;
import com.secondaryMarket.factory.DaoFactory;

public class Test {
	public static void main(String[] args){
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		User u = new User();
		u.setUserNackName("罗亚飞");
		u.setUserRealName("罗亚飞001");
		u.setUserEmail("123@sdf");
		u.setUserPassword("luo");
		u.setUserQQ("1423");
		u.setUserRole(1);
		u.setUserSchool("科大");
		u.setUserTel("234");
		Statement stmt;
		String sql = "insert into user values(null, 'diamond234', 'werluo', '罗亚wer飞', '12345', '6363wer63636', '123@foxmail.com', '西安科技大学', 1)";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		boolean flag = DaoFactory.createUserDao().insertUser(u);
		System.out.println(flag);
		*/
	}
}
