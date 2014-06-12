package org.demo.spinncast.vo;

import org.demo.spinncast.hibernate.UserHBC;

public class UserVO {
	private Integer userId = 0;
	private String userName;
	private String password;
	private String emailId;
	private String userRole;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public UserVO(UserHBC userHBC) {
		userId = userHBC.getUserId();
		userName = userHBC.getUserName();
		password = userHBC.getPassword();
		emailId = userHBC.getEmailId();
		userRole = userHBC.getUserRole();
	}
	
	public UserVO () {
		
	}
	
	
}
