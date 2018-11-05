package com.asu.MovieRecommender.User;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;

@Document(collection = "UserDetails")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Set<Role> roles = new HashSet<>();
	private String userName;
	private String userPassword;
	private String userEmailId;
	private String userContactNo;
	private Date userDOB;
	private String userCity;
	private String userAddress;
	private String userPinCode;

	public User(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.userPassword = user.getUserPassword();
		this.userEmailId = user.getUserEmailId();
		this.userContactNo = user.getUserContactNo();
		this.userDOB = user.getUserDOB();
		this.userCity = user.getUserCity();
		this.userAddress = user.getUserAddress();
		this.userPinCode = user.getUserPinCode();
		this.roles.add(new Role(MovieRecommenderConstants.DEFAULT_ROLE_ID, userName));
	}

	public User(String firstName, String lastName, String userName, String userPassword, String userEmailId,
			String userContactNo, Date userDOB, String userCity, String userAddress, String userPinCode) {
		super();
		this.roles.add(new Role(MovieRecommenderConstants.DEFAULT_ROLE_ID, userName));
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmailId = userEmailId;
		this.userContactNo = userContactNo;
		this.userDOB = userDOB;
		this.userCity = userCity;
		this.userAddress = userAddress;
		this.userPinCode = userPinCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(Date userDOB) {
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
