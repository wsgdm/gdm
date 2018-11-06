package cn.tx.model;

/**
 * RelationRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class RelationRoleMenu implements java.io.Serializable {

	// Fields

	private Integer relationId;
	private Integer roleId;
	private Integer menuId;

	// Constructors

	/** default constructor */
	public RelationRoleMenu() {
	}

	/** full constructor */
	public RelationRoleMenu(Integer roleId, Integer menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	// Property accessors

	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}