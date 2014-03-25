package org.demo.spinncast.vo;

import org.demo.spinncast.hibernate.CcmHBC;

public class CcmVO {
	private Integer ccm_id;
	private String ccm_name;
	
	public CcmVO(CcmHBC ccmHBC) {
		ccm_id = ccmHBC.getCcm_id();
		ccm_name = ccmHBC.getCcm_name();
	}
	
	public CcmVO () {
		
	}
	
	public Integer getCcm_id() {
		return ccm_id;
	}
	public void setCcm_id(Integer ccm_id) {
		this.ccm_id = ccm_id;
	}
	public String getCcm_name() {
		return ccm_name;
	}
	public void setCcm_name(String ccm_name) {
		this.ccm_name = ccm_name;
	}
	
}
