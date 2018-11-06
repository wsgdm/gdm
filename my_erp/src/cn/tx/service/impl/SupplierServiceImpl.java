package cn.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tx.dao.SupplierDao;
import cn.tx.model.Supplier;
import cn.tx.query.SupplierQuery;
import cn.tx.service.SupplierService;

public class SupplierServiceImpl extends BaseServiceImpl<Supplier,SupplierQuery> implements SupplierService {

	
	private SupplierDao supplierDao;
	
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		this.baseDao = supplierDao;
	}

	@Override
	public boolean getEmpByName(String name) {
		
		return supplierDao.getEmpByName(name);
	}

}
