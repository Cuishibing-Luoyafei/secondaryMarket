package com.secondaryMarket.dao;

import com.secondaryMarket.bean.Commodity;

public interface CommodityDao {
	public Commodity getCommodityInId(Integer commodityId);
	public boolean insertCommodity(Commodity commodity);
	public boolean updateCommodity(Commodity commodity);
	public boolean deleteCommodity(Commodity commodity);
}
