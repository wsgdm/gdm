package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.OrderModelDao;
import cn.tx.model.OrderModel;
import cn.tx.query.OrderModelQuery;

public class OrderModelDaoImpl extends BaseDaoImpl<OrderModel,OrderModelQuery> implements OrderModelDao{


	@Override
	public String creatHql(OrderModelQuery equery) {
		
		String hql = "from OrderModel t where 1=1";
		return hql + creat(equery) + " order by t.orderId desc";

	}

	@Override
	public String creatHqlCount(OrderModelQuery equery) {
		
		String hql = "select count(t.orderId) from OrderModel t where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(OrderModelQuery equery){
	
		String hql = "";
		if(StringUtils.isNotBlank(equery.getOrderCreater())){
			hql = hql +  " and t.orderCreat.name like :orderCreater";
		}
		if(equery.getMaxTotalNum() != null){
			hql = hql + " and t.totalNum <= :maxTotalNum";
		}
		if(equery.getMinTotalNum() != null){
			hql = hql + " and t.totalNum >= :minTotalNum";
		}
		if(equery.getMinCreateTime() != null){
			hql = hql + " and t.createTime >= :minCreateTime";
		}
		if(equery.getMaxCreateTime() != null){
			hql = hql + " and t.createTime <= :maxCreateTime";
		}
		if(equery.getMaxTotalPrice() != null){
			hql = hql + " and t.totalPrice <= :maxTotalPrice";
		}
		if(equery.getMinTotalPrice() != null){
			hql = hql + " and t.totalPrice >= :minTotalPrice";
		}
		if(equery.getOrderState() != null){
			hql = hql + " and t.orderState = :orderState";
		}
		if(equery.getOrderType() != null){
			hql = hql + " and t.orderType = :orderType";
		}
		if(equery.getMinCheckTime() != null){
			hql = hql + " and t.checkTime >= :minCheckTime";
		}
		if(equery.getMaxCheckTime() != null){
			hql = hql + " and t.checkTime <= :maxCheckTime";
		}
		if(equery.getSupplierId() != null){
			hql = hql + " and t.supplier.supplierId= :supplierId";
		}
		if(equery.getNeeds() != null){
			hql = hql + " and t.supplier.needs= :needs";
		}
		if(equery.getChecker() != null){
			hql = hql + " and t.chercker= :chercker";
		}
		if(equery.getCompleter() != null){
			hql = hql + " and t.completer= :completer";
		}
		return hql;
	}

}
