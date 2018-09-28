package com.asu.MovieRecommender.User;

public class Role {

	/*
	 * @author Sabyasachi Mohanty
	 * @since Sept28, Sprint-1
	 * @Task Integrate Spring Security - User Role Object
	 */
	
	
	private int roleId;
	private String userName;
	public Role(int roleId, String userName) {
		super();
		this.roleId = roleId;
		this.userName = userName;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRoleId() {
		return roleId;
	}
	public String getUserName() {
		return userName;
	}
	
	
}
