package cn.tx.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import cn.tx.model.Dep;
import cn.tx.model.Supplier;
import cn.tx.query.SupplierQuery;
import cn.tx.service.SupplierService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class SupplierAction extends BaseAction {


	private SupplierQuery supplierQuery = new SupplierQuery();
	
	private SupplierService supplierService;
	
	private Supplier supplier = new Supplier();
	
	

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplierQuery getSupplierQuery() {
		return supplierQuery;
	}

	public void setSupplierQuery(SupplierQuery supplierQuery) {
		this.supplierQuery = supplierQuery;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public String supplier_list(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("list", list);
		
		Page page = supplierService.creatPage(supplierQuery,super.list);
		ac.put("page",page);
		return SUCCESS;
	}
	
	public String supplier_input(){
		return SUCCESS;
	}
	
	public String supplier_update(){
		supplier = supplierService.getObjectById(supplier.getSupplierId());
		return SUCCESS;
	}
	
	public String supplier_delete(){
		supplierService.delete(supplier.getSupplierId());
		return LIST;
	}
	
	public void ajax_supplier_validName() throws IOException{
		boolean bool = supplierService.getEmpByName(supplier.getName());
		if(bool){
			response.getWriter().write("NoOK");
		}else{
			response.getWriter().write("OK");
		}
	}
	public void ajax_supplier_addSupplier() throws IOException{
		supplierService.save(supplier);
		response.getWriter().write("OK");
	}
	
	public void ajax_supplier_updateSupplier() throws IOException{
		Supplier supplier1 = supplierService.getObjectById(supplier.getSupplierId());
		try {
			BeanUtils.copyProperties(supplier1, supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		supplierService.update(supplier1);
		response.getWriter().write("OK");
	}
}
