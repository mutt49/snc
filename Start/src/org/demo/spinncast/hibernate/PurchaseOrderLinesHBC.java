package org.demo.spinncast.hibernate;

import java.util.Date;

import org.demo.spinncast.vo.PurchaseOrderLinesVO;
import org.demo.spinncast.vo.PurchaseOrderVO;

public class PurchaseOrderLinesHBC {

	private int poLineId;
	private int serialNo;
	private int partId;
	private int purchaseOrderId;
	private double quantity;
	private double rate;
	private double pendingQuantity;
	private double quantityKg;
	private String partDescription;
	private int gradeId;
	private String unit;
	private double amount;	
	private int invId;
	private double currQuantity;
		
	public double getCurrQuantity() {
		return currQuantity;
	}

	public void setCurrQuantity(double currQuantity) {
		this.currQuantity = currQuantity;
	}

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(int poLineId) {
		this.poLineId = poLineId;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
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

	public double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public PurchaseOrderLinesHBC() {

	}

	public PurchaseOrderLinesHBC(PurchaseOrderLinesVO purchaseOrderLinesVO) {
		if(purchaseOrderLinesVO.getPoLineId() != null)
		this.poLineId = purchaseOrderLinesVO.getPoLineId();
		this.serialNo = purchaseOrderLinesVO.getSerialNo();
		this.partId = purchaseOrderLinesVO.getPartId();
		this.quantity = purchaseOrderLinesVO.getQuantity();
		this.rate = purchaseOrderLinesVO.getRate();
		this.pendingQuantity = purchaseOrderLinesVO.getPendingQuantity();
		this.purchaseOrderId = purchaseOrderLinesVO.getPurchaseOrderId();
		this.partDescription = purchaseOrderLinesVO.getPartDescription();
		this.quantityKg = purchaseOrderLinesVO.getQuantityKg();
		this.gradeId = purchaseOrderLinesVO.getGradeId();
		this.unit = purchaseOrderLinesVO.getUnit();
		this.amount = purchaseOrderLinesVO.getAmount();
		this.invId = purchaseOrderLinesVO.getInvId();
	}

	public void update(PurchaseOrderVO purchaseOrderVO) {

	}

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public double getQuantityKg() {
		return quantityKg;
	}

	public void setQuantityKg(double quantityKg) {
		this.quantityKg = quantityKg;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
