package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.CcmVO;

public class CcmHBC {
	
	private Integer ccm_id;
	private String ccm_name;
	
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

	public CcmHBC() {

	}

	public CcmHBC(CcmVO ccmVO) {
		ccm_id = ccmVO.getCcm_id();
		ccm_name = ccmVO.getCcm_name();
	}

	public void update(CcmVO ccmVO) {
		ccm_name = ccmVO.getCcm_name();
	}
}
