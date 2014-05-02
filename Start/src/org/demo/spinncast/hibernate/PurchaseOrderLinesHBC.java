package org.demo.spinncast.hibernate;

import java.util.Date;

import org.demo.spinncast.vo.PurchaseOrderLinesVO;
import org.demo.spinncast.vo.PurchaseOrderVO;

public class PurchaseOrderLinesHBC {

	private int poLineId;
	private int serialNo;
	private int partId;
	private double quantity;
	private double rate;
	private double pendingQuantity;

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
		this.poLineId = purchaseOrderLinesVO.getPoLineId();
		this.serialNo = purchaseOrderLinesVO.getSerialNo();
		this.partId = purchaseOrderLinesVO.getPartId();
		this.quantity = purchaseOrderLinesVO.getQuantity();
		this.rate = purchaseOrderLinesVO.getRate();
		this.pendingQuantity = purchaseOrderLinesVO.getPendingQuantity();
	}

	public void update(PurchaseOrderVO purchaseOrderVO) {

	}
}
