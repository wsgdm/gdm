package cn.tx.service;

import java.util.List;

import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;

public interface OrderDetailService extends BaseService<OrderDetail,OrderDetailQuery>{
	 List<OrderDetail> getDetailByOrderModerId(String orderModerId);
}
