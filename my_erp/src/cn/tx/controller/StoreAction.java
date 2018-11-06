package cn.tx.controller;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.tx.model.OrderModel;
import cn.tx.model.Store;
import cn.tx.query.OrderModelQuery;
import cn.tx.query.StoreQuery;
import cn.tx.service.OrderModelService;
import cn.tx.service.StoreService;
import cn.tx.utils.Page;

import com.opensymphony.xwork2.ActionContext;


public class StoreAction extends BaseAction {


	private StoreQuery storeQuery = new StoreQuery();
	
	private OrderModelQuery orderModelQuery = new OrderModelQuery();
	
	private OrderModel orderModel = new OrderModel();
	
	private StoreService storeService;

	private OrderModelService orderModelService;

	private Integer productNum;
	
	private Integer productId;
	
	private Integer orderDetailId;
	

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public OrderModel getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(OrderModel orderModel) {
		this.orderModel = orderModel;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}

	public OrderModelQuery getOrderModelQuery() {
		return orderModelQuery;
	}

	public void setOrderModelQuery(OrderModelQuery orderModelQuery) {
		this.orderModelQuery = orderModelQuery;
	}

	public StoreQuery getStoreQuery() {
		return storeQuery;
	}

	public void setStoreQuery(StoreQuery storeQuery) {
		this.storeQuery = storeQuery;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public String store_list(){
		Page page = storeService.creatPage(storeQuery, super.list);
		ActionContext ac = ActionContext.getContext();
		ac.put("page", page);
		return SUCCESS;
	}
	
	public String store_input(){
		return SUCCESS;
	}
	
	public String store_dList(){
		return SUCCESS;
	}
	public String store_inList(){
		List<OrderModel> list = orderModelService.queryObjByCondition(orderModelQuery, super.list);
		
		ActionContext ac = ActionContext.getContext();
		ac.put("orderList", list);
		return SUCCESS;
	}
	public String store_inDetail(){
		orderModel = orderModelService.getObjectById(orderModel.getOrderId());
		return SUCCESS;
	}
	
	public String store_inStock(){
		List<Store> list =  storeService.list();
		ActionContext ac = ActionContext.getContext();
		ac.put("sList", list);
		return SUCCESS;
	}
	
	public void ajax_store_inStock() throws IOException{
		try {
			storeService.updateInstock(storeQuery.getStoreId(), productId, productNum, orderDetailId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write("success");
	}

}
