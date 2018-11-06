package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.DepDao;
import cn.tx.model.Dep;
import cn.tx.query.DepQuery;
import cn.tx.query.EmpQuery;

public class DepDaoImpl extends BaseDaoImpl<Dep,DepQuery> implements DepDao {


	@Override
	public String creatHql(DepQuery equery) {
		String hql = "from Dep where 1=1";
		return hql + creat(equery);

	}

	@Override
	public String creatHqlCount(DepQuery q) {
		String hql = "select count(depId) from Dep where 1=1";
		return hql + creat(q);
	} 

	public String creat(DepQuery equery){
		String hql = "";
		if(equery.getDepId() != null){
			hql = hql + " and depId like :depId";
		}
		if(StringUtils.isNotBlank(equery.getName())){
			hql = hql +  " and name like :name";
		}
		if(StringUtils.isNotBlank(equery.getTel())){
			hql = hql + " and tel like :tel";
		}
		return hql;
	}
}
