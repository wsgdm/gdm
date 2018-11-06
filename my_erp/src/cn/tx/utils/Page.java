package cn.tx.utils;

import java.util.List;

public class Page {

	private Integer pageNo = 1;
	
	private Integer pageSize = 5;
	
	private Integer totalCount = 0;
	
	private Integer startNum = 0;
	
	private Integer totalPage = 1;
	
	private List<?> list ;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStartNum() {
		startNum = (pageNo-1) *  pageSize;
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public Integer getTotalPage() {
		totalPage = totalCount/pageSize + 1;
		if(totalCount%pageSize == 0){
			totalPage--;
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	
}
