package org.demo.spinncast.vo;

import org.demo.spinncast.hibernate.MpmHBC;

public class MpmVO {

	private Integer mpm_id;
	private String mpm_name;
	
	public MpmVO(MpmHBC mpmHBC) {
		mpm_id = mpmHBC.getMpm_id();
		mpm_name = mpmHBC.getMpm_name();
	}
	
	public MpmVO () {
		
	}
	public Integer getMpm_id() {
		return mpm_id;
	}
	public void setMpm_id(Integer mpm_id) {
		this.mpm_id = mpm_id;
	}
	public String getMpm_name() {
		return mpm_name;
	}
	public void setMpm_name(String mpm_name) {
		this.mpm_name = mpm_name;
	}
}
