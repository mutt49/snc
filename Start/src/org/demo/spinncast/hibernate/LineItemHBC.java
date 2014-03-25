package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.LineItemVO;

public class LineItemHBC {

	private Integer line_item_id;
	private String description;
	private Float quantity;
	private Float rate;
	private String status;
	private Integer purchase_order_id;
	private String unit;
	private int partNo;
	
	public int getPartNo() {
		return partNo;
	}

	public void setPartNo(int part_no) {
		this.partNo = part_no;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Integer getOrder_id() {
		return getLine_item_id();
	}

	public void setOrder_id(Integer order_id) {
		this.setLine_item_id(order_id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(Integer purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public Integer getLine_item_id() {
		return line_item_id;
	}

	public void setLine_item_id(Integer line_item_id) {
		this.line_item_id = line_item_id;
	}

	public LineItemHBC() {

	}

	public LineItemHBC(LineItemVO lineItemVO) {
		setLine_item_id(lineItemVO.getId());
		description = lineItemVO.getDescription();
		quantity = lineItemVO.getQuantity();
		rate = lineItemVO.getRate();
		purchase_order_id = lineItemVO.getPurchase_order_id();
		status = lineItemVO.getStatus();
		unit = lineItemVO.getUnit();
		partNo = lineItemVO.getPartNo();
	}

	public void update(LineItemVO lineItemVO) {
		description = lineItemVO.getDescription();
		quantity = lineItemVO.getQuantity();
		rate = lineItemVO.getRate();
		purchase_order_id = lineItemVO.getPurchase_order_id();
		status = lineItemVO.getStatus();
		unit = lineItemVO.getUnit();
		partNo = lineItemVO.getPartNo();
	}

	
}
