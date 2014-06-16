package org.demo.spinncast.vo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.hibernate.TestCertificateHBC;
import org.demo.spinncast.misc.Size;

@ManagedBean(name = "TestCertificateVO")
@SessionScoped
public class TestCertificateVO {
	private int tcId;
	private int tcNo;
	private Date tcDate;
	private String heatBatchNo;
	private Date heatBatchDate;
	private String poNo;
	private Date poDate;
	private String challanNo;
	private Date challanDate;
	private String vendorName;
	private String grade;
	private String  desc;
	private String otherTests;
	private TestCertificateActualValuesVO actualValues;
	
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	public int getTcNo() {
		return tcNo;
	}
	public void setTcNo(int tcNo) {
		this.tcNo = tcNo;
	}
	public Date getTcDate() {
		return tcDate;
	}
	public void setTcDate(Date tcDate) {
		this.tcDate = tcDate;
	}
	public String getHeatBatchNo() {
		return heatBatchNo;
	}
	public void setHeatBatchNo(String heatBatchNo) {
		this.heatBatchNo = heatBatchNo;
	}
	public Date getHeatBatchDate() {
		return heatBatchDate;
	}
	public void setHeatBatchDate(Date heatBatchDate) {
		this.heatBatchDate = heatBatchDate;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getChallanNo() {
		return challanNo;
	}
	public void setChallanNo(String challanNo) {
		this.challanNo = challanNo;
	}
	public Date getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(Date challanDate) {
		this.challanDate = challanDate;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOtherTests() {
		return otherTests;
	}
	public void setOtherTests(String otherTests) {
		this.otherTests = otherTests;
	}
	public TestCertificateActualValuesVO getActualValues() {
		return actualValues;
	}
	public void setActualValues(TestCertificateActualValuesVO actualValues) {
		this.actualValues = actualValues;
	}
	
	public TestCertificateVO(){
		
	}
	
	public TestCertificateVO(TestCertificateHBC testCertificateHBC){
		tcId = testCertificateHBC.getTcId(); 
		tcNo = testCertificateHBC.getTcNo();
		tcDate = testCertificateHBC.getTcDate();
		heatBatchNo = testCertificateHBC.getHeatBatchNo();
		heatBatchDate = testCertificateHBC.getHeatBatchDate();
		poNo = testCertificateHBC.getPoNo();
		poDate = testCertificateHBC.getPoDate();
		challanNo = testCertificateHBC.getChallanNo();
		challanDate = testCertificateHBC.getChallanDate();
		vendorName = testCertificateHBC.getVendorName();
		grade = testCertificateHBC.getGrade();
		desc = testCertificateHBC.getDesc();
		otherTests = testCertificateHBC.getOtherTests();
	}

}
