package cn.tx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tx.dao.OrderDetailDao;
import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;
import cn.tx.service.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail,OrderDetailQuery> implements OrderDetailService {

	
	private OrderDetailDao orderDetailDao;
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		this.baseDao = orderDetailDao;
	}

	@Override
	public List<OrderDetail> getDetailByOrderModerId(String orderModerId) {
		
		return orderDetailDao.getDetailByOrderModerId(orderModerId);
	}

}
