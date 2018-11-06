package cn.tx.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.OrderDetailDao;
import cn.tx.model.OrderDetail;
import cn.tx.query.OrderDetailQuery;

public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail,OrderDetailQuery> implements OrderDetailDao{


	@Override
	public String creatHql(OrderDetailQuery equery) {
		String hql = "from OrderDetail t where 1=1";
		return hql + creat(equery) + " order by t.orderDetailId desc";

	}

	@Override
	public String creatHqlCount(OrderDetailQuery equery) {
		
		String hql = "select count(t.orderDetailId) from OrderDetail t where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(OrderDetailQuery equery){
		String hql = "";
		if(StringUtils.isNotBlank(equery.getOrderId())){
			hql = hql +  " and t.orderId like :orderId";
		}
		return hql;
	}

	@Override
	public List<OrderDetail> getDetailByOrderModerId(String orderModerId) {
		String hql = "from OrderDetail where orderId = ?";
		List<OrderDetail> list = this.getHibernateTemplate().find(hql, orderModerId);
		return list;
	}

}
