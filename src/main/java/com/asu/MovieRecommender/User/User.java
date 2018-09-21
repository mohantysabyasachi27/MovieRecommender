package com.asu.MovieRecommender.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;

@Document(collection = "UserDetails")
public class User {

	@Autowired
	private Role role;
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String firstName;
	private String lastName;
	private List<Role> roles;
	private String userName;
	private String userPassword;
	private String userEmailId;
	private String userContactNo;
	private String userDOB;
	private String userCity;
	private String userAddress;
	private String userPinCode;

	public User(String firstName, String lastName, String userName, String userPassword,
			String userEmailId, String userContactNo, String userDOB, String userCity, String userAddress,
			String userPinCode) {
		super();
		role.setUserName(userName);
		role.setRoleId(MovieRecommenderConstants.DEFAULT_ROLE_ID);
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles.add(role);
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmailId = userEmailId;
		this.userContactNo = userContactNo;
		this.userDOB = userDOB;
		this.userCity = userCity;
		this.userAddress = userAddress;
		this.userPinCode = userPinCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserContactNo() {
		return userContactNo;
	}

	public void setUserContactNo(String userContactNo) {
		this.userContactNo = userContactNo;
	}

	public String getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPinCode() {
		return userPinCode;
	}

	public void setUserPinCode(String userPinCode) {
		this.userPinCode = userPinCode;
	}

	public User() {

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Role> getRoles() {
		return roles;
	}

}
