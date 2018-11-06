package cn.tx.controller;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.tx.model.OrderDetail;
import cn.tx.model.OrderModel;
import cn.tx.query.OrderDetailQuery;
import cn.tx.service.OrderDetailService;
import cn.tx.service.OrderModelService;

import com.opensymphony.xwork2.ActionContext;


public class OrderDetailAction extends BaseAction {


	private OrderDetailQuery orderDetailQuery = new OrderDetailQuery();
	
	private OrderDetailService orderDetailService;
	
	private OrderModelService orderModelService;
	
	private OrderModel orderModel = new OrderModel();
	
	private OrderDetail orderDetail = new OrderDetail();
	
	
	
	


	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}

	public OrderModel getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(OrderModel orderModel) {
		this.orderModel = orderModel;
	}

	public OrderDetailQuery getOrderDetailQuery() {
		return orderDetailQuery;
	}

	public void setOrderDetailQuery(OrderDetailQuery orderDetailQuery) {
		this.orderDetailQuery = orderDetailQuery;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	public String orderDetail_orderDetailList(){
		 List<OrderDetail> list = orderDetailService.getDetailByOrderModerId(orderDetail.getOrderId());
		 ActionContext ac =  ServletActionContext.getContext();
		 ac.put("DetailList", list);
		 orderModel =  orderModelService.getObjectById(new Integer(orderDetail.getOrderId()));
		return SUCCESS;
	}
	
	public String orderDetail_input(){
		return SUCCESS;
	}
}
