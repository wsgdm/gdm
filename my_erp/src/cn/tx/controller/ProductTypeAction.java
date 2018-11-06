package cn.tx.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import cn.tx.model.ProductType;
import cn.tx.model.Supplier;
import cn.tx.query.ProductTypeQuery;
import cn.tx.service.ProductTypeService;
import cn.tx.service.SupplierService;
import cn.tx.utils.JSONUtils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class ProductTypeAction extends BaseAction {


	private ProductTypeQuery productTypeQuery = new ProductTypeQuery();
	
	private ProductTypeService productTypeService;
	
	private SupplierService supplierService;
	
	private ProductType productType = new ProductType();
	
	


	public ProductTypeAction() {
		super();
		list.add("supplier");
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ProductTypeQuery getProductTypeQuery() {
		return productTypeQuery;
	}

	public void setProductTypeQuery(ProductTypeQuery productTypeQuery) {
		this.productTypeQuery = productTypeQuery;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public String productType_list(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		if(productTypeQuery.getSupplier() != null){
			productTypeQuery.setSupplierId(productTypeQuery.getSupplier().getSupplierId());
		}
		
		
		Page page = productTypeService.creatPage(productTypeQuery, super.list);
		ac.put("page",page);
		
		return SUCCESS;
	}
	
	
	public String productType_input(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		return SUCCESS;
	}
	

	public String productType_update(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		
		productType = productTypeService.getObjectById(productType.getProductTypeId());
		return SUCCESS;
	}
	
	public String productType_delete(){
		productTypeService.delete(productType.getProductTypeId());
		return LIST;
	}
	
	public void ajax_productType_getProName() throws IOException{
		Integer supplierId = productTypeQuery.getSupplierId();
		List<ProductType> list1 = productTypeService.getProNameBySuppId(supplierId);
		
		JSONUtils.printJSONArray(response,list1,new String[]{"supplier","products"});

	}
	
	public void ajax_productType_add() throws IOException{
		productType.setSupplierId(productType.getSupplier().getSupplierId());
		boolean bool = productTypeService.isExistByProNameAndSuppId(productType.getName(), productType.getSupplierId());
		if(!bool){
			productTypeService.save(productType);
			response.getWriter().write("OK");
		}else{
			response.getWriter().write("NoOK");
		}
	}
	public void ajax_productType_update() throws IOException{
		productType.setSupplierId(productType.getSupplier().getSupplierId());
		boolean bool = productTypeService.isExistByProNameAndSuppId(productType.getName(), productType.getSupplierId());
		if(!bool){
			ProductType p1 =productTypeService.getObjectById(productType.getProductTypeId());
			try {
				BeanUtils.copyProperties(p1, productType);
			} catch (Exception e) {
				e.printStackTrace();
			}

			productTypeService.update(p1);;
			response.getWriter().write("OK");
		}else{
			response.getWriter().write("NoOK");
		}
	
	}
	
}
