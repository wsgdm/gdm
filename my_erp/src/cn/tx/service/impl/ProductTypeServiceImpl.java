package cn.tx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tx.dao.ProductTypeDao;
import cn.tx.model.ProductType;
import cn.tx.query.ProductTypeQuery;
import cn.tx.service.ProductTypeService;

public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType,ProductTypeQuery> implements ProductTypeService {

	
	private ProductTypeDao productTypeDao;
	
	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
		this.baseDao = productTypeDao;
	}

	@Override
	public List<ProductType> getProNameBySuppId(Integer supplierId) {
		
		return productTypeDao.getProNameBySuppId(supplierId);
	}

	@Override
	public boolean isExistByProNameAndSuppId(String proName, Integer suppId) {
		
		return productTypeDao.isExistByProNameAndSuppId(proName, suppId);
	}

}
