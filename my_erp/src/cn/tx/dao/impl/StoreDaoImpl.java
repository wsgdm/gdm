package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.StoreDao;
import cn.tx.model.Store;
import cn.tx.query.StoreQuery;

public class StoreDaoImpl extends BaseDaoImpl<Store,StoreQuery> implements StoreDao{


	@Override
	public String creatHql(StoreQuery equery) {
		String hql = "from Store where 1=1";
		return hql + creat(equery);
		

	}

	@Override
	public String creatHqlCount(StoreQuery equery) {
		
		String hql = "select count(storeId) from Store where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(StoreQuery equery){
		String hql = "";
		if(StringUtils.isNotBlank(equery.getEmpName())){
			hql = hql + " and stockeEmp.name like :empName";
		}
		if(StringUtils.isNotBlank(equery.getAddress())){
			hql = hql +  " and address like :address";
		}
		if(StringUtils.isNotBlank(equery.getName())){
			hql = hql + " and name like :name";
		}
		return hql;
	}

}
