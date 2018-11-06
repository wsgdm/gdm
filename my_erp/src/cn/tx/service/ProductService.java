package cn.tx.service;

import java.util.List;

import cn.tx.model.Product;
import cn.tx.query.ProductQuery;

public interface ProductService extends BaseService<Product,ProductQuery> {
	public List<Product> getProNameByProTypeId(Integer proTypeId);
}
