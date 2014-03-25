package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.PurchaseOrderVO;

public class PurchaseOrderHBC {

	private Integer order_id = 0;
	private String description;
	private Float gross_price;
	private Float net_value;
	private Integer customer_id = 0;
	
	public PurchaseOrderHBC () {
		
	}
	
	public PurchaseOrderHBC (PurchaseOrderVO purchaseOrderVO) {
		order_id = purchaseOrderVO.getOrder_id();
		description = purchaseOrderVO.getDescription();
		gross_price = purchaseOrderVO.getGross_price();
		net_value = purchaseOrderVO.getNet_value();
		customer_id = purchaseOrderVO.getCustomer_id();
	}

	public void update (PurchaseOrderVO purchaseOrderVO) {
		description = purchaseOrderVO.getDescription();
/*		gross_price = purchaseOrderVO.getGross_price();
		net_value = purchaseOrderVO.getNet_value();
		customer_id = purchaseOrderVO.getCustomer_id();
*/
		gross_price = new Float (1.0);
		net_value = new Float (2.0);
		// Delete this JK
		customer_id = 3;
	}
	

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getGross_price() {
		return gross_price;
	}

	public void setGross_price(Float gross_price) {
		this.gross_price = gross_price;
	}

	public Float getNet_value() {
		return net_value;
	}

	public void setNet_value(Float net_value) {
		this.net_value = net_value;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
}
