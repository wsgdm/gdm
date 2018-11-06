package cn.tx.query;

import cn.tx.model.ProductType;

@SuppressWarnings("serial")
public class ProductTypeQuery extends ProductType {	
		
	private Integer pageNo;
	private Integer startNum;
	
	

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
}