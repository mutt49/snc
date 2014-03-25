package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.PartMasterVO;

public class PartMasterHBC {
	private int partId;
	private String partName;
	private String drgNo;
	private float partRate;
	private float pmSize;
	private String partUom;
	private String grade;
	private float castWeight;
	private float proofMachineWeight;
	private float quantity;	
	
	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getDrgNo() {
		return drgNo;
	}

	public void setDrgNo(String drgNo) {
		this.drgNo = drgNo;
	}

	public float getPartRate() {
		return partRate;
	}

	public void setPartRate(float partRate) {
		this.partRate = partRate;
	}

	public float getPmSize() {
		return pmSize;
	}

	public void setPmSize(float pmSize) {
		this.pmSize = pmSize;
	}

	public String getPartUom() {
		return partUom;
	}

	public void setPartUom(String partUom) {
		this.partUom = partUom;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public float getCastWeight() {
		return castWeight;
	}

	public void setCastWeight(float castWeight) {
		this.castWeight = castWeight;
	}

	public float getProofMachineWeight() {
		return proofMachineWeight;
	}

	public void setProofMachineWeight(float proofMachineWeight) {
		this.proofMachineWeight = proofMachineWeight;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public PartMasterHBC () {
		
	}
	
	public PartMasterHBC (PartMasterVO partMasterVo) {
		partId = partMasterVo.getPartId();
		partName = partMasterVo.getPartName();
		drgNo = partMasterVo.getDrgNo();
		partRate = partMasterVo.getPartRate();
		pmSize = partMasterVo.getPmSize();
		partUom = partMasterVo.getPartUom();
		grade = partMasterVo.getGrade();
		castWeight = partMasterVo.getCastWeight();
		proofMachineWeight = partMasterVo.getProofMachineWeight();
		quantity = partMasterVo.getQuantity();
	}

	public void update (PartMasterVO partMasterVo) {
		partId = partMasterVo.getPartId();
		partName = partMasterVo.getPartName();
		drgNo = partMasterVo.getDrgNo();
		partRate = partMasterVo.getPartRate();
		pmSize = partMasterVo.getPmSize();
		partUom = partMasterVo.getPartUom();
		grade = partMasterVo.getGrade();
		castWeight = partMasterVo.getCastWeight();
		proofMachineWeight = partMasterVo.getProofMachineWeight();
		quantity = partMasterVo.getQuantity();
	}
	
}
