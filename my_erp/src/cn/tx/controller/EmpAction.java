package cn.tx.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.tx.model.Dep;
import cn.tx.model.Emp;
import cn.tx.model.Role;
import cn.tx.query.EmpQuery;
import cn.tx.service.DepService;
import cn.tx.service.EmpService;
import cn.tx.service.RoleService;
import cn.tx.utils.MD5Utils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class EmpAction extends BaseAction {


	private EmpQuery empQuery = new EmpQuery();
	
	private Emp emp = new Emp();
	
	private EmpService empService;
	
	private DepService depService;
	
	private RoleService roleService;
	
	private String RoleIds;
	
	private String verify;
	
	
	
	
	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getRoleIds() {
		return RoleIds;
	}

	public void setRoleIds(String roleIds) {
		RoleIds = roleIds;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public EmpQuery getEmpQuery() {
		return empQuery;
	}

	public void setEmpQuery(EmpQuery empQuery) {
		this.empQuery = empQuery;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public String emp_list(){
		List<Dep> list = depService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("qList", list);
		
		Page page = empService.creatPage(empQuery,super.list);
		ac.put("page",page);
		return SUCCESS;
	}
	
	public String emp_input(){
		List<Dep> list = depService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("qList", list);
		return SUCCESS;
	}
	
	public String emp_update(){
		List<Dep> list = depService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("qList", list);
		
		
		Integer empId = emp.getEmpId();
		emp = empService.getObjectById(empId);
		
		return SUCCESS;
	}
	
	public String emp_delete(){
		empService.delete(emp.getEmpId());
		return LIST;
	}
	
	public String emp_inputRole(){
		ActionContext ac =  ServletActionContext.getContext();	
		List<Role> list = empService.queryRole(emp.getEmpId());
		ac.put("roles", list);
		return SUCCESS;	
	}
	
	public String emp_toLogin(){
		return SUCCESS;	
	}
	
	public String emp_login(){
		ActionContext ac = ServletActionContext.getContext();
		String piccode = (String) session.getAttribute("piccode");
		if(!StringUtils.equalsIgnoreCase(piccode, verify)){
			ac.put("tip", "codeError");
			return LOGIN;
		}
		String password = MD5Utils.md5(emp.getPassword());
		boolean bool = empService.getEmpByUnameAndPassword(emp.getUsername(),password);
		if(!bool){
			ac.put("tip", "unOrpwError");
			return LOGIN;
		}else{
			String username = emp.getUsername();
			ac.getSession().put("user", username);		
			return MAIN;
		}
		
		
	}
	
	
	public void ajax_emp_validUname() throws IOException{
		String username = emp.getUsername();
		boolean bool = empService.getEmpByUname(username);
		if(bool){
			//用户名已经存在
			response.getWriter().write("yes");
		}else{
			response.getWriter().write("no");
		}
	}
	
	public void ajax_emp_add() throws IOException{
		String password = emp.getPassword();
		password = MD5Utils.md5(password);
		emp.setPassword(password);
		empService.save(emp);
		response.getWriter().write("success");	
		
	}
	public void ajax_emp_role() throws IOException{
		empService.updateEmpRole(emp.getEmpId(),RoleIds);
		response.getWriter().write("success");
		
	}
	
	public void ajax_emp_update() throws IOException{
		
		Emp emp1 = empService.getObjectById(emp.getEmpId());
		try {
			BeanUtils.copyProperties(emp1, emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		empService.update(emp1);
		response.getWriter().write("success");
	}
	
	public void ajax_emp_getImage() throws Exception{
	        BufferedImage img = new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);  
	  
	        // 得到该图片的绘图对象    
	  
	        Graphics g = img.getGraphics();  
	  
	        Random r = new Random();  
	  
	        Color c = new Color(200, 150, 255);  
	  
	        g.setColor(c);  
	  
	        // 填充整个图片的颜色    
	  
	        g.fillRect(0, 0, 68, 22);  
	  
	        // 向图片中输出数字和字母    
	  
	        StringBuffer sb = new StringBuffer();  
	        
	  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	  
	        int index, len = ch.length;  
	  
	        for (int i = 0; i < 4; i++) {  
	  
	            index = r.nextInt(len);  
	  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  
	  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // 输出的  字体和大小                      
	  
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //写什么数字，在图片 的什么位置画    
	  
	            sb.append(ch[index]);  
	  
	        }  
	        //把验证码的值放入session中
	        request.getSession().setAttribute("piccode", sb.toString());  
	        //把验证码的图片写回浏览器
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
}
