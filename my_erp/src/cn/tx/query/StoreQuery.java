package cn.tx.query;

import cn.tx.model.Store;

@SuppressWarnings("serial")
public class StoreQuery extends Store {	
		
	private Integer pageNo;


	private Integer startNum;
	
	private String empName;
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

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
