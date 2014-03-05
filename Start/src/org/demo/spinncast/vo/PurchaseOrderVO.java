package org.demo.spinncast.vo;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.misc.Size;

@ManagedBean(name = "PurchaseOrderVO")
@SessionScoped
public class PurchaseOrderVO {
	private Integer order_id;
	private String description;
	private Float quantity;
	private Float gross_price;

	private Float net_value;
	private Integer customer_id;
	
	List<LineItemVO> lineItemList;
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

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
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

	public List<LineItemVO> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<LineItemVO> lineItemList) {
		this.lineItemList = lineItemList;
	}

}
