package cn.tx.service.impl;

import cn.tx.dao.OrderModelDao;
import cn.tx.model.OrderModel;
import cn.tx.query.OrderModelQuery;
import cn.tx.service.OrderModelService;

public class OrderModelServiceImpl extends BaseServiceImpl<OrderModel,OrderModelQuery> implements OrderModelService {

	
	private OrderModelDao orderModelDao;
	
	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
		this.baseDao = orderModelDao;
	}

}
