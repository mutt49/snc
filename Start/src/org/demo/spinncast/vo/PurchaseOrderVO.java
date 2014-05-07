package org.demo.spinncast.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.hibernate.PurchaseOrderHBC;


@ManagedBean(name = "PurchaseOrderVO")
@SessionScoped
public class PurchaseOrderVO {
	private Integer purchaseOrderId;
	private String purchaseOrderNo;
	private Integer customerId;
	private Date purchaseOrderDate;
	private String customerName;
	private List<PurchaseOrderLinesVO> poLines = new ArrayList<PurchaseOrderLinesVO>();
	private CustomerVO custDetails;
	
	public List<PurchaseOrderLinesVO> getPoLines() {
		return poLines;
	}

	public void setPoLines(List<PurchaseOrderLinesVO> poLines) {
		this.poLines = poLines;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}
	
	public PurchaseOrderVO(){
		custDetails = new CustomerVO();
		
	}
	
	public PurchaseOrderVO(PurchaseOrderHBC purchaseOrderHbc){
		this.purchaseOrderId = purchaseOrderHbc.getPurchaseOrderId();
		this.purchaseOrderNo = purchaseOrderHbc.getPurchaseOrderNo();
		this.purchaseOrderDate = purchaseOrderHbc.getPurchaseOrderDate();
		this.customerId = purchaseOrderHbc.getCustomerId();
	}

	public CustomerVO getCustDetails() {
		return custDetails;
	}

	public void setCustDetails(CustomerVO custDetails) {
		this.custDetails = custDetails;
	}

}
