package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.MpmVO;

public class MpmHBC {
	
	private Integer mpm_id;
	private String mpm_name;
	
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

	public MpmHBC() {

	}

	public MpmHBC(MpmVO mpmVO) {
		mpm_id = mpmVO.getMpm_id();
		mpm_name = mpmVO.getMpm_name();
	}

	public void update(MpmVO mpmVO) {
		mpm_name = mpmVO.getMpm_name();
	}
}
