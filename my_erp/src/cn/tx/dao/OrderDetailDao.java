package cn.tx.dao;

import java.util.List;

import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;


public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery>{
	
	public List<OrderDetail> getDetailByOrderModerId(String orderModerId);
}
