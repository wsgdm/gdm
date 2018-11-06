package cn.tx.dao;

import cn.tx.model.Emp;
import cn.tx.query.EmpQuery;

public interface EmpDao extends BaseDao<Emp, EmpQuery> {
	
	public boolean getEmpByUname( String username);
	
	public boolean getEmpByUnameAndPassword(String username,String password);
	
	public Emp getEmpByname(String username);
}
