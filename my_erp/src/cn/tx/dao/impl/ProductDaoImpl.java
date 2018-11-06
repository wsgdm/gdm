package cn.tx.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.ProductDao;
import cn.tx.model.Product;
import cn.tx.query.ProductQuery;
import cn.tx.query.ProductTypeQuery;

public class ProductDaoImpl extends BaseDaoImpl<Product,ProductQuery> implements ProductDao{


	@Override
	public String creatHql(ProductQuery equery) {
		
		String hql = "from Product t where 1=1";
		return hql + creat(equery) + " order by t.productId desc";

	}

	@Override
	public String creatHqlCount(ProductQuery equery) {
		
		String hql = "select count(t.productId) from Product t where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(ProductQuery equery){
		
		String hql = "";
		if(equery.getSupplierId() != null){
			hql = hql +  " and t.productType.supplier.supplierId = :supplierId";
		}
		if(equery.getProductId() != null){
			hql = hql + " and t.productId = :productId";
		}
		if(StringUtils.isNotBlank(equery.getProducer())){
			hql = hql + " and t.producer like :producer";
		}
		if(StringUtils.isNotBlank(equery.getUnit())){
			hql = hql + " and t.unit like :unit";
		}
		if(equery.getMinInPrice() != null){
			hql = hql + " and t.inPrice >= :minInPrice";
		}
		if(equery.getMaxInPrice() != null){
			hql = hql + " and t.inPrice <= :maxInPrice";
		}
		if(equery.getMinOutPrice() != null){
			hql = hql + " and t.outPrice >= :minOutPrice";
		}
		if(equery.getMaxOutPrice() != null){
			hql = hql + " and t.outPrice <= :maxOutPrice";
		}
		return hql;
	}

	@Override
	public List<Product> getProNameByProTypeId(Integer proTypeId) {
		String hql = "from Product t where t.productType.productTypeId =? ";
		List<Product> list = this.getHibernateTemplate().find(hql,proTypeId);
		return list;
	}


}
