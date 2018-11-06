package cn.tx.service.impl;

import java.util.List;
import java.util.Set;

import cn.tx.dao.EmpDao;
import cn.tx.dao.RoleDao;
import cn.tx.model.Emp;
import cn.tx.model.Role;
import cn.tx.query.EmpQuery;
import cn.tx.service.EmpService;
import cn.tx.utils.Page;

public class EmpServiceImpl extends BaseServiceImpl<Emp,EmpQuery> implements EmpService {

	
	private EmpDao empDao;
	
	private RoleDao roleDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
		this.baseDao = empDao;
	}
	

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	@Override
	public boolean getEmpByUname(String username) {
		
		return empDao.getEmpByUname(username);
	}

	@Override
	public List<Role> queryRole(Integer empId) {
		List<Role> list =roleDao.list();
		Emp emp = empDao.getObjectById(empId);
		Set<Role> roles = emp.getRoles();
		for(Role re : list){
			for(Role re1 : roles){
				if(re.getRoleId().intValue() == re1.getRoleId().intValue()){
					re.setSelect("yes");
				}
			}
		}
		return list;
	}



	@Override
	public void updateEmpRole(Integer empId, String roles) {
		Emp emp = empDao.getObjectById(empId);
		String[]  strs = roles.split(",");
		Set<Role> set = emp.getRoles();
		set.clear();
		for(String str : strs){
			Role role = roleDao.getObjectById(new Integer(str));
			set.add(role);
		}	
		emp.setRoles(set);
		empDao.update(emp);
	}


	@Override
	public boolean getEmpByUnameAndPassword(String username, String password) {
		
		return empDao.getEmpByUnameAndPassword(username, password);
	}


	@Override
	public Emp getEmpByname(String username) {
		
		return empDao.getEmpByname(username);
	}


	

}
