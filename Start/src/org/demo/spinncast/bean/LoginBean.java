package org.demo.spinncast.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {

	private String userName;

	private String password = "";

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

	public LoginBean() {
		
	}

	public String login(){
		if(userName.equalsIgnoreCase("admin")){
			return "TestCertificateSearch";
		}else{
			return "";
		}
	}
	
	public String logout(){
		 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        return "LoginPage";
	}
	
	public String forgotPassword(){
		
		return "";
	}

	public String reset() {
		userName = "";
		password = "";
		return "LoginPage";
	}

	
}