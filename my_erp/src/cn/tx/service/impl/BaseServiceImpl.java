package cn.tx.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import cn.tx.dao.BaseDao;
import cn.tx.service.BaseService;
import cn.tx.utils.Page;

public class BaseServiceImpl<T, Q> implements BaseService<T,Q>{

	public BaseDao<T, Q> baseDao;
	

	@Override
	public void save(T t) {
		baseDao.save(t);	
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
		
	}

	@Override
	public T getObjectById(Integer id) {
		
		return (T) baseDao.getObjectById(id);
	}

	@Override
	public void delete(Integer id) {
		baseDao.delete(id);
		
	}

	@Override
	public List<T> queryObjByCondition(Q q,List<String> exclude) {
		
		return baseDao.queryObjByCondition(q,exclude);
	}

	@Override
	public List<T> list() {
		return baseDao.list();
	}

	@Override
	public Long queryObjByCount(Q q, List<String> exclude) {
		
		return baseDao.queryObjByCount(q, exclude);
	}

	@Override
	public Page creatPage(Q q,List<String> exclude) {
		Page page = new Page();
		Class<? extends Object> class1 = q.getClass();
		try {
			Field field = class1.getDeclaredField("pageNo");
			field.setAccessible(true);
			Integer pageNo = (Integer) field.get(q);
			if(pageNo == null){
				pageNo=1;
			}
			Integer startNum = (pageNo-1) * page.getPageSize();
			Field field1 = class1.getDeclaredField("startNum");
			field1.setAccessible(true);
			field1.set(q, startNum);
			List<T> list = baseDao.queryObjByCondition(q,exclude);
			Long totalCount = baseDao.queryObjByCount(q,exclude);
			page.setList(list);
			page.setPageNo(pageNo);
			page.setStartNum(startNum);
			page.setTotalCount(new Integer(totalCount + ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}
	

}
