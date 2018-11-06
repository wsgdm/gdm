package cn.tx.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.EmpDao;
import cn.tx.model.Emp;
import cn.tx.query.EmpQuery;

public class EmpDaoImpl extends BaseDaoImpl<Emp,EmpQuery> implements EmpDao{

	@Override
	public String creatHql(EmpQuery equery) {
		String hql = "from Emp where 1=1";
		return hql + creat(equery) + "order by empId desc";

	}

	@Override
	public String creatHqlCount(EmpQuery equery) {
		String hql = "select count(empId) from Emp where 1=1";
		return hql + creat(equery);
		
	}
	
	public String creat(EmpQuery equery){
		String hql = "";
		if(StringUtils.isNotBlank(equery.getEmail())){
			hql = hql + " and email like :email";
		}
		if(StringUtils.isNotBlank(equery.getName())){
			hql = hql +  " and name like :name";
		}
		if(StringUtils.isNotBlank(equery.getTel())){
			hql = hql + " and tel like :tel";
		}
		if(StringUtils.isNotBlank(equery.getUsername())){
			hql = hql + " and username like :username";	
		}
		if(equery.getGender() != null){
			hql = hql + " and gender like :gender";	
		}
		
		if(equery.getStartbirth() != null){
			hql = hql + " and birthday >= :startbirth";	
		}
		if(equery.getEndbirth() != null){
			hql = hql + " and birthday <= :endbirth";
		}
		if(equery.getDepId()!= null){
			hql = hql + " and dep.depId = :depId";
		}
		return hql;
	}

	@Override
	public boolean getEmpByUname(String username) {
		String hql = "from Emp where 1=1 and username = ?";
		List<?> list = this.getHibernateTemplate().find(hql, username);
		if(list.size() > 0){
			//true表示有数据
			return true;
		}
		return false;
	}
	
	public Emp getEmpByname(String username) {
		String hql = "from Emp where 1=1 and username = ?";
		List<Emp> list =  this.getHibernateTemplate().find(hql, username);
		Emp emp = list.get(0);
		return emp;
	}

	@Override
	public boolean getEmpByUnameAndPassword(String username, String password) {
		String hql = "from Emp where username = ? and password = ?";
		List<Emp> emp = this.getHibernateTemplate().find(hql, username,password);
		if(emp.isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}

}
