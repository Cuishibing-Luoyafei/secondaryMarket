package com.secondaryMarket.service.impl;

import com.secondaryMarket.bean.Commodity;
import com.secondaryMarket.dao.CommodityDao;
import com.secondaryMarket.factory.DaoFactory;

import cui.secondaryMarket.service.CommodityService;

public class CommodityServiceImpl implements CommodityService{
	private CommodityDao cd;
	public CommodityServiceImpl() {
		cd = DaoFactory.createCommodityDao();
	}
	@Override
	public Commodity getCommodityInId(Integer commodityId) {
		// TODO Auto-generated method stub
		return cd.getCommodityInId(commodityId);
	}

	@Override
	public boolean insertCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		return cd.insertCommodity(commodity);
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		return cd.updateCommodity(commodity);
	}

	@Override
	public boolean deleteCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		return cd.deleteCommodity(commodity);
	}
	
}
