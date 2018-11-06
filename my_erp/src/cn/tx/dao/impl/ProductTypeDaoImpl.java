package cn.tx.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.ProductTypeDao;
import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;

public class ProductTypeDaoImpl extends BaseDaoImpl<ProductType,ProductTypeQuery> implements ProductTypeDao{


	@Override
	public String creatHql(ProductTypeQuery equery) {
		
		String hql = "from ProductType t where 1=1";
		return hql + creat(equery) + " order by t.productTypeId desc";

	}

	@Override
	public String creatHqlCount(ProductTypeQuery equery) {
		
		String hql = "select count(t.productTypeId) from ProductType t where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(ProductTypeQuery equery){
	
		String hql = "";
		if(equery.getSupplierId() != null){
			hql = hql +  " and t.supplier.supplierId = :supplierId";
		}
		if(equery.getProductTypeId() != null){
			hql = hql + " and t.productTypeId = :productTypeId";
		}
		return hql;
	}

	@Override
	public List<ProductType> getProNameBySuppId(Integer supplierId) {
		String hql = "from ProductType t where t.supplier.supplierId = ?";	
		return this.getHibernateTemplate().find(hql, supplierId);
	}

	@Override
	public boolean isExistByProNameAndSuppId(String proName, Integer suppId) {
		String hql = "from ProductType t where t.supplier.supplierId = ? and t.name = ?";
		List<ProductType> list = this.getHibernateTemplate().find(hql, suppId,proName);
		if(!list.isEmpty()){
			return true;
		}else{
			return false;
		}
	}


}
