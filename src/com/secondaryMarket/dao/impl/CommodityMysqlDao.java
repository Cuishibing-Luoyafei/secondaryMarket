package com.secondaryMarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.dao.CommodityDao;
import com.secondaryMarket.factory.ConnectionFactory;

public class CommodityMysqlDao implements CommodityDao {
	
	/* 
	 * commodityId          | int(11)      | NO   | PRI | NULL    | auto_increment |
	| commodityName        | varchar(30)  | NO   |     | NULL    |                |
	| commodityCategary    | varchar(30)  | NO   |     | NULL    |                |
	| commodityStatus      | int(11)      | YES  |     | -1      |                |
	| commodityPicture     | varchar(255) | NO   |     | NULL    |                |
	| commodityDescribe    | text         | NO   |     | NULL    |                |
	| commodityCount       | int(11)      | YES  |     | 1       |                |
	| commodityOldNewLevel | int(11)      | YES  |     | NULL    |                |
	| commodityOldPrice    | varchar(11)  | YES  |     | NULL    |                |
	| commodityNewPrice    | varchar(11)  | YES  |     | NULL    |                |
	| commodityOwner       | int(11)      | YES  |     | NULL    |                |
	| commodityDownDay     | int(11)
	 * */
	
	/* (non-Javadoc)
	 * @see com.secondaryMarket.dao.CommodityDao#getCommodityInId(java.lang.Integer)
	 */
	@Override
	public Commodity getCommodityInId(Integer commodityId) {
		// TODO Auto-generated method stub
		Commodity commodity = new Commodity();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "select * from commodity where commodityId = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commodityId);
			rs = pstmt.executeQuery();
			rs.next();
			commodity.setCommodityId(rs.getInt(1));
			commodity.setCommodityName(rs.getString(2));
			commodity.setCommodityCategary(rs.getString(3));
			commodity.setCommodityStatus(rs.getInt(4));
			commodity.setCommodityPicture(rs.getString(5));
			commodity.setCommodityDescribe(rs.getString(6));
			commodity.setCommodityCount(rs.getInt(7));
			commodity.setCommodityOldNewLevel(rs.getInt(8));
			commodity.setCommodityOldPrice(rs.getString(9));
			commodity.setCommodityNewPrice(rs.getString(10));
			commodity.setCommodityOwner(rs.getInt(11));
			commodity.setCommodityDownDay(rs.getInt(12));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
System.out.println("根据物品Id查找物品，查找结果出现异常！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt, rs);
		}
		return commodity;
	}

	/* (non-Javadoc)
	 * @see com.secondaryMarket.dao.CommodityDao#insertCommodity(com.secondaryMarket.bean.Commodity)
	 */
	@Override
	public boolean insertCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "insert into commodity(commodityId, commodityName, commodityCategary, commodityStatus, commodityPicture," + 
		" commodityDescribe, commodityCount, commodityOldNewLevel, commodityOldPrice, commodityNewPrice, commodityOwner," + 
		" commodityDownDay) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commodity.getCommodityName());
			pstmt.setString(2, commodity.getCommodityCategary());
			pstmt.setInt(3, commodity.getCommodityStatus());
			pstmt.setString(4, commodity.getCommodityPicture());
			pstmt.setString(5, commodity.getCommodityDescribe());
			pstmt.setInt(6, commodity.getCommodityCount());
			pstmt.setInt(7, commodity.getCommodityOldNewLevel());
			pstmt.setString(8, commodity.getCommodityOldPrice());
			pstmt.setString(9, commodity.getCommodityNewPrice());
			pstmt.setInt(10, commodity.getCommodityOwner());
			pstmt.setInt(11, commodity.getCommodityDownDay());
			int result = pstmt.executeUpdate();
			if(result != 1) {
				flag = false;
System.out.println("物品插入数据库失败！");
			}
		} catch(SQLException e) {
			flag = false;
System.out.println("物品插入数据库失败！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		return flag;
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		boolean flag  = true;
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "update commodity set commodityName=?, commodityCategary=?, commodityStatus=?, commodityPicture=?," + 
		" commodityDescribe=?, commodityCount=?, commodityOldNewLevel=?, commodityOldPrice=?, commodityNewPrice=?, commodityOwner=?," + 
		" commodityDownDay=? where commodityId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, commodity.getCommodityName());
			pstmt.setString(2, commodity.getCommodityCategary());
			pstmt.setInt(3, commodity.getCommodityStatus());
			pstmt.setString(4, commodity.getCommodityPicture());
			pstmt.setString(5, commodity.getCommodityDescribe());
			pstmt.setInt(6, commodity.getCommodityCount());
			pstmt.setInt(7, commodity.getCommodityOldNewLevel());
			pstmt.setString(8, commodity.getCommodityOldPrice());
			pstmt.setString(9, commodity.getCommodityNewPrice());
			pstmt.setInt(10, commodity.getCommodityOwner());
			pstmt.setInt(11, commodity.getCommodityDownDay());
			pstmt.setInt(12, commodity.getCommodityId());
			int result = pstmt.executeUpdate();
			if(result != 1) {
				flag = false;
System.out.println("物品更新失败！");
			}
		} catch(SQLException e) {
			flag = false;
System.out.println("物品更新失败！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		return flag;
	}

	@Override
	public boolean deleteCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		boolean flag = true;
		int commodityId = commodity.getCommodityId();
		Connection conn = ConnectionFactory.createMySqlConnectionBuilder().getConnection();
		String sql = "delete from commodity where commodityId = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commodityId);
			if(pstmt.executeUpdate() != 1)
				flag = false;
		} catch(SQLException e) {
			flag = false;
System.out.println("数据库删除货物时出错！");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closed(conn, pstmt);
		}
		return flag;
	}
}
