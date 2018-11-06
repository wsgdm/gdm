package cn.tx.service;

import java.util.List;

import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType,ProductTypeQuery>{
	
	public List<ProductType> getProNameBySuppId(Integer supplierId);
	
	public boolean isExistByProNameAndSuppId(String proName, Integer suppId);
}
