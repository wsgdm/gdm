package cn.tx.dao.impl;

import cn.tx.dao.MenuDao;
import cn.tx.model.Menu;
import cn.tx.query.MenuQuery;

public class MenuDaoImpl extends BaseDaoImpl<Menu,MenuQuery> implements MenuDao{


	@Override
	public String creatHql(MenuQuery equery) {
		
		String hql = "from Menu t where 1=1 ";
		
		return hql;

	}

	@Override
	public String creatHqlCount(MenuQuery equery) {
		
		return null;
		
	}
	
	public String creat(MenuQuery equery){
	
		return null;
	}

}
