package cn.tx.dao.impl;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.RoleDao;
import cn.tx.model.Role;
import cn.tx.query.RoleQuery;

public class RoleDaoImpl extends BaseDaoImpl<Role,RoleQuery> implements RoleDao{


	@Override
	public String creatHql(RoleQuery equery) {
		String hql = "from Role where 1=1";
		return hql + creat(equery);
		

	}

	@Override
	public String creatHqlCount(RoleQuery equery) {
		
		String hql = "select count(roleId) from Role where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(RoleQuery equery){
	
		String hql = "";
		if(StringUtils.isNotBlank(equery.getName())){
			hql = hql +  " and name like :name";
		}
		if(StringUtils.isNotBlank(equery.getCode())){
			hql = hql + " and code like :code";
		}
		return hql;
	}


}
