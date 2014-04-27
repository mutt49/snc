package org.demo.spinncast.hibernate;

import java.util.Date;

import org.demo.spinncast.vo.InvoiceHeaderVO;

public class InvoiceHeaderHBC {

	private int invId;
	private Date invDate;
	private int customerId;
	private String purchaseOrderId;
	private Date purchaseOrderDate;
	private String modeOfTransport;
	private String vehicleNo;
	private float netTotalAmount;
	private float freightInsurance;
	private float grandTotal;
	private String tcNo;
	private String irNo;
	private String lrNo;
	private String paymentTerms;
	private float pkgFrwdChg;
	private Date invIssueDate;
	private Date removalDate;
	private float liAmountTotal;
	private float bedRate;
	private float edCessRate;
	private float shsCess;
	private float vatOrCst;
	private String invNo;
	private String deliveryTo;
	private String deliveryAddress;

	public String getDeliveryTo() {
		return deliveryTo;
	}

	public void setDeliveryTo(String deliveryTo) {
		this.deliveryTo = deliveryTo;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public float getBedRate() {
		return bedRate;
	}

	public void setBedRate(float bedRate) {
		this.bedRate = bedRate;
	}

	public float getEdCessRate() {
		return edCessRate;
	}

	public void setEdCessRate(float edCessRate) {
		this.edCessRate = edCessRate;
	}

	public float getShsCess() {
		return shsCess;
	}

	public void setShsCess(float shsCess) {
		this.shsCess = shsCess;
	}

	public float getVatOrCst() {
		return vatOrCst;
	}

	public void setVatOrCst(float vatOrCst) {
		this.vatOrCst = vatOrCst;
	}

	public float getLiAmountTotal() {
		return liAmountTotal;
	}

	public void setLiAmountTotal(float liAmountTotal) {
		this.liAmountTotal = liAmountTotal;
	}

	public float getPkgFrwdChg() {
		return pkgFrwdChg;
	}

	public void setPkgFrwdChg(float pkgFrwdChg) {
		this.pkgFrwdChg = pkgFrwdChg;
	}

	public Date getInvIssueDate() {
		return invIssueDate;
	}

	public void setInvIssueDate(Date invIssueDate) {
		this.invIssueDate = invIssueDate;
	}

	public Date getRemovalDate() {
		return removalDate;
	}

	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}

	public String getIrNo() {
		return irNo;
	}

	public void setIrNo(String irNo) {
		this.irNo = irNo;
	}

	public String getLrNo() {
		return lrNo;
	}

	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
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

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
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
		modeOfTransport = invHdr.getModeOfTransport();
		vehicleNo = invHdr.getVehicleNo();
		netTotalAmount = invHdr.getNetTotalAmount();
		freightInsurance = invHdr.getFreightInsurance();
		grandTotal = invHdr.getGrandTotal();
		tcNo = invHdr.getTcNo();
		irNo = invHdr.getIrNo();
		lrNo = invHdr.getLrNo();
		paymentTerms = invHdr.getPaymentTerms();
		pkgFrwdChg = invHdr.getPkgFrwdChg();
		invIssueDate = invHdr.getInvIssueDate();
		removalDate = invHdr.getRemovalDate();
		liAmountTotal = invHdr.getLiAmountTotal();
		bedRate = invHdr.getBedRate();
		edCessRate = invHdr.getEdCessRate();
		shsCess = invHdr.getShsCess();
		vatOrCst = invHdr.getVatOrCst();
		invNo = invHdr.getInvNo();
		deliveryTo = invHdr.getDeliveryTo();
		deliveryAddress = invHdr.getDeliveryAddress();
	}

	public void update(InvoiceHeaderVO invHdr) {
		invId = invHdr.getInvId();
		invDate = invHdr.getInvDate();
		customerId = invHdr.getCustomerId();
		purchaseOrderId = invHdr.getPurchaseOrderId();
		purchaseOrderDate = invHdr.getPurchaseOrderDate();
		modeOfTransport = invHdr.getModeOfTransport();
		vehicleNo = invHdr.getVehicleNo();
		netTotalAmount = invHdr.getNetTotalAmount();
		freightInsurance = invHdr.getFreightInsurance();
		grandTotal = invHdr.getGrandTotal();
		tcNo = invHdr.getTcNo();
		irNo = invHdr.getIrNo();
		lrNo = invHdr.getLrNo();
		paymentTerms = invHdr.getPaymentTerms();
		pkgFrwdChg = invHdr.getPkgFrwdChg();
		invIssueDate = invHdr.getInvIssueDate();
		removalDate = invHdr.getRemovalDate();
		liAmountTotal = invHdr.getLiAmountTotal();
		bedRate = invHdr.getBedRate();
		edCessRate = invHdr.getEdCessRate();
		shsCess = invHdr.getShsCess();
		vatOrCst = invHdr.getVatOrCst();
		invNo = invHdr.getInvNo();
		deliveryTo = invHdr.getDeliveryTo();
		deliveryAddress = invHdr.getDeliveryAddress();
	}

}
