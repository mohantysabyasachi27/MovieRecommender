package com.asu.MovieRecommender.User;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author kumar
 * This class is a POJO class for USer Object and transforms it into a Collection object for MongoDB.
 *
 */
@Document(collection = "UserDetails")
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Set<Role> roles = new HashSet<>();
	private String userName;
	private String userPassword;
	private String userEmailId;
	private String userContactNo;
	private String userDOB;
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
			String userContactNo, String userDOB, String userCity, String userAddress, String userPinCode) {
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

	public static void main(String... args) throws JsonProcessingException {
		User u = new User();
		u.setFirstName("Sunny");
		u.setLastName("Mohanty");
		u.setUserName("test");
		u.setUserPassword("test");
		u.setUserDOB("19920427");
		u.setUserEmailId("smohan31@asu.edu");
		u.setUserContactNo("4806166215");
		ObjectMapper map = new ObjectMapper();
		String value = map.writeValueAsString(u);
		System.out.println(value);
		// {"id":null,"firstName":"Sunny","lastName":"Mohanty","roles":null,"userName":"test","userPassword":"test","userEmailId":"smohan31@asu.edu","userContactNo":"4806166215","userDOB":"19920427","userCity":null,"userAddress":null,"userPinCode":null}

	}
}
