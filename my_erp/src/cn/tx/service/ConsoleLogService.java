package cn.tx.service;

import cn.tx.model.ConsoleLog;
import cn.tx.query.ConsoleLogQuery;

public interface ConsoleLogService extends BaseService<ConsoleLog,ConsoleLogQuery>{
	public ConsoleLog getConsoleLogByTableAndEntityIdNewest(Integer entityId,
			String tableName);
}
