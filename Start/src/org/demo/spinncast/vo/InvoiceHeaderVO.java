package org.demo.spinncast.vo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "InvoiceHeaderVO")
@SessionScoped
public class InvoiceHeaderVO {
	private int invId;
	private Date invDate;
	private int customerId;
	private int purchaseOrderId;
	private Date purchaseOrderDate;
	private int delivaryChallanNo;
	private Date delivaryChallanDate;
	private String modeOfTransport;
	private String vehicleNo;
	private float netTotalAmount;
	private float freightInsurance;
	private float grandTotal;
	private CustomerVO custDetails = new CustomerVO();
	
	public CustomerVO getCustDetails() {
		return custDetails;
	}
	public void setCustDetails(CustomerVO custDetails) {
		this.custDetails = custDetails;
	}
	public int getInvId() {
		return invId;
	}
	public void setInvId(int invId) {
		this.invId = invId;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}
	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}
	public int getDelivaryChallanNo() {
		return delivaryChallanNo;
	}
	public void setDelivaryChallanNo(int delivaryChallanNo) {
		this.delivaryChallanNo = delivaryChallanNo;
	}
	public Date getDelivaryChallanDate() {
		return delivaryChallanDate;
	}
	public void setDelivaryChallanDate(Date delivaryChallanDate) {
		this.delivaryChallanDate = delivaryChallanDate;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public float getNetTotalAmount() {
		return netTotalAmount;
	}
	public void setNetTotalAmount(float netTotalAmount) {
		this.netTotalAmount = netTotalAmount;
	}
	public float getFreightInsurance() {
		return freightInsurance;
	}
	public void setFreightInsurance(float freightInsurance) {
		this.freightInsurance = freightInsurance;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

}
