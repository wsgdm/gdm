package cn.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tx.dao.ConsoleLogDao;
import cn.tx.model.ConsoleLog;
import cn.tx.query.ConsoleLogQuery;
import cn.tx.service.ConsoleLogService;

public class ConsoleLogServiceImpl extends BaseServiceImpl<ConsoleLog,ConsoleLogQuery> implements ConsoleLogService {

	
	private ConsoleLogDao consoleLogDao;
	
	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
		this.baseDao = consoleLogDao;
	}

	@Override
	public ConsoleLog getConsoleLogByTableAndEntityIdNewest(Integer entityId,
			String tableName) {

		return consoleLogDao.getConsoleLogByTableAndEntityIdNewest(entityId, tableName);
	}

}
