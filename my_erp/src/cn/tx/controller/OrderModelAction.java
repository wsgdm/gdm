package cn.tx.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import cn.tx.model.ConsoleLog;
import cn.tx.model.Emp;
import cn.tx.model.OrderDetail;
import cn.tx.model.OrderModel;
import cn.tx.model.Product;
import cn.tx.model.ProductType;
import cn.tx.model.Supplier;
import cn.tx.query.OrderModelQuery;
import cn.tx.service.ConsoleLogService;
import cn.tx.service.EmpService;
import cn.tx.service.OrderModelService;
import cn.tx.service.ProductService;
import cn.tx.service.ProductTypeService;
import cn.tx.service.SupplierService;
import cn.tx.utils.ERPConstants;
import cn.tx.utils.JSONUtils;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class OrderModelAction extends BaseAction {


	private OrderModelQuery orderModelQuery = new OrderModelQuery();
	
	private OrderModelService orderModelService;
	
	private SupplierService supplierService;
	
	private ProductTypeService productTypeService;
	
	private ProductService productService;
	
	private ConsoleLogService consoleLogService;
	
	private EmpService empService;
	
	private OrderModel orderModel = new OrderModel();
	
	private ConsoleLog consoleLog = new ConsoleLog();
	
	private Integer[] productTypeId;
	private Integer[] productId;
	private Integer[] num;

	private String note;
	
	

	public void setConsoleLogService(ConsoleLogService consoleLogService) {
		this.consoleLogService = consoleLogService;
	}

	public ConsoleLog getConsoleLog() {
		return consoleLog;
	}

	public void setConsoleLog(ConsoleLog consoleLog) {
		this.consoleLog = consoleLog;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public Integer[] getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer[] productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer[] getProductId() {
		return productId;
	}

	public void setProductId(Integer[] productId) {
		this.productId = productId;
	}

	public Integer[] getNum() {
		return num;
	}

	public void setNum(Integer[] num) {
		this.num = num;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public OrderModel getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(OrderModel orderModel) {
		this.orderModel = orderModel;
	}

	public OrderModelQuery getOrderModelQuery() {
		return orderModelQuery;
	}

	public void setOrderModelQuery(OrderModelQuery orderModelQuery) {
		this.orderModelQuery = orderModelQuery;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}

	public String orderModel_list(){
		Page page = orderModelService.creatPage(orderModelQuery, super.list);
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("page", page);
		return SUCCESS;
	}
	
	public String orderModel_audit(){
		
		return SUCCESS;
	}
	public String orderModel_update(){
		orderModel = orderModelService.getObjectById(orderModel.getOrderId());
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("suppliers", list);
		note = consoleLogService.getConsoleLogByTableAndEntityIdNewest(
				orderModel.getOrderId(), "order_model").getNote();
		return SUCCESS;
	}
	
	public String orderModel_checkList(){
		Page page = orderModelService.creatPage(orderModelQuery, super.list);
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("page", page);
		return SUCCESS;
	}
	
	public String orderModel_input(){
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("supplierList",list);
		return SUCCESS;
	}
	
	public void ajax_orderModel_getProType(){
		Integer supplierId = orderModel.getSupplierId();
		List<ProductType> list1 = productTypeService.getProNameBySuppId(supplierId);
		
		JSONUtils.printJSONArray(response,list1,new String[]{"supplier"});
	}

	public void ajax_orderModel_getPro(){
		Integer productTypeId = orderModel.getProductTypeId();
		List<Product> list1 = productService.getProNameByProTypeId(productTypeId);
		
		JSONUtils.printJSONArray(response,list1,new String[]{"productType"});
	}
	
	public void ajax_orderModel_getProductPrice() throws IOException{
		Product p =  productService.getObjectById(orderModel.getProductId());
		response.getWriter().write(p.getInPrice() + "");
		
	}

	public void ajax_orderModel_add() throws IOException{
		ActionContext ac =  ActionContext.getContext();
		 Map<String, Object> session1 = ac.getSession();
		OrderModel odm = new OrderModel();
		odm.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		String user = (String) session1.get("user");
		Emp emp = empService.getEmpByname(user);
		odm.setCreater(emp.getEmpId());
		odm.setCreateTime(new Date());
		odm.setOrderCreat(emp);
		odm.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		odm.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		Integer supplierId = orderModel.getSupplierId();
		odm.setSupplier(supplierService.getObjectById(supplierId));
		
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		int totalNum = 0;
		double totalPrice = 0;
		for(int i = 0; i < productId.length; i++){
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setDetailNum(num[i]);
			Double price =(productService.getObjectById(productId[i])).getInPrice();
			orderDetail.setDetailPrice(price);
			orderDetail.setSurplus(num[i]);
			orderDetail.setProduct(productService.getObjectById(productId[i]));
			ods.add(orderDetail);
			totalNum = totalNum + num[i];
			totalPrice = totalPrice + num[i]*price;
		}
		odm.setTotalNum(totalNum);
		odm.setTotalPrice(totalPrice);
		odm.setDetails(ods);
		orderModelService.save(odm);
		response.getWriter().write("OK");
		
		Set<OrderDetail> i = orderModel.getDetails();
		
		
	}
	
	public void ajax_orderModel_update() throws IOException{
		ActionContext ac =  ActionContext.getContext();
		 Map<String, Object> session1 = ac.getSession();
		orderModel.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		String user = (String) session1.get("user");
		Emp emp = empService.getEmpByname(user);
		orderModel.setCreater(emp.getEmpId());
		orderModel.setCreateTime(new Date());
		orderModel.setOrderCreat(emp);
		orderModel.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		orderModel.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		Integer supplierId = orderModel.getSupplier().getSupplierId();
		orderModel.setSupplier(supplierService.getObjectById(supplierId));
		
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		int totalNum = 0;
		double totalPrice = 0;
		for(int i = 0; i < productId.length; i++){
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setDetailNum(num[i]);
			Double price =(productService.getObjectById(productId[i])).getInPrice();
			orderDetail.setDetailPrice(price);
			orderDetail.setSurplus(num[i]);
			orderDetail.setProduct(productService.getObjectById(productId[i]));
			ods.add(orderDetail);
			totalNum = totalNum + num[i];
			totalPrice = totalPrice + num[i]*price;
		}
		orderModel.setTotalNum(totalNum);
		orderModel.setTotalPrice(totalPrice);
		orderModel.setDetails(ods);
		orderModelService.update(orderModel);
		response.getWriter().write("OK");
		
		
	}
	
	public void ajax_orderModel_audit() throws IOException{
		OrderModel od = orderModelService.getObjectById(orderModel.getOrderId());
		od.setOrderState(orderModel.getOrderState());
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = (String) session.get("user");
		Integer empId = empService.getEmpByname(username).getEmpId();
		od.setChecker(empId);
		od.setCheckTime(new Date());
		orderModelService.update(od);
		
		consoleLog.setEmpId(empId);
		consoleLog.setEntityId(orderModel.getOrderId());
		consoleLog.setNote(note);
		consoleLog.setOptType("审核采购单");
		consoleLog.setOptTime(new Timestamp(new Date().getTime()));
		consoleLog.setTableName("order_model");
		consoleLogService.save(consoleLog);
		
		response.getWriter().write("OK");
		
	}
	
	
	
}
