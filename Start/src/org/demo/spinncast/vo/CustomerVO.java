package org.demo.spinncast.vo;

import java.util.ArrayList;
import java.util.List;

import org.demo.spinncast.hibernate.CustomerHBC;

public class CustomerVO {
	private Integer customer_id;
	private String customer_name;
	private String customer_address;
	private String cst_no;
	private String bst_no;
	private String ecc_no;
	private String octroi_no;
	private String vendor_code;
	
	private List<CustomerPersonnelVO> personnelList;
	
	public CustomerVO () {
		setPersonnelList(new ArrayList<CustomerPersonnelVO>());
	}
	
	public CustomerVO(CustomerHBC customerHBC) {
		customer_id = customerHBC.getCustomer_id();
		customer_name = customerHBC.getCustomer_name();
		customer_address = customerHBC.getCustomer_address();
		cst_no = customerHBC.getCst_no();
		bst_no = customerHBC.getBst_no();
		ecc_no = customerHBC.getEcc_no();
		octroi_no = customerHBC.getOctroi_no();
		vendor_code = customerHBC.getVendor_code();
	}
	
	public String getOctroi_no() {
		return octroi_no;
	}
	public void setOctroi_no(String octroi_no) {
		this.octroi_no = octroi_no;
	}
	public String getVendor_code() {
		return vendor_code;
	}
	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCst_no() {
		return cst_no;
	}
	public void setCst_no(String cst_no) {
		this.cst_no = cst_no;
	}
	public String getBst_no() {
		return bst_no;
	}
	public void setBst_no(String bst_no) {
		this.bst_no = bst_no;
	}
	public String getEcc_no() {
		return ecc_no;
	}
	public void setEcc_no(String ecc_no) {
		this.ecc_no = ecc_no;
	}

	public List<CustomerPersonnelVO> getPersonnelList() {
		return personnelList;
	}

	public void setPersonnelList(List<CustomerPersonnelVO> personnelList) {
		this.personnelList = personnelList;
	}
}
