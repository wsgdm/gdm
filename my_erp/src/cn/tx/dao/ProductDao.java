package cn.tx.dao;

import java.util.List;

import cn.tx.model.Product;
import cn.tx.model.ProductType;
import cn.tx.query.ProductQuery;


public interface ProductDao extends BaseDao<Product, ProductQuery>{
	
	public List<Product> getProNameByProTypeId(Integer proTypeId);
	
}
