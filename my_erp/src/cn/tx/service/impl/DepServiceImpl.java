package cn.tx.service.impl;

import cn.tx.dao.DepDao;
import cn.tx.model.Dep;
import cn.tx.query.DepQuery;
import cn.tx.service.DepService;

public class DepServiceImpl extends BaseServiceImpl<Dep,DepQuery> implements DepService {

	
	private DepDao depDao;
	
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
		this.baseDao = depDao;
	}

	
	


}
