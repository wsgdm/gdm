package cn.tx.query;

import java.util.Date;

import cn.tx.model.OrderModel;

@SuppressWarnings("serial")
public class OrderModelQuery extends OrderModel {	
		
	private Integer pageNo;
	private Integer startNum;
	
	private Integer minTotalNum;
	private Integer maxTotalNum;
	private Double minTotalPrice;
	private Double maxTotalPrice;
	private Date minCreateTime;
	private Date maxCreateTime;
	
	private Date minCheckTime;
	private Date maxCheckTime;
	
	
	private Integer needs;
	
	
	

	public Integer getNeeds() {
		return needs;
	}

	public void setNeeds(Integer needs) {
		this.needs = needs;
	}

	public Date getMinCheckTime() {
		return minCheckTime;
	}

	public void setMinCheckTime(Date minCheckTime) {
		this.minCheckTime = minCheckTime;
	}

	public Date getMaxCheckTime() {
		return maxCheckTime;
	}

	public void setMaxCheckTime(Date maxCheckTime) {
		this.maxCheckTime = maxCheckTime;
	}

	public Integer getMinTotalNum() {
		return minTotalNum;
	}

	public void setMinTotalNum(Integer minTotalNum) {
		this.minTotalNum = minTotalNum;
	}

	public Integer getMaxTotalNum() {
		return maxTotalNum;
	}

	public void setMaxTotalNum(Integer maxTotalNum) {
		this.maxTotalNum = maxTotalNum;
	}

	public Double getMinTotalPrice() {
		return minTotalPrice;
	}

	public void setMinTotalPrice(Double minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}

	public Double getMaxTotalPrice() {
		return maxTotalPrice;
	}

	public void setMaxTotalPrice(Double maxTotalPrice) {
		this.maxTotalPrice = maxTotalPrice;
	}

	public Date getMinCreateTime() {
		return minCreateTime;
	}

	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public Date getMaxCreateTime() {
		return maxCreateTime;
	}

	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
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
