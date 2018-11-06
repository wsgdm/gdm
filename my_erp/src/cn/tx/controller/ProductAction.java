package cn.tx.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.tx.model.Product;
import cn.tx.model.ProductType;
import cn.tx.model.Supplier;
import cn.tx.query.ProductQuery;
import cn.tx.service.ProductService;
import cn.tx.service.ProductTypeService;
import cn.tx.service.SupplierService;
import cn.tx.utils.JSONUtils;
import cn.tx.utils.Page;


public class ProductAction extends BaseAction {


	private ProductQuery productQuery = new ProductQuery();
	
	private ProductService productService;
	
	private ProductTypeService productTypeService;
	

	private SupplierService supplierService;
	
	private Product product = new Product();
	
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ProductQuery getProductQuery() {
		return productQuery;
	}

	public void setProductQuery(ProductQuery productQuery) {
		this.productQuery = productQuery;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String product_list(){
		
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		
		Page page = productService.creatPage(productQuery, super.list);
		ac.put("page", page);
		
		return SUCCESS;
	}
	
	public String product_input(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		return SUCCESS;
	}
	
	public String product_delete(){
		productService.delete(product.getProductId());
		return LIST;
	}
	public String product_update(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		
		product = productService.getObjectById(product.getProductId());
		return SUCCESS;
	}
	
	
	public void ajax_product_getProName(){
		Integer supplierId = productQuery.getSupplierId();
		List<ProductType> list1 = productTypeService.getProNameBySuppId(supplierId);
		List<Product> list = new ArrayList<Product>();
		for(ProductType ty : list1){
			list.addAll(productService.getProNameByProTypeId(ty.getProductTypeId()));
		}
		JSONUtils.printJSONArray(response,list,new String[]{"productType"});
	}
	
	public void ajax_product_getProTypeName(){
		Integer supplierId = productQuery.getSupplierId();
		List<ProductType> list1 = productTypeService.getProNameBySuppId(supplierId);
	
		JSONUtils.printJSONArray(response,list1,new String[]{"supplier"});
	}
	
	public void ajax_product_vaildProName() throws IOException{
		String res = "OK";
		List<Product> list = productService.getProNameByProTypeId(product.getProductTypeId());
		for(Product p : list){
			if(p.getName().equals(product.getName())){
				res = "NoOK";
			}
		}
		response.getWriter().write(res);
	}
	
	public void ajax_product_add() throws IOException{
		productService.save(product);
		response.getWriter().write("OK");
	}
	public void ajax_product_update() throws IOException{
		Product p = productService.getObjectById(product.getProductId());
		try {
			BeanUtils.copyProperties(p, product);
			productService.update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write("OK");
	}
}
