package cn.tx.controller;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.tx.model.Dep;
import cn.tx.model.Emp;
import cn.tx.model.OrderModel;
import cn.tx.model.Supplier;
import cn.tx.query.OrderModelQuery;
import cn.tx.service.DepService;
import cn.tx.service.EmpService;
import cn.tx.service.OrderModelService;
import cn.tx.service.SupplierService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;



public class TransportOrderAction extends BaseAction {

	private OrderModelQuery orderModelQuery = new OrderModelQuery();
	
	private OrderModelService orderModelService;
	
	private SupplierService supplierService;
	
	private OrderModel orderModel = new OrderModel();
	
	private EmpService empService;
	
	
	



	public void setEmpService(EmpService empService) {
		this.empService = empService;
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



	public String transportOrder_taskList(){
		Page page = orderModelService.creatPage(orderModelQuery, super.list);
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("page", page);
		ac.put("suppliers", list);
		return SUCCESS;
	}
	
	public String transportOrder_taskDetail(){
		orderModel = orderModelService.getObjectById(orderModel.getOrderId());
		List<Emp> emp = empService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("emp",emp);
		return SUCCESS;
	}
	
	public String transportOrder_tasks(){
		Page page = orderModelService.creatPage(orderModelQuery, super.list);
		List<Supplier> list = supplierService.list();
		ActionContext ac =  ServletActionContext.getContext();
		ac.put("page", page);
		ac.put("suppliers", list);
		return SUCCESS;
	}
	
	public String transportOrder_taskDetailbuying(){
		orderModel = orderModelService.getObjectById(orderModel.getOrderId());
		return SUCCESS;
	}
	
	public void ajax_transportOrder_assginOrder() throws IOException{
		OrderModel orderModel1 = orderModelService.getObjectById(orderModel.getOrderId());
		Emp emp = empService.getObjectById(orderModel.getCompleter());
		orderModel1.setTransportOrder(emp);
		orderModel1.setOrderState(2);
		orderModelService.update(orderModel1);
		response.getWriter().write("success");
	}
	
	public void ajax_transportOrder_getOrderProduct() throws IOException{
		OrderModel order1 = orderModelService.getObjectById(orderModel.getOrderId());
		order1.setOrderState(3);
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	
	public void ajax_transportOrder_finishTranOrder() throws IOException{
		OrderModel order1 = orderModelService.getObjectById(orderModel.getOrderId());
		order1.setOrderType(3);
		order1.setOrderState(1);
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	

	
	
}
