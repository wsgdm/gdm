package cn.tx.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import cn.tx.model.Menu;
import cn.tx.model.Role;
import cn.tx.query.RoleQuery;
import cn.tx.service.MenuService;
import cn.tx.service.RoleService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class RoleAction extends BaseAction {


	private RoleQuery roleQuery = new RoleQuery();
	
	private RoleService roleService;
	
	private Role role = new Role();
	
	private MenuService menuService;
	
	private String permIds;
	
	


	public String getPermIds() {
		return permIds;
	}

	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleQuery getRoleQuery() {
		return roleQuery;
	}

	public void setRoleQuery(RoleQuery roleQuery) {
		this.roleQuery = roleQuery;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String role_list(){
		
		ActionContext ac =  ServletActionContext.getContext();
		
		Page page = roleService.creatPage(roleQuery,super.list);
		ac.put("page",page);
		return SUCCESS;
	}
	
	public String role_input(){
		return SUCCESS;
	}
	
	public String role_listperm(){
		//根据roleId查询role对象
				Role role1 = roleService.getObjectById(roleQuery.getRoleId());
				Set<Menu> menus = role1.getMenus();
				
				//获得系统菜单
				Menu rootMenu = menuService.getObjectById(1);
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				
				createTreeData(rootMenu, list, menus);
				JSONArray ja = JSONArray.fromObject(list);
				ActionContext context = ActionContext.getContext();
				context.put("zNodes", ja);
				return SUCCESS;
	}
	
	public void createTreeData(Menu menu, List<Map<String, Object>> list){
		
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				list.add(map);
			}
			Set<Menu> menus = menu.getMenus();
			if(menus != null && menus.size() > 0){
				for(Menu m: menus){
					createTreeData(m, list);
				}
			}
			
			
			
		}
	}

	
	public void createTreeData(Menu menu, List<Map<String, Object>> list, Set<Menu> roleMenus){
		
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				for(Menu m : roleMenus){
					if(m.getMenuId().intValue() == menu.getMenuId().intValue()){
						map.put("checked", true);
						map.put("open", true);
						break;
					}
				}
				
				list.add(map);
			}
			
			/*if(id.intValue() == 1){
				map.put("open", true);
			}*/
			
			Set<Menu> menus = menu.getMenus();
			if(menus != null && menus.size() > 0){
				for(Menu m: menus){
					createTreeData(m, list,roleMenus);
				}
			}
			
			
		}
	}
	
	public String role_update(){		
		role = roleService.getObjectById(role.getRoleId());
		return SUCCESS;
	}
	
	public String role_delete(){		
		roleService.delete(role.getRoleId());
		return LIST;
	}
	
	public void ajax_role_valid() throws IOException{
		String name = roleQuery.getName();
		String code = roleQuery.getCode();
		roleQuery.setName(null);
		List<Role> list = roleService.queryObjByCondition(roleQuery, super.list);
		roleQuery.setName(name);
		roleQuery.setCode(null);
		List<Role> list1 = roleService.queryObjByCondition(roleQuery, super.list);
		roleQuery.setCode(code);
		if (list.isEmpty() && list1.isEmpty()){
			Role role = new Role();
			role.setCode(roleQuery.getCode());
			role.setName(roleQuery.getName());
			roleService.save(role);
			response.getWriter().write("success");
		}else{
			if(!list.isEmpty() && list1.isEmpty()){
				response.getWriter().write("codeExist");
			}else if(!list.isEmpty() && !list1.isEmpty()){
				response.getWriter().write("codenameExist");
			}else{
				response.getWriter().write("nameExist");
			}
		}
		
	}
	
	public void ajax_role_update() throws IOException{
		Role role1 = roleService.getObjectById(role.getRoleId());
		try {
			BeanUtils.copyProperties(role1, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		roleService.update(role1);
		response.getWriter().write("success");
	}
	
	public void ajax_role_grantPerm() throws IOException{
		roleService.updateGrantPerm(roleQuery.getRoleId(), permIds);
		response.getWriter().write("success");
	}
	

}
