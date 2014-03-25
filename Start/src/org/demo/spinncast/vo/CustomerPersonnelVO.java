package org.demo.spinncast.vo;

import org.demo.spinncast.hibernate.CustomerPersonnelHBC;

public class CustomerPersonnelVO {
	private Integer personnel_id;
	private Integer customer_id;
	private String name;
	private String department;
	private String phone;
	private String email;
	public CustomerPersonnelVO(CustomerPersonnelHBC customerPersonnelHBC) {
		personnel_id = customerPersonnelHBC.getPersonnel_id();
		customer_id = customerPersonnelHBC.getCustomer_id();
		name = customerPersonnelHBC.getName();
		department = customerPersonnelHBC.getDepartment();
		phone = customerPersonnelHBC.getPhone();
		email = customerPersonnelHBC.getEmail();
	}
	public CustomerPersonnelVO () {
		
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPersonnel_id() {
		return personnel_id;
	}
	public void setPersonnel_id(Integer personnel_id) {
		this.personnel_id = personnel_id;
	}
}
