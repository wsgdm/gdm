package cn.tx.dao;

import java.util.List;

import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;


public interface ProductTypeDao extends BaseDao<ProductType, ProductTypeQuery>{
	
	public List<ProductType> getProNameBySuppId(Integer supplierId);
	
	public boolean isExistByProNameAndSuppId(String proName,Integer suppId);
	

}
