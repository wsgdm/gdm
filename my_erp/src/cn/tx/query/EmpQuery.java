package cn.tx.query;

import java.util.Date;

import cn.tx.model.Emp;

@SuppressWarnings("serial")
public class EmpQuery extends Emp {

	private Date startbirth;
	
	private Date endbirth;
	
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

	public Date getStartbirth() {
		return startbirth;
	}

	public void setStartbirth(Date startbirth) {
		this.startbirth = startbirth;
	}

	public Date getEndbirth() {
		return endbirth;
	}

	public void setEndbirth(Date endbirth) {
		this.endbirth = endbirth;
	}

	@Override
	public String toString() {
		return "EmpQuery [startbirth=" + startbirth + ", endbirth=" + endbirth
				+ "]";
	}

	

	
	
	
}
