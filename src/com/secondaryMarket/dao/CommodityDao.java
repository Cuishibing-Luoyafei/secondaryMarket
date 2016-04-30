package com.secondaryMarket.dao;

import java.util.List;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.bean.User;

public interface CommodityDao {
	public Commodity getCommodityInId(Integer commodityId);
	public boolean insertCommodity(Commodity commodity);
	public boolean updateCommodity(Commodity commodity);
	public boolean deleteCommodity(Commodity commodity);
	public List<Commodity> getCommodityInUser(User user);
}
