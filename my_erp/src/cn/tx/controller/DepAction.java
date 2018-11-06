package cn.tx.controller;

import org.apache.struts2.ServletActionContext;

import cn.tx.query.DepQuery;
import cn.tx.service.DepService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class DepAction extends BaseAction {


	private DepQuery depQuery = new DepQuery();
	
	private DepService depService;
	
	
	


	public DepQuery getDepQuery() {
		return depQuery;
	}

	public void setDepQuery(DepQuery depQuery) {
		this.depQuery = depQuery;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public String dep_list(){
		
		ActionContext ac =  ServletActionContext.getContext();
		
		Page page = depService.creatPage(depQuery,super.list);
		ac.put("page",page);
		return SUCCESS;
	}
	
	public String dep_input(){
		return SUCCESS;
	}
}
