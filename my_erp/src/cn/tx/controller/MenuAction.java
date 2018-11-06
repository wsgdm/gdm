package cn.tx.controller;

import org.apache.struts2.ServletActionContext;

import cn.tx.query.MenuQuery;
import cn.tx.service.MenuService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class MenuAction extends BaseAction {


	private MenuQuery menuQuery = new MenuQuery();
	
	private MenuService menuService;
	
	
	


	public MenuQuery getMenuQuery() {
		return menuQuery;
	}

	public void setMenuQuery(MenuQuery menuQuery) {
		this.menuQuery = menuQuery;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String menu_list(){

		return SUCCESS;
	}
	
	public String menu_input(){
		return SUCCESS;
	}
}
