package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "PurchaseOrderLinesVO")
@SessionScoped
public class PurchaseOrderLinesVO {
	private int poLineId;
	private int serialNo;
	private int partId;
	private double quantity;
	private double rate;
	private double pendingQuantity;
	private String partName;

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
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

}
