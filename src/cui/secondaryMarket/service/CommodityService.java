package cui.secondaryMarket.service;

import com.secondaryMarket.bean.Commodity;

public interface CommodityService {
	public Commodity getCommodityInId(Integer commodityId);
	public boolean insertCommodity(Commodity commodity);
	public boolean updateCommodity(Commodity commodity);
	public boolean deleteCommodity(Commodity commodity);
}
