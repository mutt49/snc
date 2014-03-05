package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.TestCertificateVO;

public class TestCertificateHBC {
	int id;
	String name, description;
	double quantity;
	double rate;
	
	public TestCertificateHBC () {
		
	}
	
	public TestCertificateHBC(TestCertificateVO testCertificateVO) {
		name = testCertificateVO.getName();
		description = testCertificateVO.getDescription();
		quantity = testCertificateVO.getQuantity();
		rate = testCertificateVO.getRate ();
		id = testCertificateVO.getId();
	}

	public void update (TestCertificateVO testCertificateVO) {
		name = testCertificateVO.getName();
		description = testCertificateVO.getDescription();
		quantity = testCertificateVO.getQuantity();
		rate = testCertificateVO.getRate ();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
}
