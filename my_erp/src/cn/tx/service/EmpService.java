package cn.tx.service;

import java.util.List;
import java.util.Set;

import cn.tx.model.Emp;
import cn.tx.model.Role;
import cn.tx.query.EmpQuery;
import cn.tx.utils.Page;

public interface EmpService extends BaseService<Emp,EmpQuery>{

	public boolean getEmpByUname( String username);
	
	public List<Role> queryRole(Integer empId); 
	
	public void updateEmpRole(Integer empId,String roles);
	
	public boolean getEmpByUnameAndPassword(String username, String password);
	
	public Emp getEmpByname(String username);
}
