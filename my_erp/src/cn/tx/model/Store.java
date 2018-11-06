package cn.tx.model;

import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private Integer storeId;
	private Integer stockman;
	private String name;
	private String address;

	private Emp stockeEmp;
	
	private Set<StoreDetail> storeDetail;
	
	
	
	
	// Constructors

	public Set<StoreDetail> getStoreDetail() {
		return storeDetail;
	}

	public void setStoreDetail(Set<StoreDetail> storeDetail) {
		this.storeDetail = storeDetail;
	}

	public Emp getStockeEmp() {
		return stockeEmp;
	}

	public void setStockeEmp(Emp stockeEmp) {
		this.stockeEmp = stockeEmp;
	}

	/** default constructor */
	public Store() {
	}

	/** full constructor */
	public Store(Integer stockman, String name, String address) {
		this.stockman = stockman;
		this.name = name;
		this.address = address;
	}

	// Property accessors

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStockman() {
		return this.stockman;
	}

	public void setStockman(Integer stockman) {
		this.stockman = stockman;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}