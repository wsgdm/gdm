package cn.tx.controller;

import cn.tx.query.StoreDetailQuery;
import cn.tx.service.StoreDetailService;


public class StoreDetailAction extends BaseAction {


	private StoreDetailQuery storeDetailQuery = new StoreDetailQuery();
	
	private StoreDetailService storeDetailService;
	
	
	


	public StoreDetailQuery getStoreDetailQuery() {
		return storeDetailQuery;
	}

	public void setStoreDetailQuery(StoreDetailQuery storeDetailQuery) {
		this.storeDetailQuery = storeDetailQuery;
	}

	public void setStoreDetailService(StoreDetailService storeDetailService) {
		this.storeDetailService = storeDetailService;
	}

	public String storeDetail_list(){

		return SUCCESS;
	}
	
	public String storeDetail_input(){
		return SUCCESS;
	}
}
