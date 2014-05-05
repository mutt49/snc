package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.hibernate.PurchaseOrderLinesHBC;

@ManagedBean(name = "PurchaseOrderLinesVO")
@SessionScoped
public class PurchaseOrderLinesVO {
	private Integer poLineId;
	private Integer serialNo;
	private Integer partId;
	private Integer purchaseOrderId;
	private double quantity;
	private double rate;
	private double pendingQuantity;
	private String partName;
	private double quantityKg;
	private String partDescription;
	private int gradeId;
	private String unit;

	public PurchaseOrderLinesVO(PurchaseOrderLinesHBC result) {
		poLineId = result.getPoLineId ();
		serialNo = result.getSerialNo ();
		partId = result.getPartId ();
		purchaseOrderId = result.getPurchaseOrderId ();
		quantity = result.getQuantity ();
		rate = result.getRate ();
		pendingQuantity = result.getPendingQuantity ();
		partName = "";
		partDescription = "";
		quantityKg = result.getQuantityKg();
		gradeId = result.getGradeId();
		unit = result.getUnit ();
	}

	public PurchaseOrderLinesVO() {
		// TODO Auto-generated constructor stub
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
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

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public double getQuantityKg() {
		return quantityKg;
	}

	public void setQuantityKg(double quantityKg) {
		this.quantityKg = quantityKg;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
