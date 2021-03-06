package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.InvoiceLineItemVO;
import org.demo.spinncast.vo.PurchaseOrderLinesVO;
import org.demo.spinncast.vo.PurchaseOrderVO;

public class InvoiceLineItemHBC {

	private int invLineItemId;
	private int partId;
	private int invId;
	private String pkgDesc;
	private int quantityNo;
	private float quantityKgs;
	private String unit;
	private float rate;
	private float amount;
	private int serialNo;
	private String noOfPkgs;
	private int gradeId;

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getNoOfPkgs() {
		return noOfPkgs;
	}

	public void setNoOfPkgs(String noOfPkgs) {
		this.noOfPkgs = noOfPkgs;
	}

	public int getInvLineItemId() {
		return invLineItemId;
	}

	public void setInvLineItemId(int invLineItemId) {
		this.invLineItemId = invLineItemId;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public String getPkgDesc() {
		return pkgDesc;
	}

	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}

	public int getQuantityNo() {
		return quantityNo;
	}

	public void setQuantityNo(int quantityNo) {
		this.quantityNo = quantityNo;
	}

	public float getQuantityKgs() {
		return quantityKgs;
	}

	public void setQuantityKgs(float quantityKgs) {
		this.quantityKgs = quantityKgs;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public InvoiceLineItemHBC() {

	}
	
	public InvoiceLineItemHBC(PurchaseOrderLinesVO poLines) {
		invId = poLines.getInvId();
		pkgDesc = poLines.getPartDescription();
		noOfPkgs = "";
		partId = poLines.getPartId();
		
		unit = poLines.getUnit();
		rate = Float.parseFloat(poLines.getRate()+"");
		serialNo = poLines.getSerialNo();
		gradeId = poLines.getGradeId();
		if(unit.equalsIgnoreCase("no")){
			amount = Float.parseFloat(poLines.getCurrQuantity()+"") * rate;
			quantityNo = Integer.parseInt(new Double(poLines.getCurrQuantity()).intValue()+"");
			quantityKgs = Float.parseFloat(poLines.getQuantityKg()+"");
		}else{
			amount = Float.parseFloat(poLines.getCurrQuantity()+"") * rate;
			quantityNo = Integer.parseInt(new Double(poLines.getQuantity()).intValue()+"");
			quantityKgs = Float.parseFloat(poLines.getCurrQuantity()+"");
		}
	}

	public InvoiceLineItemHBC(InvoiceLineItemVO invLineItem) {
		invLineItemId = invLineItem.getInvLineItemId();
		partId = invLineItem.getPartId();
		invId = invLineItem.getInvId();
		pkgDesc = invLineItem.getPkgDesc();
		quantityNo = invLineItem.getQuantityNo();
		quantityKgs = invLineItem.getQuantityKgs();
		unit = invLineItem.getUnit();
		rate = invLineItem.getRate();
		amount = invLineItem.getAmount();
		noOfPkgs = invLineItem.getNoOfPkgs();
		serialNo = invLineItem.getSerialNo();
		gradeId = invLineItem.getGradeId();
		System.out.println(serialNo);
	}

	public void update(InvoiceLineItemVO invLineItem) {
		invLineItemId = invLineItem.getInvLineItemId();
		partId = invLineItem.getPartId();
		invId = invLineItem.getInvId();
		pkgDesc = invLineItem.getPkgDesc();
		quantityNo = invLineItem.getQuantityNo();
		quantityKgs = invLineItem.getQuantityKgs();
		unit = invLineItem.getUnit();
		rate = invLineItem.getRate();
		amount = invLineItem.getAmount();
		noOfPkgs = invLineItem.getNoOfPkgs();
		serialNo = invLineItem.getSerialNo();
		System.out.println(serialNo);
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


}
