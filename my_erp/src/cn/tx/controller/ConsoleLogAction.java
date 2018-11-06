package cn.tx.controller;

import org.apache.struts2.ServletActionContext;

import cn.tx.query.ConsoleLogQuery;
import cn.tx.service.ConsoleLogService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class ConsoleLogAction extends BaseAction {


	private ConsoleLogQuery consoleLogQuery = new ConsoleLogQuery();
	
	private ConsoleLogService consoleLogService;
	
	
	


	public ConsoleLogQuery getConsoleLogQuery() {
		return consoleLogQuery;
	}

	public void setConsoleLogQuery(ConsoleLogQuery consoleLogQuery) {
		this.consoleLogQuery = consoleLogQuery;
	}

	public void setConsoleLogService(ConsoleLogService consoleLogService) {
		this.consoleLogService = consoleLogService;
	}

	public String consoleLog_list(){

		return SUCCESS;
	}
	
	public String consoleLog_input(){
		return SUCCESS;
	}
}
