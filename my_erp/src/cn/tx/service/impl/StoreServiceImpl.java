package cn.tx.service.impl;

import java.util.Set;

import cn.tx.dao.OrderDetailDao;
import cn.tx.dao.OrderModelDao;
import cn.tx.dao.ProductDao;
import cn.tx.dao.StoreDao;
import cn.tx.model.OrderDetail;
import cn.tx.model.OrderModel;
import cn.tx.model.Product;
import cn.tx.model.Store;
import cn.tx.model.StoreDetail;
import cn.tx.query.StoreQuery;
import cn.tx.service.StoreService;

public class StoreServiceImpl extends BaseServiceImpl<Store,StoreQuery> implements StoreService {

	
	private StoreDao storeDao;
	
	private OrderDetailDao orderDetailDao;
	
	private ProductDao productDao;
	
	private OrderModelDao orderModelDao;
	
	
	




	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
		this.baseDao = storeDao;
	}

	@Override
	public void updateInstock(Integer storeId, Integer productId,
			Integer productNum, Integer orderDetailId) {
		int isExitPro = 0; 
		Store store = storeDao.getObjectById(storeId);
		OrderDetail orderDetail = orderDetailDao.getObjectById(orderDetailId);
		Set<StoreDetail> storeDetail = store.getStoreDetail();
		for(StoreDetail sd : storeDetail ){
			if(sd.getProduct().getProductId().intValue() == productId){
				sd.setNum(sd.getNum()+productNum);
				if(orderDetail.getSurplus() < productNum){
					productNum = orderDetail.getSurplus();
				}
				int num = orderDetail.getSurplus() - productNum;
				orderDetail.setSurplus(num);
				isExitPro = 1;
			}
		}
		//商品不存在
		if(isExitPro == 0){
			StoreDetail sd = new StoreDetail();
			sd.setStoreId(storeId);
			Product p = productDao.getObjectById(productId);
			sd.setProduct(p);
			if(orderDetail.getSurplus() < productNum){
				productNum = orderDetail.getSurplus();
			}
			sd.setNum(productNum);
			int num = orderDetail.getSurplus() - productNum;
			orderDetail.setSurplus(num);		
			storeDetail.add(sd);
		}
		
		OrderModel odm = orderModelDao.getObjectById(new Integer(orderDetail.getOrderId()));
		int i = 0;
		for(OrderDetail od : odm.getDetails()){
			i = od.getSurplus() + i;
		}
		if(i == 0){
			odm.setOrderState(3);
			odm.setOrderType(3);
		}else{
			odm.setOrderState(2);
		}
		orderModelDao.update(odm);
		orderDetailDao.update(orderDetail);
		storeDao.update(store);
	}

}
