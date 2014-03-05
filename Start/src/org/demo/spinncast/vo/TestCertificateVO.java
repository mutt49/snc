package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.misc.Size;

@ManagedBean(name = "TestCertificateVO")
@SessionScoped
public class TestCertificateVO {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public MaterialVO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialVO material) {
		this.material = material;
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

	int id;
	
	String name;
	
	String description;
	
	Size size;
	
	MaterialVO material;
	
	double quantity;
	
	double rate;
}
