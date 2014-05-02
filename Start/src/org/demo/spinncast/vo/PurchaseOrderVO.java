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
	private int purchaseOrderId;
	private int purchaseOrderNo;
	private int customerId;
	private Date purchaseOrderDate;
	private String customerName;
	private List<PurchaseOrderLinesVO> poLines = new ArrayList<PurchaseOrderLinesVO>();
 
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

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public int getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(int purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}
	
	public PurchaseOrderVO(){
		
	}
	
	public PurchaseOrderVO(PurchaseOrderHBC purchaseOrderHbc){
		this.purchaseOrderId = purchaseOrderHbc.getPurchaseOrderId();
		this.purchaseOrderNo = purchaseOrderHbc.getPurchaseOrderNo();
		this.purchaseOrderDate = purchaseOrderHbc.getPurchaseOrderDate();
		this.customerId = purchaseOrderHbc.getCustomerId();
	}

}
