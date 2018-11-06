package cn.tx.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.SupplierDao;
import cn.tx.model.ProductType;
import cn.tx.model.Supplier;
import cn.tx.query.SupplierQuery;

public class SupplierDaoImpl extends BaseDaoImpl<Supplier,SupplierQuery> implements SupplierDao{


	@Override
	public String creatHql(SupplierQuery equery) {
		String hql = "from Supplier where 1=1";
		return hql + creat(equery);
		

	}

	@Override
	public String creatHqlCount(SupplierQuery equery) {
		String hql = "select count(supplierId) from Supplier where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(SupplierQuery equery){
		String hql = "";
		if(equery.getSupplierId() != null){
			hql = hql + " and supplierId like :supplierId";
		}
		if(StringUtils.isNotBlank(equery.getTel())){
			hql = hql +  " and tel like :tel";
		}
		if(StringUtils.isNotBlank(equery.getContact())){
			hql = hql + " and contact like :contact";
		}

		if(equery.getNeeds() != null){
			hql = hql + " and needs like :needs";	
		}
		return hql;
	
	}

	@Override
	public boolean getEmpByName(String name) {
		String hql = "from Supplier where name = ? ";
		List<Supplier> list = this.getHibernateTemplate().find(hql, name);
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}



}
