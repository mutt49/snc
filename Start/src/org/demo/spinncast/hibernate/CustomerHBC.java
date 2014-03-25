package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.CustomerVO;

public class CustomerHBC {
	private Integer customer_id;
	private String customer_name;
	private String customer_address;
	private String cst_no;
	private String bst_no;
	private String ecc_no;
	private String octroi_no;
	private String vendor_code;
	
	
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


	public CustomerHBC() {

	}

	public CustomerHBC(CustomerVO customerVO) {
		customer_name = customerVO.getCustomer_name();
		customer_address = customerVO.getCustomer_address();
		cst_no = customerVO.getCst_no();
		bst_no = customerVO.getBst_no();
		ecc_no = customerVO.getEcc_no();
		octroi_no = customerVO.getOctroi_no();
		vendor_code = customerVO.getVendor_code();
		customer_id = customerVO.getCustomer_id();
	}

	public void update(CustomerVO customerVO) {
		customer_name = customerVO.getCustomer_name();
		customer_address = customerVO.getCustomer_address();
		cst_no = customerVO.getCst_no();
		bst_no = customerVO.getBst_no();
		ecc_no = customerVO.getEcc_no();
		octroi_no = customerVO.getOctroi_no();
		vendor_code = customerVO.getVendor_code();
	}
}
