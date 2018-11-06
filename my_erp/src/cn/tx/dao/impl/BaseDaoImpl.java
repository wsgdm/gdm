package cn.tx.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tx.dao.BaseDao;
import cn.tx.utils.Page;

public abstract class BaseDaoImpl<T,Q> extends HibernateDaoSupport implements BaseDao<T,Q> {

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
		
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObjectById(Integer id) {
		return (T) this.getHibernateTemplate().get(getGenericClass(), id);
		
	}

	@Override
	public void delete(Integer id) {
		Object obj = getObjectById(id);
		this.getHibernateTemplate().delete(obj);
		
	}

	public abstract String creatHql(Q q);
	public abstract String creatHqlCount(Q q);

	@SuppressWarnings("unchecked")
	public  List<T> queryObjByCondition(final Q q,final List<String> exclude){
		
		List<T> objList = getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,
					SQLException {
				String myhql = creatHql(q);
				Query query = session.createQuery(myhql);
				DynamicParam(query,q,exclude);
				Class<? extends Object> class1 = q.getClass();
				Object startNum = null;
				try {
					Field field = class1.getDeclaredField("startNum");
					field.setAccessible(true);
					startNum = field.get(q);
					if(startNum == null){
						startNum = 0;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				Integer pageSize = (new Page()).getPageSize();
				List<T> list1 = (List<T>)query.setFirstResult((Integer)startNum).setMaxResults(pageSize).list();
				return list1;
			}	
		});
		return objList;
	}

	
	public final void DynamicParam(Query query, Q q,List<String> exclude){
		
		@SuppressWarnings("unchecked")
		Class<? extends Q> class1 =  (Class<? extends Q>) q.getClass();
		Field[] field =  class1.getDeclaredFields();
		Field[] field1 =  class1.getSuperclass().getDeclaredFields();
		List<Field> list1 = Arrays.asList(field);
		List<Field> list2 = Arrays.asList(field1);
		List<Field> mylist = new ArrayList<Field>();
		mylist.addAll(list1);
		mylist.addAll(list2);
		
		for(Field fi : mylist){
		
			String fileName = fi.getName();
			Object var = null;
			if(exclude != null && exclude.contains(fileName)){
				continue;
			}
			try {
				fi.setAccessible(true);
				var = fi.get(q);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(var != null){
				
				if(var.getClass() == String.class){
					if(StringUtils.isNotBlank((String) var)){
						query.setParameter(fileName, "%" + var + "%");
					}
					
				}else{
					query.setParameter(fileName,var);
				}
				
			}

			
		}
	} 
	
	
	public final Class<?> getGenericClass(){
		Type genericSuperclass = (Type) this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) genericSuperclass;
		Type[] types = pt.getActualTypeArguments();
		Class<?> class1 = (Class<?>) types[0];
		return class1;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(){
		Class<?> class1 = getGenericClass();
		String className = class1.getName();
		String hql = "from " + className;
		return this.getHibernateTemplate().find(hql);
		
	}
	

	public Long queryObjByCount(final Q q,final List<String> exclude){
		long objLong = getHibernateTemplate().execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				String myhql = creatHqlCount(q);
				Query query = session.createQuery(myhql);
				DynamicParam(query,q,exclude);
				Long count = (Long) query.uniqueResult();
				return count;
			}	
		});
		return objLong;
		
		
	}
}
