package cn.tx.service;

import java.util.List;

import cn.tx.query.EmpQuery;
import cn.tx.utils.Page;

public interface BaseService<T,Q> {

	public void save(T t);
	
	public void update(T t);
	
	public T getObjectById(Integer id);
	
	public void delete(Integer id);
	
	public List<T> queryObjByCondition(Q q,List<String> list);
	
	public Long queryObjByCount(Q q,List<String> list);
	
	public List<T> list();
	
	public Page creatPage(Q q,List<String> exclude);
}
