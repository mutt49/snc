package org.demo.spinncast.hibernate;

import java.util.Date;

import org.demo.spinncast.vo.InvoiceHeaderVO;

public class InvoiceHeaderHBC {

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

	public InvoiceHeaderHBC() {

	}

	public InvoiceHeaderHBC(InvoiceHeaderVO invHdr) {
		invId = invHdr.getInvId();
		invDate = invHdr.getInvDate(); 
		customerId = invHdr.getCustomerId();
		purchaseOrderId = invHdr.getPurchaseOrderId();
		purchaseOrderDate = invHdr.getPurchaseOrderDate();
		delivaryChallanNo = invHdr.getDelivaryChallanNo();
		delivaryChallanDate = invHdr.getDelivaryChallanDate();
		modeOfTransport = invHdr.getModeOfTransport();
		vehicleNo = invHdr.getVehicleNo();
		netTotalAmount = invHdr.getNetTotalAmount();
		freightInsurance = invHdr.getFreightInsurance();
		grandTotal = invHdr.getGrandTotal();
	}

	public void update(InvoiceHeaderVO invHdr) {
		invId = invHdr.getInvId();
		invDate = invHdr.getInvDate(); 
		customerId = invHdr.getCustomerId();
		purchaseOrderId = invHdr.getPurchaseOrderId();
		purchaseOrderDate = invHdr.getPurchaseOrderDate();
		delivaryChallanNo = invHdr.getDelivaryChallanNo();
		delivaryChallanDate = invHdr.getDelivaryChallanDate();
		modeOfTransport = invHdr.getModeOfTransport();
		vehicleNo = invHdr.getVehicleNo();
		netTotalAmount = invHdr.getNetTotalAmount();
		freightInsurance = invHdr.getFreightInsurance();
		grandTotal = invHdr.getGrandTotal();
	}

	
}
