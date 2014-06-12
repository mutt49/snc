package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.CcmVO;
import org.demo.spinncast.vo.UserVO;

public class UserHBC {

	private Integer userId;
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

	public UserHBC() {

	}

	public UserHBC(UserVO userVO) {
		userId = userVO.getUserId();
		userName = userVO.getUserName();
		password = userVO.getPassword();
		emailId = userVO.getEmailId();
		userRole = userVO.getUserRole();
	}

	public void update(UserVO userVO) {
		userId = userVO.getUserId();
		userName = userVO.getUserName();
		password = userVO.getPassword();
		emailId = userVO.getEmailId();
		userRole = userVO.getUserRole();
	}
}
