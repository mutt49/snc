package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "InvoiceLineItemVO")
@SessionScoped
public class InvoiceLineItemVO {
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
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
}
