package org.demo.spinncast.hibernate;

import java.util.Date;

import org.demo.spinncast.vo.PurchaseOrderVO;

public class PurchaseOrderHBC {

	private int purchaseOrderId;
	private int purchaseOrderNo;
	private int customerId;
	private Date purchaseOrderDate;

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

	public PurchaseOrderHBC() {

	}

	public PurchaseOrderHBC(PurchaseOrderVO purchaseOrderVO) {
		this.purchaseOrderId = purchaseOrderVO.getPurchaseOrderId();
		this.purchaseOrderNo = purchaseOrderVO.getPurchaseOrderNo();
		this.customerId = purchaseOrderVO.getCustomerId();
		this.purchaseOrderDate = purchaseOrderVO.getPurchaseOrderDate();
	}

	public void update(PurchaseOrderVO purchaseOrderVO) {

	}
}
