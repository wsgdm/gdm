package cn.tx.dao;

import java.util.List;

public interface BaseDao<T,Q> {

	public void save(T t);
	
	public void update(T t);
	
	public T getObjectById(Integer id);
	
	public void delete(Integer id);
	
	public List<T> queryObjByCondition(Q q,List<String> exclude);
	
	public Long queryObjByCount(Q q,List<String> exclude);
	
	public List<T> list();
}
