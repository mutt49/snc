package org.demo.spinncast.vo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.hibernate.PartMasterHBC;

@ManagedBean(name = "PartMasterVO")
@SessionScoped
public class PartMasterVO {
	private int partId;
	private String partName;
	private String drgNo;
	private float partRate;
	private float pmSize;
	private String partUom;
	//private String grade;
	private float castWeight;
	private float proofMachineWeight;
	private float quantity;
	private List<GradeMasterVO> grades;
	List<PartGradeMappingVO> partGradeMapping = new ArrayList<PartGradeMappingVO>();
	
	public PartMasterVO(){
		
	}
	
	public PartMasterVO(PartMasterHBC partMasterHbc){
		this.castWeight = partMasterHbc.getCastWeight();
		this.drgNo = partMasterHbc.getDrgNo();
		this.partId = partMasterHbc.getPartId();
		this.partName = partMasterHbc.getPartName();
		this.partRate = partMasterHbc.getPartRate();
		this.partUom = partMasterHbc.getPartUom();
		this.pmSize = partMasterHbc.getPmSize();
		this.proofMachineWeight = partMasterHbc.getProofMachineWeight();
		this.quantity = partMasterHbc.getQuantity();
	}

	public List<PartGradeMappingVO> getPartGradeMapping() {
		return partGradeMapping;
	}

	public void setPartGradeMapping(List<PartGradeMappingVO> partGradeMapping) {
		this.partGradeMapping = partGradeMapping;
	}
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

	public List<GradeMasterVO> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeMasterVO> grades) {
		this.grades = grades;
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

}
