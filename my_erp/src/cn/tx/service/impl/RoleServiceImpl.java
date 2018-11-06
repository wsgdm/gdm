package cn.tx.service.impl;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import cn.tx.dao.MenuDao;
import cn.tx.dao.RoleDao;
import cn.tx.model.Menu;
import cn.tx.model.Role;
import cn.tx.query.RoleQuery;
import cn.tx.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role,RoleQuery> implements RoleService {

	
	private RoleDao roleDao;
	
	private MenuDao menuDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		super.baseDao = roleDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void updateGrantPerm(Integer roleId, String permIds) {
		System.out.println(roleId);
		Role role = roleDao.getObjectById(roleId);
		Set<Menu> menus = role.getMenus();
		menus.clear();
		if(StringUtils.isNotBlank(permIds)){
			String[] menuIds = permIds.split(",");
			for(String menuId : menuIds){
				Menu menu = menuDao.getObjectById(new Integer(menuId));
				menus.add(menu);
			}
			
		}
		
	}

}
