package cn.tx.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.ConsoleLogDao;
import cn.tx.model.ConsoleLog;
import cn.tx.query.ConsoleLogQuery;

public class ConsoleLogDaoImpl extends BaseDaoImpl<ConsoleLog,ConsoleLogQuery> implements ConsoleLogDao{


	@Override
	public String creatHql(ConsoleLogQuery equery) {
		
		return null;

	}

	@Override
	public String creatHqlCount(ConsoleLogQuery equery) {
		
		return null;
		
	}
	
	public String creat(ConsoleLogQuery equery){
	
		return null;
	}

	@Override
	public ConsoleLog getConsoleLogByTableAndEntityIdNewest(Integer entityId,
			String tableName) {
		
		String hql = "from ConsoleLog where entityId = ? and tableName = ?";
		List<ConsoleLog> list = this.getHibernateTemplate().find(hql,entityId,tableName);
		Timestamp max = list.get(0).getOptTime();
		int flag = 0;
		for(int i = 1; i < list.size(); i++){
			Timestamp date = list.get(i).getOptTime();
			if(date.after(max)){
				max = date;
				flag = i;
			}			
		}
		return list.get(flag);
	}

}
